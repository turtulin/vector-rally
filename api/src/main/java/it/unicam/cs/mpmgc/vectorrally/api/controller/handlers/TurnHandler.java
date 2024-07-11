package it.unicam.cs.mpmgc.vectorrally.api.controller.handlers;

import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.GameRule;

/**
 * This interface represents a handler for managing turns in the vector rally game.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface TurnHandler {

    /**
     * Starts the turn for the current player.
     *
     * @param player the player whose turn is starting.
     */
    void startTurn(Player player);

    /**
     * Ends the turn for the current player.
     *
     * @param player the player whose turn is ending.
     */
    void endTurn(Player player);

    /**
     * Gets the next player whose turn is to be played.
     *
     * @return the next player.
     */
    Player getNextPlayer();
}
