package it.unicam.cs.mpmgc.vectorrally.api.model.cars;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;

/**
 * This class represents a race car in the vector rally game.
 * It implements the {@link Car} interface.
 *
 * @version 1.0
 * @since 2024-07-10
 */
public class RaceCar implements Car {

    private final Acceleration acceleration;

    private final CarColour carColour;

    /**
     * Constructs a RaceCar with the specified colour.
     *
     * @param carColour the colour of the car.
     * @throws NullPointerException if the carColour is null.
     */
    public RaceCar(CarColour carColour) {
        if (carColour == null) throw new NullPointerException("Cannot create a car without a colour");
        this.acceleration = new Acceleration(0,0);
        this.carColour = carColour;
    }

    @Override
    public Acceleration getAcceleration() {
        return acceleration;
    }

    @Override
    public void setAcceleration(Acceleration acceleration) {
        if (acceleration == null) throw new NullPointerException("Car acceleration cannot be null");
        this.acceleration.setDx(acceleration.getDx());
        this.acceleration.setDy(acceleration.getDy());
    }

    @Override
    public CarColour getCarColour() {
        return carColour;
    }
}
