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
     * Displays the racetrack.
     *
     * @param raceTrack the {@link Track} to display.
     * @param players the {@link List} of {@link Player} .
     * @param destinations the {@link List} of {@link Coordinates} destinations.
     */
    void printRaceTrack(Track raceTrack, List<Player> players, List<Coordinates> destinations);

    /**
     * Displays a message.
     *
     * @param message the message to display
     */
    void displayMessage(String message);

    /**
     * Asks if the player wants to play another match.
     * @return {@code true} if the player wants to play another match, {@code false} otherwise.
     */
    boolean askToPlayAnotherMatch();

    /**
     * Asks the player to choose a move from the available moves.
     * @param possibleDestinations the {@link List} of possible {@link Coordinates} destinations.
     */
    void displayMoves(List<Coordinates> possibleDestinations);

    /**
     * Asks the player to choose a move from the available moves.
     * @param possibleMoves the {@link List} of possible {@link Move} objects.
     * @return the chosen {@link Move} .
     */
    Move chooseMove(List<Move> possibleMoves);

    /**
     * Displays the welcome message and the game rules.
     */
    void displayWelcomeAndRules();

    /**
     * Asks if the player knows the game rules.
     * @return {@code true} if the player knows the rules, {@code false} otherwise.
     */
    boolean askIfPlayerKnowsRules();

    /**
     * Asks the player to choose a rule type.
     * @return the chosen rule type.
     */
    int chooseRuleType();

    /**
     * Choose the track files available.
     * @param trackFiles the {@link List} of track files.
     * @return the chosen track file name.
     */
    String pickTrack(List<String> trackFiles);

    /**
     * Asks for the number of human players.
     * @param maxPlayers the maximum number of players allowed.
     * @return the number of human players.
     */
    int askNumberOfHumanPlayers(int maxPlayers);

    /**
     * Asks the player to choose a bot strategy difficulty for all bots.
     * @return the chosen {@link BotStrategy} difficulty.
     */
    BotStrategy chooseAllBotsStrategyDifficulty();

    /**
     * Initializes the shift algorithm chosen by the player.
     *
     * @return the {@link NeighborsGenerator}  initialized.
     */
    NeighborsGenerator initializeShiftAlgorithm();

    /**
     * Asks the player to proceed to the next turn.
     */
    void goToNextTurn();

    /**
     * Displays the tracks available.
     * @param trackFiles the {@link List} of track files.
     */
    void displayTracks(List<String> trackFiles);
}
