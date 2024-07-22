package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
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
public interface IOController extends BasicIOController {

    /**
     * Displays the racetrack.
     *
     * @param raceTrack the racetrack to display.
     * @param players the list of players.
     * @param destinations the list of destinations.
     */
    void printRaceTrack(RaceTrack raceTrack, List<Player> players, List<Position> destinations);

    /**
     * Displays a message.
     *
     * @param message the message to display
     */
    void displayMessage(String message);

    /**
     * Asks if the player wants to play another match.
     * @return true if the player wants to play another match, false otherwise.
     */
    boolean askToPlayAnotherMatch();

    /**
     * Asks the player to choose a move from the available moves.
     * @param possibleMoves the list of possible moves.
     * @return the index of the chosen move.
     */
    int chooseMove(List<Move> possibleMoves);

    /**
     * Asks the player to choose a starting position from the available positions.
     * @param player the player that has to choose the starting position.
     * @param availablePositions the list of available positions.
     * @return the chosen starting position.
     */
    Position chooseStartingPosition(Player player, List<Position> availablePositions);

    /**
     * Displays the welcome message and the game rules.
     */
    void displayWelcomeAndRules();
    /**
     * Asks if the player knows the game rules.
     * @return true if the player knows the rules, false otherwise.
     */
    boolean askIfPlayerKnowsRules();

    /**
     * Asks the player to choose a rule type.
     * @return the chosen rule type.
     */
    int chooseRuleType();

    /**
     * Choose the track files available.
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
     * Asks the player to choose a car color.
     * @param availableColors the list of available colors.
     * @return the chosen car color.
     */
    CarColour chooseCarColor(List<CarColour> availableColors);

    /**
     * Asks the player to choose the difficulty for each bot.
     * @return true if the player wants to choose the difficulty for each bot, false otherwise.
     */
    boolean askToChooseForEachBot();

    /**
     * Asks the player to choose a bot strategy difficulty.
     * @param carColour the car color of the bot.
     * @return the chosen bot strategy difficulty.
     */
    BotStrategy chooseEachBotStrategyDifficulty(CarColour carColour);

    /**
     * Asks the player to choose a bot strategy difficulty for all bots.
     * @return the chosen bot strategy difficulty.
     */
    BotStrategy chooseAllBotsStrategyDifficulty();
    /**
     * Asks if the player is satisfied with the configuration.
     * @return true if the player is satisfied, false otherwise.
     */
    boolean askIfSatisfiedWithConfiguration(RaceTrack raceTrack, List<Player> players);

    /**
     * Prints the message of the end of the match.
     */
    void displayEndMatchMessage();

    /**
     * Initializes the shift algorithm chosen by the player.
     *
     * @return the neighbors generator initialized.
     */
    NeighborsGenerator initializeShiftAlgorithm();
}
