package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Coordinates;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.Track;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;

import java.util.List;

/**
 * Defines methods for input and output operations in the game.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface IOController {

    /**
     * Displays the welcome message and the game rules.
     */
    void displayWelcome();

    /**
     * Asks if the player knows the game rules.
     * @return {@code true} if the player knows the rules, {@code false} otherwise.
     */
    boolean getAskIfPlayerIgnoresRules();

    /**
     * Displays the rules of the game.
     */
    void displayGameRules();

    /**
     * Displays the shift rule types.
     */
    void displayShiftRuleType();

    /**
     * Asks the player to choose a rule type.
     * @return the chosen rule type.
     */
    NeighborsGenerator getRuleType();

    /**
     * Displays the tracks available.
     * @param trackFiles the {@link List} of track files.
     */
    void displayTracks(List<String> trackFiles);

    /**
     * Choose the track files available.
     * @param trackFiles the {@link List} of track files.
     * @return the chosen track file name.
     */
    String getTrack(List<String> trackFiles);

    /**
     * Displays the number of human players that can be chosen.
     * @param maxPlayers the maximum number of players allowed.
     */
    void displayChooseNumHumanPlayers(int maxPlayers);

    /**
     * Asks for the number of human players.
     * @param maxPlayers the maximum number of players allowed.
     * @return the number of human players.
     */
    int getNumberOfHumanPlayers(int maxPlayers);

    /**
     * Displays the message asking if the player wants to play another match.
     */
    void displayAskIfPlayerWantsToPlayAnotherMatch();

    /**
     * Asks if the player wants to play another match.
     * @return {@code true} if the player wants to play another match, {@code false} otherwise.
     */
    boolean getAskToPlayAnotherMatch();

    /**
     * Asks the player to choose a move from the available moves.
     * @param numMoves the number of possible moves.
     */
    void displayMoves(int numMoves);

    /**
     * Asks the player to choose a move from the available moves.
     * @param possibleMoves the {@link List} of possible {@link Move} objects.
     * @return the chosen {@link Move} .
     */
    Move getChosenMove(List<Move> possibleMoves);

    /**
     * Displays the racetrack.
     *
     * @param raceTrack the {@link Track} to display.
     * @param players the {@link List} of {@link Player} .
     * @param destinations the {@link List} of {@link Coordinates} destinations.
     */
    void printRaceTrack(Track raceTrack, List<Player> players, List<Coordinates> destinations);

    /**
     * Displays the message that the player can proceed to the next turn.
     */
    void displayGoToNextTurn();

    /**
     * Asks the player to proceed to the next turn.
     */
    void getGoToNextTurn();

    /**
     * Displays the game over message.
     */
    void displayGameOver();

    /**
     * Displays the winner of the game.
     * @param winner the {@link Player} who won the game.
     */
    void displayTurn(Player winner, int counter);

    /**
     * Displays the elimination of a player.
     * @param player the {@link Player} who was eliminated.
     */
    void displayElimination(Player player);

    /**
     * Displays the winning message.
     * @param winner the {@link Player} who won the game.
     */
    void displayWinningMessage(Player winner);

    /**
     * Displays the bot strategies difficulty.
     */
    void displayBotStrategyDifficulty();

    /**
     * Asks the player to choose a bot strategy difficulty for all bots.
     * @return the chosen {@link BotStrategy} difficulty.
     */
    BotStrategy getBotsStrategyDifficulty();

}
