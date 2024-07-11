package it.unicam.cs.mpmgc.vectorrally.api.controller.handlers;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

/**
 * This interface represents a handler for move validation and processing in the vector rally game.
 * Uses Chain of Responsibility Pattern.
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface MoveHandler {
    /**
     * Handles the move for the given player.
     *
     * @param player the player making the move.
     * @param newPosition the new position the player is moving to.
     * @return true if the move is valid and processed, false otherwise.
     */
    boolean handleMove(Player player, Position newPosition);
}
