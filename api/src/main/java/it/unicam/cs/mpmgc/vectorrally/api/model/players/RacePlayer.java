package it.unicam.cs.mpmgc.vectorrally.api.model.players;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;

public class RacePlayer implements Player {
    private final Car playerCar;

    private final Position currentPosition;

    private boolean racing;

    private final PlayerType playerType;

    public RacePlayer(Car playerCar, PlayerType playerType) {
        if (playerCar == null)
            throw new NullPointerException("Cannot assign null to a human player's race car");
        this.playerCar = playerCar;
        this.currentPosition = new Position(0,0);
        this.racing = false;
        this.playerType = playerType;
    }

    @Override
    public Acceleration getSpeed() {
        return this.playerCar.getSpeed();
    }

    @Override
    public void setSpeed(Acceleration newSpeed) {
        if (newSpeed == null)
            throw new NullPointerException("Cannot update a player's speed to null");
        this.playerCar.setSpeed(newSpeed);
    }

    @Override
    public CarColour getPlayerCarColor() {
        return this.playerCar.getCarColour();
    }

    @Override
    public Position getPosition() {
        return this.currentPosition;
    }

    @Override
    public void setPosition(Position position) {
        if (position == null)
            throw new NullPointerException("Cannot update a player's position to null");
        this.currentPosition.setX(position.getX());
        this.currentPosition.setY(position.getY());
    }

    @Override
    public boolean isRacing() {
        return this.racing;
    }

    @Override
    public void setIsRacing() {
        this.racing = true;
    }

    @Override
    public PlayerType getPlayerType() {
        return playerType;
    }

}
