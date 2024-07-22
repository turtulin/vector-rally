package controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.match.CLIGameEngine;
import it.unicam.cs.mpmgc.vectorrally.api.view.IOController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class CLIGameEngineTest {

    private CLIGameEngine gameEngine;
    private IOController ioController;

    @BeforeEach
    void setUp() {
        ioController = mock(IOController.class);
        gameEngine = new CLIGameEngine(ioController) {
        };
    }

    @Test
    void endMatchShouldDisplayEndMessageAndAskToPlayAnotherMatch() {
        when(ioController.askToPlayAnotherMatch()).thenReturn(false);
        boolean result = gameEngine.endMatch();
        verify(ioController, times(1)).displayEndMatchMessage();
        verify(ioController, times(1)).askToPlayAnotherMatch();
        assertFalse(result);
    }
}
