package it.unicam.cs.mpmgc.vectorrally.api.controller.io;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategyDifficulty;

import java.util.List;

/**
 * This interface defines methods for input and output operations in the game.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface IOController {
    /**
     * Displays a welcome message to the player.
     */
    void displayWelcomeMessage();

    /**
     * Asks if the player knows the game rules.
     * @return true if the player knows the rules, false otherwise.
     */
    boolean askIfPlayerKnowsRules();

    /**
     * Displays the game rules.
     */
    void displayGameRules();

    /**
     * Asks the player to choose a rule type.
     * @return the chosen rule type.
     */
    int chooseRuleType();

    /**
     * Asks the player to choose a track from the available tracks.
     * @return the chosen track file name.
     */
    String chooseTrack() throws Exception;

    /**
     * Asks for the number of human players.
     * @param maxPlayers the maximum number of players allowed.
     * @return the number of human players.
     */
    int askNumberOfHumanPlayers(int maxPlayers);

    /**
     * Asks the player to choose a car color.
     * @param availableColors the list of available colors.
     * @return the chosen car color.
     */
    CarColour chooseCarColor(List<CarColour> availableColors);

    /**
     * Asks the player to choose a bot strategy difficulty.
     * @return the chosen bot strategy difficulty.
     */
    BotStrategyDifficulty chooseBotStrategyDifficulty();

    /**
     * Asks if the player is satisfied with the current configuration.
     * @return true if the player is satisfied, false otherwise.
     */
    boolean askIfSatisfiedWithConfiguration();

    /**
     * Asks the player to choose a move from the available moves.
     * @param possibleMoves the list of possible moves.
     * @return the index of the chosen move.
     */
    int chooseMove(List<Move> possibleMoves);

    /**
     * Asks if the player wants to play another match.
     * @return true if the player wants to play another match, false otherwise.
     */
    boolean askToPlayAnotherMatch();

    /**
     * Ask the player to start another match with the same configuration.
     */
    boolean sameConfiguration();

    /**
     * Displays the end of the game message.
     */
    void displayEndMatchMessage();

    /**
     * Displays an error message.
     */
    void displayErrorMessage(String message);
    void printRaceTrack(RaceTrack raceTrack, List<Player> players);

    Position chooseStartingPosition(List<Position> availablePositions, int playerIndex);
}
