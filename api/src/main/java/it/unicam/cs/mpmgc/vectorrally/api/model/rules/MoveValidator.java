package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

/**
 * This interface defines the contract for validating moves in the vector rally game.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface MoveValidator extends GameRule {

    /**
     * Checks if the move is valid for the given player.
     *
     * @param player the player making the move.
     * @param newPosition the new position the player is moving to.
     * @return true if the move is valid, false otherwise.
     */
    boolean isRespected(Player player, Position newPosition);
}
