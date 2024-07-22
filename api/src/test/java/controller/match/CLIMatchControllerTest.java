package controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.view.TerminalIOController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CLIMatchControllerTest {

    private TerminalIOController ioController;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ioController = new TerminalIOController();
    }

    @Test
    void askIfPlayerKnowsRules_ShouldReturnYes_WhenInputIsYes() {
        String input = "yes\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ioController = new TerminalIOController();

        boolean result = ioController.askIfPlayerKnowsRules();

        assertTrue(result);
    }

    @Test
    void askIfPlayerKnowsRules_ShouldReturnNo_WhenInputIsNo() {
        String input = "no\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ioController = new TerminalIOController();

        boolean result = ioController.askIfPlayerKnowsRules();

        assertFalse(result);
    }

    @Test
    void askNumberOfHumanPlayers_ShouldReturnValidNumber_WhenInputIsValid() {
        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ioController = new TerminalIOController();

        int result = ioController.askNumberOfHumanPlayers(4);

        assertEquals(2, result);
    }

    @Test
    void chooseCarColor_ShouldReturnChosenColor_WhenInputIsValid() {
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ioController = new TerminalIOController();
        List<CarColour> availableColors = List.of(CarColour.RED, CarColour.BLUE);

        CarColour result = ioController.chooseCarColor(availableColors);

        assertEquals(CarColour.RED, result);
    }

    @Test
    void askToChooseForEachBot_ShouldReturnTrue_WhenInputIsYes() {
        String input = "yes\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ioController = new TerminalIOController();

        boolean result = ioController.askToChooseForEachBot();

        assertTrue(result);
    }

    @Test
    void askToChooseForEachBot_ShouldReturnFalse_WhenInputIsNo() {
        String input = "no\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ioController = new TerminalIOController();

        boolean result = ioController.askToChooseForEachBot();

        assertFalse(result);
    }

    @Test
    void chooseMove_ShouldReturnChosenMoveIndex_WhenInputIsValid() {
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ioController = new TerminalIOController();
        Move move1 = new Move(new Acceleration(1, 1), new Position(1, 1));
        Move move2 = new Move(new Acceleration(2, 2), new Position(2, 2));
        List<Move> possibleMoves = List.of(move1, move2);

        int result = ioController.chooseMove(possibleMoves);

        assertEquals(0, result);
    }

    @Test
    void chooseStartingPosition_ShouldReturnChosenPosition_WhenInputIsValid() {
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ioController = new TerminalIOController();
        Player player = mock(Player.class);
        when(player.getPlayerCarColour()).thenReturn(CarColour.RED);
        Position pos1 = new Position(0, 0);
        Position pos2 = new Position(1, 1);
        List<Position> availablePositions = List.of(pos1, pos2);

        Position result = ioController.chooseStartingPosition(player, availablePositions);

        assertEquals(pos1, result);
    }

    @Test
    void askToPlayAnotherMatch_ShouldReturnTrue_WhenInputIsYes() {
        String input = "yes\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ioController = new TerminalIOController();

        boolean result = ioController.askToPlayAnotherMatch();

        assertTrue(result);
    }

    @Test
    void askToPlayAnotherMatch_ShouldReturnFalse_WhenInputIsNo() {
        String input = "no\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ioController = new TerminalIOController();

        boolean result = ioController.askToPlayAnotherMatch();

        assertFalse(result);
    }

    @Test
    void displayMessage_ShouldPrintMessage() {
        ioController.displayMessage("Hello, world!");
        assertTrue(outContent.toString().contains("Hello, world!"));
    }

}
