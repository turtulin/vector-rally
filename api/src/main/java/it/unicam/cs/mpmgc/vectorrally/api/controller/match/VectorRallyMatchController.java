package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.io.IOController;
import it.unicam.cs.mpmgc.vectorrally.api.controller.io.Utils;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.BotPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMoveValidator;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMovesGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.BotStrategyFactory;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.DecisionStrategy;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

public class VectorRallyMatchController implements MatchController {
    private List<Player> players;
    private RaceTrack raceTrack;
    private final IOController ioController;
    private final BasicMovesGenerator<NeighborsGenerator> moveGenerator;
    private Queue<Player> turnQueue;
    private final BotStrategyFactory botStrategyFactory;
    private boolean gameOver;
    private final BasicMoveValidator moveValidator;
    private int turnCounter = 0;


    public VectorRallyMatchController(IOController ioController, BasicMovesGenerator<NeighborsGenerator> moveGenerator) {
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
    public void startMatch() throws Exception {
        while (!gameOver) {
            handleTurn(Objects.requireNonNull(turnQueue.poll()));
        }
        endMatch();
    }

    @Override
    public void handleTurn(Player player) {
        Utils.printTurnMessage(turnCounter++, player);
        List<Move> possibleMoves = moveGenerator.generatePossibleMoves(player, raceTrack, players);
        if (possibleMoves.isEmpty()) handleElimination(player);
         else {
            int chosenMoveIndex;
            if (player instanceof BotPlayer botPlayer) {
                chosenMoveIndex = handleTurnBot(botPlayer, possibleMoves);
            } else {
                chosenMoveIndex = handleTurnHuman(possibleMoves);
            }
            Move chosenMove = possibleMoves.get(chosenMoveIndex);
            handleMove(player, chosenMove);
        }
    }

    private int handleTurnHuman(List<Move> possibleMoves) {
        Utils.printRaceTrack(raceTrack, players, getPossibleDestinations(possibleMoves));
        return ioController.chooseMove(possibleMoves);
    }

    private int handleTurnBot(BotPlayer botPlayer, List<Move> possibleMoves) {
        DecisionStrategy strategy = botStrategyFactory.getStrategy(botPlayer.getStrategy());
        Move chosenMove = strategy.decideMove(botPlayer, possibleMoves);
        return possibleMoves.indexOf(possibleMoves.stream()
                .filter(move -> move.equals(chosenMove))
                .findFirst()
                .orElseThrow());
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
        turnQueue.add(player);
    }

    @Override
    public void handleElimination(Player player) {
        Utils.printEliminationMessage(player);
        turnQueue.remove(player);
        players.remove(player);
        if (players.isEmpty()) gameOver = true;
    }

    @Override
    public void endMatch() throws Exception {
        ioController.displayEndMatchMessage();
        if (ioController.askToPlayAnotherMatch()) {
            GameEngine engine = new VectorRallyEngine();
            engine.startGame();
        }
    }

    private boolean checkIfPlayerWins(Move move) {
        Position end = move.getDestination();
        return raceTrack.getComponentAt(end.getX(), end.getY()) == TrackComponent.END_LINE ||
                moveValidator.passesThroughComponent(raceTrack, move, TrackComponent.END_LINE);
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    private List<Position> getPossibleDestinations(List<Move> moves) {
        return moves.stream().map(Move::getDestination).collect(Collectors.toList());
    }
}

