package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;

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
     * Asks the player to choose a track from the available tracks.
     * @return the chosen track file name.
     */
    String findTrack() throws Exception;

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
    BotStrategy chooseEachBotStrategyDifficulty(CarColour carColour);

    /**
     * Asks if the player is satisfied with the current configuration.
     * @return true if the player is satisfied, false otherwise.
     */
    boolean askIfSatisfiedWithConfiguration(RaceTrack raceTrack, List<Player> players);

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
     * Displays the end of the game message.
     */
    void displayEndMatchMessage();

    Position chooseStartingPosition(Player player, List<Position> availablePositions);
    boolean askToChooseForEachBot();
    public BotStrategy chooseAllBotStrategyDifficulty();
}
