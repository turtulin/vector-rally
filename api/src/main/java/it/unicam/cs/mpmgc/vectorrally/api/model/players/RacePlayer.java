package it.unicam.cs.mpmgc.vectorrally.api.model.players;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;

/**
 * This class represents a player in the vector rally game.
 * It implements the {@link Player} interface.
 *
 * @version 1.0
 * @since 2024-07-10
 */
public class RacePlayer implements Player {
    private final Car playerCar;

    private final Position position;

    private boolean isRacing;

    private final PlayerType playerType;

    /**
     * Constructs a RacePlayer with the specified car and player type.
     *
     * @param playerCar the car associated with the player.
     * @param playerType the type of the player (HUMAN or BOT).
     * @throws NullPointerException if the playerCar or playerType is null.
     */
    public RacePlayer(Car playerCar, PlayerType playerType) {
        if (playerCar == null) throw new NullPointerException("Cannot create a player without a car");
        if (playerType == null) throw new NullPointerException("Cannot create a player without a player type.");
        this.playerCar = playerCar;
        this.position = new Position(0,0);
        this.isRacing = false;
        this.playerType = playerType;
    }

    @Override
    public Acceleration getPlayerAcceleration() {
        return this.playerCar.getAcceleration();
    }

    @Override
    public void setPlayerAcceleration(Acceleration acceleration) {
        if (acceleration == null) throw new NullPointerException("Player speed cannot be null");
        this.playerCar.setAcceleration(acceleration);
    }

    @Override
    public CarColour getPlayerCarColour() {
        return this.playerCar.getCarColour();
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position position) {
        if (position == null) throw new NullPointerException("Player position cannot be null");
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    @Override
    public boolean isRacing() {
        return this.isRacing;
    }

    @Override
    public void setRacing(boolean IsRacing) {
        this.isRacing = IsRacing;
    }

    @Override
    public PlayerType getPlayerType() {
        return playerType;
    }

}
