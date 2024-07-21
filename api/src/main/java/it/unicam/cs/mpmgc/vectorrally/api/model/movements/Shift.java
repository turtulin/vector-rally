package it.unicam.cs.mpmgc.vectorrally.api.model.movements;

/**
 * Represents the shift from one position to another with a certain acceleration.
 * This interface is immutable, meaning the state of an instance cannot be modified after it is created.
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface Shift {

    /**
     * Retrieves the acceleration of the shift.
     *
     * @return the acceleration.
     */
    Acceleration acceleration();

    /**
     * Retrieves the position of the shift.
     *
     * @return the position.
     */
    Coordinates position();
}
