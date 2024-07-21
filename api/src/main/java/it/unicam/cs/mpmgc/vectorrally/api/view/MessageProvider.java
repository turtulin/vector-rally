package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

/**
 * Defines the interface for providing various messages in the game.
 * Extends the CLIUtils interface.
 *
 * @version 1.0
 * @since 2024-07-18
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface MessageProvider extends CLIUtils {

    /**
     * Returns the message for the current turn and player.
     *
     * @param turn the current turn number
     * @param player the player whose turn it is
     * @return the message indicating the current turn and player
     */
    String getTurnMessage(int turn, Player player);

    /**
     * Returns the message for when a player wins.
     *
     * @param player the player who won
     * @return the message indicating the player has won
     */
    String getWinMessage(Player player);

    /**
     * Returns the message for when a player is eliminated.
     *
     * @param player the player who is eliminated
     * @return the message indicating the player has been eliminated
     */
    String getEliminationMessage(Player player);

    /**
     * Returns the welcome message.
     *
     * @return the welcome message
     */
    String getWelcomeMessage();

    /**
     * Returns the game rules message.
     *
     * @return the game rules message
     */
    String getGameRules();

    /**
     * Returns the game over message.
     *
     * @return the game over message
     */
    String getGameOverMessage();

    /**
     * Returns the congratulations message.
     *
     * @return the congratulations message
     */
    String getCongratulationsMessage();

    /**
     * Returns the end message.
     *
     * @return the end message
     */
    String getEndMessage();

    /**
     * Returns the invalid choice message.
     *
     * @return the invalid choice message
     */
    String getInvalidChoiceMessage();

    /**
     * Returns the message asking if the player knows the rules.
     *
     * @return the message asking if the player knows the rules
     */
    String getAskIfPlayerKnowsRulesMessage();

    /**
     * Returns the rule type choice message.
     *
     * @return the rule type choice message
     */
    String getRuleTypeChoiceMessage();

    /**
     * Returns the track choice message.
     *
     * @return the track choice message
     */
    String getTrackChoiceMessage();

    /**
     * Returns the move choice message.
     *
     * @return the move choice message
     */
    String getMoveChoiceMessage();

    /**
     * Returns the message asking to play again.
     *
     * @return the message asking to play again
     */
    String getAskToPlayAgainMessage();

    /**
     * Returns the message asking if the player is satisfied with the configuration.
     *
     * @return the message asking if the player is satisfied with the configuration
     */
    String getAskIfSatisfiedWithConfigurationMessage();

    /**
     * Returns the message asking to choose for each bot.
     *
     * @return the message asking to choose for each bot
     */
    String getAskToChooseForEachBotMessage();

    /**
     * Returns the car color choice message.
     *
     * @return the car color choice message
     */
    String getCarColourChoiceMessage();

    /**
     * Returns the message asking for the number of human players.
     *
     * @param maxPlayers the maximum number of players allowed
     * @return the message asking for the number of human players
     */
    String getAskNumberOfHumanPlayersMessage(int maxPlayers);

    /**
     * Returns the message to choose each bot's strategy difficulty.
     *
     * @param carColour the color of the car
     * @return the message to choose each bot's strategy difficulty
     */
    String getChooseEachBotStrategyDifficultyMessage(CarColour carColour);

    /**
     * Returns the message to choose the strategy difficulty for all bots.
     *
     * @return the message to choose the strategy difficulty for all bots
     */
    String getChooseAllBotStrategyDifficultyMessage();

    /**
     * Returns the message to choose the starting position for the car.
     *
     * @param carColour the color of the car
     * @return the message to choose the starting position for the car
     */
    String getChooseStartingPositionMessage(CarColour carColour);

    /**
     * Returns the message for the next turn.
     *
     * @return the message for the next turn
     */
    String getNextTurnMessage();
}
