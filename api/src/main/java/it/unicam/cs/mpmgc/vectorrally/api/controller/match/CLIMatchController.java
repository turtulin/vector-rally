package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.view.*;
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
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Manages the game match for the CLI-based interface, implementing the MatchController interface.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class CLIMatchController implements MatchController {
    private List<Player> players;
    private RaceTrack raceTrack;
    private final CLIIOController ioController;
    private final BasicMovesGenerator<NeighborsGenerator> moveGenerator;
    private Queue<Player> turnQueue;
    private final BotStrategyFactory botStrategyFactory;
    private boolean gameOver;
    private final BasicMoveValidator moveValidator;
    private final MessageProvider messageProvider = new GameMessageProvider();
    private int turnCounter = 0;
    private Player currentPlayer;
    private boolean isMovePending = false;
    private Move pendingMove;

    /**
     * Constructs a CLIMatchController with the specified IO controller and move generator.
     *
     * @param ioController the IO controller for CLI input/output
     * @param moveGenerator the generator for possible moves
     */
    public CLIMatchController(CLIIOController ioController, BasicMovesGenerator<NeighborsGenerator> moveGenerator) {
        this.ioController = ioController;
        this.moveGenerator = moveGenerator;
        this.botStrategyFactory = initializeBotStrategyFactory(moveGenerator.getNeighborsGenerator());
        this.moveValidator = new BasicMoveValidator();
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

    /**
     * Proceeds to the next turn in the match.
     */
    private void nextTurn() {
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
        ioController.displayMessage(messageProvider.getTurnMessage(turnCounter++, player));
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

    @Override
    public void handleElimination(Player player) {
        ioController.displayMessage(messageProvider.getEliminationMessage(player));
        turnQueue.remove(player);
        players.remove(player);
        if (players.isEmpty()) {
            gameOver = true;
            ioController.displayMessage(messageProvider.getGameOverMessage());
        }
    }

    /**
     * Checks if the move results in the player winning the match.
     *
     * @param move the move made by the player
     * @return true if the player wins, false otherwise
     */
    private boolean checkIfPlayerWins(Move move) {
        Position end = move.getDestination();
        return raceTrack.getComponentAt(end.getX(), end.getY()) == TrackComponent.END_LINE ||
                moveValidator.passesThroughComponent(raceTrack, move, TrackComponent.END_LINE);
    }

    /**
     * Gets the possible destinations for the given moves.
     *
     * @param moves the list of moves
     * @return the list of possible destination positions
     */
    private List<Position> getPossibleDestinations(List<Move> moves) {
        return moves.stream().map(Move::getDestination).collect(Collectors.toList());
    }

    /**
     * Displays possible moves for a human player and sets up for the next turn.
     *
     * @param possibleMoves the list of possible moves for the human player
     */
    private void showHumanPlayerMoves(List<Move> possibleMoves) {
        ioController.printRaceTrack(raceTrack, players, getPossibleDestinations(possibleMoves));
        pendingMove = possibleMoves.get(ioController.chooseMove(possibleMoves));
        isMovePending = true;
        ioController.waitForNextTurn();
    }

    /**
     * Displays possible moves for a bot player and sets up for the next turn.
     *
     * @param botPlayer the bot player whose moves are being displayed
     * @param possibleMoves the list of possible moves for the bot player
     */
    private void showBotMoves(BotPlayer botPlayer, List<Move> possibleMoves) {
        ioController.printRaceTrack(raceTrack, players, getPossibleDestinations(possibleMoves));
        DecisionStrategy strategy = botStrategyFactory.getStrategy(botPlayer.getStrategy());
        pendingMove = strategy.decideMove(botPlayer, possibleMoves);
        isMovePending = true;
        ioController.waitForNextTurn();
    }

    /**
     * Executes the pending move for the current player.
     */
    private void executePendingMove() {
        makeMove(currentPlayer, pendingMove);
        isMovePending = false;
        turnQueue.add(currentPlayer);
        if (checkIfPlayerWins(pendingMove)) {
            ioController.displayMessage(messageProvider.getWinMessage(currentPlayer));
            ioController.displayMessage(messageProvider.getCongratulationsMessage());
            gameOver = true;
        }
    }

    /**
     * Makes the specified move for the given player.
     *
     * @param player the player making the move
     * @param move the move to be made
     */
    private void makeMove(Player player, Move move) {
        player.setPosition(move.getDestination());
        player.setPlayerAcceleration(move.acceleration());
    }

    /**
     * Initializes the bot strategy factory with the specified neighbors' generator.
     *
     * @param neighborsGenerator the neighbors generator
     * @return the bot strategy factory
     */
    private BotStrategyFactory initializeBotStrategyFactory(NeighborsGenerator neighborsGenerator) {
        return new BotStrategyFactory(neighborsGenerator);
    }
}

