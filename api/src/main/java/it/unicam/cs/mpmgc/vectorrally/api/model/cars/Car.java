package it.unicam.cs.mpmgc.vectorrally.api.model.cars;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;

/**
 * Represents a car in the Vector Rally game. This interface provides methods to manage
 * the car's acceleration and retrieve its colour.
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface Car {

    /**
     * Retrieves the current acceleration of the car.
     *
     * @return the current acceleration.
     */
    Acceleration getAcceleration();

    /**
     * Updates the acceleration of the car.
     *
     * @param acceleration the new acceleration to be set.
     * @throws NullPointerException if the new acceleration is null.
     */
    void setAcceleration(Acceleration acceleration);

    /**
     * Retrieves the colour of the car.
     *
     * @return the car colour.
     */
    CarColour getCarColour();
}
