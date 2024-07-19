package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;

import java.util.List;

public interface CLIIOController extends IOControllerNew {

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

}
