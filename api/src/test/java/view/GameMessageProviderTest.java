package view;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.BotPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.HumanPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.RaceCar;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;
import it.unicam.cs.mpmgc.vectorrally.api.view.GameMessageProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameMessageProviderTest {

    private GameMessageProvider messageProvider;
    private Player humanPlayer;
    private Player botPlayer;

    @BeforeEach
    void setUp() {
        messageProvider = new GameMessageProvider();
        humanPlayer = new HumanPlayer(new RaceCar(CarColour.RED));
        botPlayer = new BotPlayer(new RaceCar(CarColour.BLUE), BotStrategy.EASY);
    }

    @Test
    void getTurnMessageShouldReturnCorrectMessage() {
        String expectedHumanMessage = "TURN 1 for player \033[31mP\033[0m";
        String expectedBotMessage = "TURN 1 for player \033[34mB\033[0m";

        assertEquals(expectedHumanMessage, messageProvider.getTurnMessage(1, humanPlayer));
        assertEquals(expectedBotMessage, messageProvider.getTurnMessage(1, botPlayer));
    }

    @Test
    void getWinMessageShouldReturnCorrectMessage() {
        String expectedMessage = "Player RED has crossed the finish line";
        assertEquals(expectedMessage, messageProvider.getWinMessage(humanPlayer));
    }

    @Test
    void getEliminationMessageShouldReturnCorrectMessage() {
        String expectedMessage = "Player RED has no more moves to perform\nPlayer RED LOSES!!!";
        assertEquals(expectedMessage, messageProvider.getEliminationMessage(humanPlayer));
    }

    @Test
    void getEndMessageShouldReturnCorrectMessage() {
        String expectedMessage = "The match has ended. Thank you for playing!";
        assertEquals(expectedMessage, messageProvider.getEndMessage());
    }

    @Test
    void getInvalidChoiceMessageShouldReturnCorrectMessage() {
        String expectedMessage = "Invalid choice.";
        assertEquals(expectedMessage, messageProvider.getInvalidChoiceMessage());
    }

    @Test
    void getAskIfPlayerKnowsRulesMessageShouldReturnCorrectMessage() {
        String expectedMessage = "Do you know the rules of the game? (yes/no)";
        assertEquals(expectedMessage, messageProvider.getAskIfPlayerKnowsRulesMessage());
    }

    @Test
    void getRuleTypeChoiceMessageShouldReturnCorrectMessage() {
        String expectedMessage = "Choose the rule type:";
        assertEquals(expectedMessage, messageProvider.getRuleTypeChoiceMessage());
    }

    @Test
    void getTrackChoiceMessageShouldReturnCorrectMessage() {
        String expectedMessage = "Choose a track from the available tracks:";
        assertEquals(expectedMessage, messageProvider.getTrackChoiceMessage());
    }

    @Test
    void getMoveChoiceMessageShouldReturnCorrectMessage() {
        String expectedMessage = "Choose a move:";
        assertEquals(expectedMessage, messageProvider.getMoveChoiceMessage());
    }

    @Test
    void getAskToPlayAgainMessageShouldReturnCorrectMessage() {
        String expectedMessage = "Do you want to play again? (yes/no)";
        assertEquals(expectedMessage, messageProvider.getAskToPlayAgainMessage());
    }

    @Test
    void getAskIfSatisfiedWithConfigurationMessageShouldReturnCorrectMessage() {
        String expectedMessage = "Are you satisfied with the current configuration? (yes/no)";
        assertEquals(expectedMessage, messageProvider.getAskIfSatisfiedWithConfigurationMessage());
    }

    @Test
    void getAskToChooseForEachBotMessageShouldReturnCorrectMessage() {
        String expectedMessage = "Do you want to choose the strategy for each bot? (yes/no)";
        assertEquals(expectedMessage, messageProvider.getAskToChooseForEachBotMessage());
    }

    @Test
    void getCarColourChoiceMessageShouldReturnCorrectMessage() {
        String expectedMessage = "Choose a car color:";
        assertEquals(expectedMessage, messageProvider.getCarColourChoiceMessage());
    }

    @Test
    void getAskNumberOfHumanPlayersMessageShouldReturnCorrectMessage() {
        String expectedMessage = "Enter the number of human players(max 4):";
        assertEquals(expectedMessage, messageProvider.getAskNumberOfHumanPlayersMessage(4));
    }

    @Test
    void getChooseEachBotStrategyDifficultyMessageShouldReturnCorrectMessage() {
        String expectedMessage = "Choose the RED bot strategy difficulty:";
        assertEquals(expectedMessage, messageProvider.getChooseEachBotStrategyDifficultyMessage(CarColour.RED));
    }

    @Test
    void getChooseAllBotStrategyDifficultyMessageShouldReturnCorrectMessage() {
        String expectedMessage = "Choose the bot strategy difficulty:";
        assertEquals(expectedMessage, messageProvider.getChooseAllBotStrategyDifficultyMessage());
    }

    @Test
    void getChooseStartingPositionMessageShouldReturnCorrectMessage() {
        String expectedMessage = "RED, choose your starting position:";
        assertEquals(expectedMessage, messageProvider.getChooseStartingPositionMessage(CarColour.RED));
    }

    @Test
    void getNextTurnMessageShouldReturnCorrectMessage() {
        String expectedMessage = "Press enter to continue to the next turn...";
        assertEquals(expectedMessage, messageProvider.getNextTurnMessage());
    }
}
