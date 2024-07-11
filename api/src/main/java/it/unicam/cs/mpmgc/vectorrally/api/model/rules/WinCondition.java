package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Direction;

/**
 * This interface defines the contract for checking winning conditions in the vector rally game.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface WinCondition extends GameRule {
    /**
     * Checks if the direction of movement is valid.
     *
     * @param direction the direction to check.
     * @return true if the direction is valid, false otherwise.
     */
    boolean isValidDirection(Direction direction);
}
