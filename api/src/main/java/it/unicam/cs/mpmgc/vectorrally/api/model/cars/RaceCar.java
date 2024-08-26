package it.unicam.cs.mpmgc.vectorrally.api.model.cars;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Vector;

/**
 * Represents a race car in the Vector Rally game. This class implements the {@link Car} interface
 * and provides the necessary functionality to manage the car's acceleration and colour.
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class RaceCar implements Car {
    private final Vector acceleration;
    private final CarColour carColour;

    public RaceCar(CarColour carColour) {
        if(carColour == null) throw new NullPointerException("Cannot create a car without a colour");
        this.acceleration = new Acceleration(0,0);
        this.carColour = carColour;
    }

    @Override
    public Vector getAcceleration() {
        return acceleration;
    }

    @Override
    public void setAcceleration(Vector acceleration) {
        if(acceleration == null) throw new NullPointerException("Car acceleration cannot be null");
        this.acceleration.setDx(acceleration.getDx());
        this.acceleration.setDy(acceleration.getDy());
    }

    @Override
    public CarColour getCarColour() {
        return carColour;
    }
}
