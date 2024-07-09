package it.unicam.cs.mpmgc.vectorrally.api.model.cars;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.ShiftVector;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Vector;

public class RaceCar implements Car {

    private final Vector speedometer;

    private final CarColour carColour;

    public RaceCar(CarColour carColour) {
        this.speedometer = new ShiftVector(0,0);
        this.carColour = carColour;
    }

    @Override
    public Vector getSpeed() {
        return speedometer;
    }

    @Override
    public void setSpeed(Vector newSpeed) {
        if (newSpeed == null)
            throw new NullPointerException("New speed is null");
        this.speedometer.setX(newSpeed.getX());
        this.speedometer.setY(newSpeed.getY());
    }

    @Override
    public CarColour getCarColour() {
        return carColour;
    }
}
