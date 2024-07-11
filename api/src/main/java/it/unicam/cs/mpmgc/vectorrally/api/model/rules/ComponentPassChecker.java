package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Coordinates;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;

/**
 * This interface defines the contract for checking if a player passes through a specified component.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @autor Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface ComponentPassChecker {
    /**
     * Checks if a player passes through a specified component during their move.
     *
     * @param startPosition the starting position of the move.
     * @param endPosition the ending position of the move.
     * @param component the component to check for.
     * @return true if the player passes through the specified component, false otherwise.
     */
    boolean passesThroughComponent(Position startPosition, Position endPosition, TrackComponent component);
}
