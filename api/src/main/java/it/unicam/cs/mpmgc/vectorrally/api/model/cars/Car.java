package it.unicam.cs.mpmgc.vectorrally.api.model.cars;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;

public interface Car {
    /**
     * Returns the current speed the race car has reached
     *
     * @return the current speed the race car has reached
     *         in terms of a velocity vector
     */
    Acceleration getSpeed();

    /**
     * Updates the race car's speed
     *
     * @param newSpeed the new speed reached by the race car
     * @throws NullPointerException if newSpeed is null
     */
    void setSpeed(Acceleration newSpeed);

    /**
     * Returns the race car's color
     *
     * @return the race car's color
     */
    CarColour getCarColour();
}
