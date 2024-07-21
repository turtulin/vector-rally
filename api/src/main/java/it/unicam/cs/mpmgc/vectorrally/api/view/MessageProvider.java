package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

public interface MessageProvider extends CLIUtils {
    String getTurnMessage(int turn, Player player);
    String getWinMessage(Player player);
    String getEliminationMessage(Player player);
    String getWelcomeMessage();
    String getGameRules();
    String getGameOverMessage();
    String getCongratulationsMessage();
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
    String getNextTurnMessage();
}
