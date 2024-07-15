package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.io.IOController;
import it.unicam.cs.mpmgc.vectorrally.api.controller.io.Utils;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.AStar;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.AStarManhattan;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.BotPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMoveValidator;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMovesGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.BotStrategyFactory;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.MoveValidator;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.DecisionStrategy;

import java.nio.file.attribute.PosixFileAttributes;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

public class VectorRallyMatchController implements MatchController {
    private List<Player> players;
    private RaceTrack raceTrack;
    private final IOController ioController;
    private final BasicMovesGenerator moveGenerator;
    private Queue<Player> turnQueue;
    private final BotStrategyFactory botStrategyFactory;
    private boolean gameOver;
    private BasicMoveValidator moveValidator;


    public VectorRallyMatchController(IOController ioController, BasicMovesGenerator moveGenerator) {
        this.ioController = ioController;
        this.moveGenerator = moveGenerator;
        this.botStrategyFactory = initializeBotStrategyFactory();
        this.moveValidator = new BasicMoveValidator();
    }

    private BotStrategyFactory initializeBotStrategyFactory() {
        AStar aStarAlgorithm = new AStarManhattan(raceTrack);  // Initialize AStar with raceTrack
        return new BotStrategyFactory(aStarAlgorithm);  // Initialize BotStrategyFactory
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
            handleTurn(Objects.requireNonNull(turnQueue.poll()));
        }
        endMatch();
    }

    @Override
    public void handleTurn(Player player) {
        Utils.printTurnMessage(turnQueue.size(), player);
        List<Move> possibleMoves = moveGenerator.generatePossibleMoves(player, raceTrack, players);
        Utils.printRaceTrack(raceTrack, players, getPossibleDestinations(possibleMoves));
        if (possibleMoves.isEmpty()) {
            handleElimination(player);
        } else {
            int chosenMoveIndex;
            if (player instanceof BotPlayer botPlayer) {
                DecisionStrategy strategy = botStrategyFactory.getStrategy(botPlayer.getStrategy());
                Move chosenPosition = strategy.decideMove(player, possibleMoves);
                chosenMoveIndex = possibleMoves.indexOf(possibleMoves.stream()
                        .filter(move -> move.equals(chosenPosition))
                        .findFirst()
                        .orElseThrow());
            } else {
                chosenMoveIndex = ioController.chooseMove(possibleMoves);
            }
            Move chosenMove = possibleMoves.get(chosenMoveIndex);
            handleMove(player, chosenMove);
        }
    }

    @Override
    public void handleMove(Player player, Move move) {
        if (checkIfPlayerWins(move)) {
            Utils.printWinMessage(player);
            gameOver = true;
            return;
        }
        player.setPosition(move.getDestination());
        player.setPlayerAcceleration(move.acceleration());
        System.out.println(" new speed " + move.acceleration());
        turnQueue.add(player);
    }

    @Override
    public void handleElimination(Player player) {
        Utils.printEliminationMessage(player);
        turnQueue.remove(player);
        players.remove(player);
        if (players.isEmpty()) {
            gameOver = true;
        }
    }

    @Override
    public void endMatch() {
        ioController.displayEndMatchMessage();
    }

    private boolean checkIfPlayerWins(Move move) {
        Position end = move.getDestination();
        return raceTrack.getComponentAt(end.getX(), end.getY()) == TrackComponent.END_LINE ||
                moveValidator.passesThroughComponent(raceTrack, move, TrackComponent.END_LINE);
    }

    @Override
    public boolean checkGameOver() {
        return gameOver;
    }

    private List<Position> getPossibleDestinations(List<Move> moves) {
        return moves.stream().map(Move::getDestination).collect(Collectors.toList());
    }
}

