package it.unicam.cs.mpmgc.vectorrally.api.controller.handlers;

import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * This class implements the TurnHandler interface, managing turns in the vector rally game.
 *
 * @version 1.0
 * @since 2024-07-11
 */

public class BasicTurnHandler implements TurnHandler {
    private final Queue<Player> playerQueue;

    /**
     * Constructs a BasicTurnHandler with the given list of players.
     *
     * @param players the list of players in the game.
     */
    public BasicTurnHandler(List<Player> players) {
        this.playerQueue = new LinkedList<>(players);
    }


    @Override
    public void startTurn(Player player) {

    }

    @Override
    public void endTurn(Player player) {

    }

    @Override
    public Player getNextPlayer() {
        return null;
    }
}
