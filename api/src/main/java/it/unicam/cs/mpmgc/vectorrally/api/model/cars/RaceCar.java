package it.unicam.cs.mpmgc.vectorrally.api.model.cars;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;

public class RaceCar implements Car {

    private final Acceleration speedometer;

    private final CarColour carColour;

    public RaceCar(CarColour carColour) {
        this.speedometer = new Acceleration(0,0);
        this.carColour = carColour;
    }

    @Override
    public Acceleration getSpeed() {
        return speedometer;
    }

    @Override
    public void setSpeed(Acceleration newSpeed) {
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
