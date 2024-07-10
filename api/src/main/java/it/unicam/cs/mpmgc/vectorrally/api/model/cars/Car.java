package it.unicam.cs.mpmgc.vectorrally.api.model.cars;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;

/**
 * This interface represents a car in the vector rally game.
 * It provides methods to get and set the car's speed and get the car's colour.
 *
 * @version 1.0
 * @since 2024-07-10
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface Car {

    /**
     * Gets the current acceleration of the car.
     *
     * @return the current acceleration.
     */
    Acceleration getAcceleration();

    /**
     * Sets the acceleration of the car.
     *
     * @param acceleration the new acceleration.
     */
    void setAcceleration(Acceleration acceleration);

    /**
     * Gets the colour of the car.
     *
     * @return the car colour.
     */
    CarColour getCarColour();
}
