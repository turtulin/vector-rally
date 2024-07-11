package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;

/**
 * This interface defines the contract for validating moves in the vector rally game.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface MoveValidator extends Rule {

    /**
     * Validates a player's move.
     *
     * @param move the move to be validated.
     * @return true if the move is valid, false otherwise.
     */
    boolean validateMove(Move move);
}
