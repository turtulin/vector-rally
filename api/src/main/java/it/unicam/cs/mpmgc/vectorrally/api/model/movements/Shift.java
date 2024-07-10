package it.unicam.cs.mpmgc.vectorrally.api.model.movements;

/**
 * This interface represents the shift from one position to another with a certain acceleration.
 * It is immutable.
 *
 * @version 1.0
 * @since 2024-07-10
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface Shift {

    /**
     * Gets the acceleration of the shift.
     *
     * @return the acceleration.
     */
    Acceleration acceleration();

    /**
     * Gets the position of the shift.
     *
     * @return the position.
     */
    Coordinates position();
}
