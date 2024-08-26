package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.BotStrategyFactory;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.BotPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.Track;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMovesGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.view.MatchGameView;

import java.util.List;

/**
 * The {@code BasicMatchController} class is responsible for managing the flow of a game match,
 * including handling player turns, determining possible moves, processing player actions, and determining the end of the game.
 * <p>
 * This class interacts with the {@link MatchGameView} to update the game interface based on the current state of the match,
 * and it uses the {@link BasicMovesGenerator} to generate possible moves for each player.
 * The controller also manages the turn order using a {@link TurnHandler} and determines the behavior of bot players through a {@link BotStrategyFactory}.
 *
 * @version 1.0
 * @since 2024-08-21
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class BasicMatchController implements MatchController {
    private TurnHandler turnHandler;
    private boolean isGameOn;
    private final List<Player> players;
    private final Track raceTrack;
    private final MatchGameView gameView;
    private final BasicMovesGenerator<NeighborsGenerator> moveGenerator;
    private final BotStrategyFactory botStrategyFactory;


    public BasicMatchController(MatchGameView gameView, BasicMovesGenerator<NeighborsGenerator> moveGenerator, List<Player> players, Track raceTrack) {
        this.gameView = gameView;
        this.moveGenerator = moveGenerator;
        this.raceTrack = raceTrack;
        this.isGameOn = true;
        this.players = players;
        this.botStrategyFactory = new BotStrategyFactory(moveGenerator.getNeighborsGenerator());
    }

    @Override
    public void startMatch() {
        this.turnHandler = new BasicTurnHandler(players);
        while (isGameOn()) {
            handleTurn(turnHandler.getCurrentPlayer());
            gameView.goToNextTurn();
        }
        handleEndGame();
    }

    @Override
    public void handleTurn(Player player) {
        turnHandler.startTurn();
        gameView.displayTurn(player, turnHandler.getTurnCounter());
        List<Move> possibleMoves = moveGenerator.generatePossibleMoves(player, raceTrack, turnHandler.getPlayers());
        if (!possibleMoves.isEmpty()) {
            gameView.displayPossibleMoves(turnHandler.getPlayers(), raceTrack, moveGenerator.getPossibleDestinations(possibleMoves));
            Move chosenMove = findMove(player, possibleMoves);
            player.makeMove(chosenMove);
            if (moveGenerator.isWinningMove(chosenMove, raceTrack)) setGameOn(false);
            turnHandler.endTurn();
        }
        else handleElimination(player);
    }

    @Override
    public void handleElimination(Player player) {
        gameView.displayElimination(player);
        turnHandler.removePlayer(player);
        if (turnHandler.getPlayers().isEmpty()) setGameOn(false);
    }

    @Override
    public void handleEndGame() {
        if (turnHandler.getPlayers().isEmpty()) gameView.displayGameOver();
        else gameView.displayWinner(turnHandler.getPlayers().getLast());
    }

    @Override
    public boolean isGameOn() {
        return isGameOn;
    }

    @Override
    public void setGameOn(boolean isGameOn) {
        this.isGameOn = isGameOn;
    }

    @Override
    public Move findMove(Player player, List<Move> possibleMoves) {
        if (player instanceof BotPlayer botPlayer) return botStrategyFactory.getStrategy(botPlayer.getStrategy()).decideMove(botPlayer, possibleMoves);
        else return gameView.getMoveChoice(possibleMoves);
    }

    @Override
    public TurnHandler getTurnHandler() {
        return turnHandler;
    }
}
