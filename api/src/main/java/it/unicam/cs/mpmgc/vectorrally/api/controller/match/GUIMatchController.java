package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.BotStrategyFactory;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.BotPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMoveValidator;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMovesGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.DecisionStrategy;
import it.unicam.cs.mpmgc.vectorrally.api.view.GraphicalIOController;
import it.unicam.cs.mpmgc.vectorrally.api.view.IOController;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class GUIMatchController implements MatchController {
    private List<Player> players;
    private RaceTrack raceTrack;
    private final IOController ioController;
    private final BasicMovesGenerator<NeighborsGenerator> moveGenerator;
    private Queue<Player> turnQueue;
    private final BotStrategyFactory botStrategyFactory;
    private boolean gameOver;
    private final BasicMoveValidator moveValidator;
    private int turnCounter = 0;
    private Player currentPlayer;
    private boolean isMovePending = false;
    private Move pendingMove;


    public GUIMatchController(GraphicalIOController ioController, BasicMovesGenerator<NeighborsGenerator> moveGenerator) {
        this.ioController = ioController;
        this.moveGenerator = moveGenerator;
        this.botStrategyFactory = initializeBotStrategyFactory();
        this.moveValidator = new BasicMoveValidator();
    }

    private BotStrategyFactory initializeBotStrategyFactory() {
        return new BotStrategyFactory();
    }

    @Override
    public void initializeMatch(List<Player> players, RaceTrack raceTrack) {
        this.players = players;
        this.raceTrack = raceTrack;
        this.turnQueue = new LinkedList<>(players);
        this.gameOver = false;
    }

    @Override
    public void startMatch() {
        while (!gameOver) {
            nextTurn();
        }
    }

    public void nextTurn() {
        if (isMovePending) {
            executePendingMove();
        } else {
            currentPlayer = turnQueue.poll();
            if (currentPlayer == null) {
                return;
            }
            handleTurn(currentPlayer);
        }
    }

    @Override
    public void handleTurn(Player player) {
        //ioController.displayMessage(messageProvider.getTurnMessage(turnCounter++, player));
        List<Move> possibleMoves = moveGenerator.generatePossibleMoves(player, raceTrack, players);
        if (possibleMoves.isEmpty()) {
            handleElimination(player);
        } else {
            if (player instanceof BotPlayer botPlayer) {
                showBotMoves(botPlayer, possibleMoves);
            } else {
                showHumanPlayerMoves(possibleMoves);
            }
        }
    }

    private void showHumanPlayerMoves(List<Move> possibleMoves) {
        //ioController.printRaceTrack(raceTrack, players, getPossibleDestinations(possibleMoves));
        //pendingMove = possibleMoves.get(ioController.chooseMove(possibleMoves));
        isMovePending = true;
        ioController.waitForNextTurn();
    }

    private void showBotMoves(BotPlayer botPlayer, List<Move> possibleMoves) {
        //ioController.printRaceTrack(raceTrack, players, getPossibleDestinations(possibleMoves));
        DecisionStrategy strategy = botStrategyFactory.getStrategy(botPlayer.getStrategy());
        pendingMove = strategy.decideMove(botPlayer, possibleMoves);
        isMovePending = true;
        ioController.waitForNextTurn();
    }

    private void executePendingMove() {
        makeMove(currentPlayer, pendingMove);
        isMovePending = false;
        turnQueue.add(currentPlayer);
        if (checkIfPlayerWins(pendingMove)) {
            gameOver = true;
        }
    }

    private void makeMove(Player currentPlayer, Move pendingMove) {
        currentPlayer.setPosition(pendingMove.getDestination());
        currentPlayer.setPlayerAcceleration(pendingMove.acceleration());
    }

    @Override
    public void handleElimination(Player player) {
        turnQueue.remove(player);
        players.remove(player);
        if (players.isEmpty()) {
            System.out.println("Game over");
            gameOver = true;
        }
    }

    public boolean checkIfPlayerWins(Move move) {
        Position end = move.getDestination();
        return raceTrack.getComponentAt(end.getX(), end.getY()) == TrackComponent.END_LINE ||
                moveValidator.passesThroughComponent(raceTrack, move, TrackComponent.END_LINE);
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    public List<Position> getPossibleDestinations(List<Move> moves) {
        return moves.stream().map(Move::getDestination).collect(Collectors.toList());
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public List<Move> getPossibleMoves() {
        return moveGenerator.generatePossibleMoves(currentPlayer, raceTrack, players);
    }
}
