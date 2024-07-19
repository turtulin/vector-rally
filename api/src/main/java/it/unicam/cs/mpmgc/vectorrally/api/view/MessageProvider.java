package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

public interface MessageProvider {
    String getTurnMessage(int turn, Player player);
    String getWinMessage(Player player);
    String getEliminationMessage(Player player);
    String getWelcomeMessage();
    String getGameRules();
    String getGameOverMessage();
    String getEndMessage();
    String getInvalidChoiceMessage();
    String getAskIfPlayerKnowsRulesMessage();
    String getRuleTypeChoiceMessage();
    String getTrackChoiceMessage();
    String getMoveChoiceMessage();
    String getAskToPlayAgainMessage();
    String getAskIfSatisfiedWithConfigurationMessage();
    String getAskToChooseForEachBotMessage();
    String getCarColourChoiceMessage();
    String getAskNumberOfHumanPlayersMessage(int maxPlayers);
    String getChooseEachBotStrategyDifficultyMessage(CarColour carColour);
    String getChooseAllBotStrategyDifficultyMessage();
    String getChooseStartingPositionMessage(CarColour carColour);

    default String getCarColorCode(CarColour colour) {
        return switch (colour) {
            case RED -> "\033[31m";
            case ORANGE -> "\033[38;5;208m";
            case YELLOW -> "\033[33m";
            case GREEN -> "\033[32m";
            case BLUE -> "\033[34m";
            case PURPLE -> "\033[35m";
            case WHITE -> "\033[37m";
            case PINK -> "\033[38;5;205m";
            case GREY -> "\033[38;5;240m";
            case BROWN -> "\033[38;5;94m";
        };
    }
}
