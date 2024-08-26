package controller;

import it.unicam.cs.mpmgc.vectorrally.api.controller.match.BasicTurnHandler;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BasicTurnHandlerTest {
    private BasicTurnHandler turnHandler;
    private Player player1;
    private Player player2;
    private Player player3;

    @BeforeEach
    public void setUp() {
        player1 = mock(Player.class);
        player2 = mock(Player.class);
        player3 = mock(Player.class);
        List<Player> players = Arrays.asList(player1, player2, player3);
        turnHandler = new BasicTurnHandler(players);
    }

    @Test
    public void shouldStartWithFirstPlayer() {
        assertEquals(player1, turnHandler.getCurrentPlayer());
    }

    @Test
    public void shouldCycleToNextPlayerAfterTurnEnds() {
        turnHandler.endTurn();
        assertEquals(player2, turnHandler.getCurrentPlayer());
        turnHandler.endTurn();
        assertEquals(player3, turnHandler.getCurrentPlayer());
        turnHandler.endTurn();
        assertEquals(player1, turnHandler.getCurrentPlayer());
    }

    @Test
    public void shouldRemovePlayerFromTurnOrder() {
        turnHandler.removePlayer(player2);
        assertEquals(2, turnHandler.getPlayers().size());
        assertFalse(turnHandler.getPlayers().contains(player2));
    }

    @Test
    public void shouldNotIncrementTurnCounterUntilAllPlayersHadTurn() {
        turnHandler.endTurn();
        assertEquals(1, turnHandler.getTurnCounter());
        turnHandler.endTurn();
        assertEquals(1, turnHandler.getTurnCounter());
    }
}
