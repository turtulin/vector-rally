package it.unicam.cs.mpmgc.vectorrally.api.model.players;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;

/**
 * Abstract implementation of the {@link Player} interface. This class provides the basic
 * functionality for a player in the Vector Rally game, including management of the player's
 * car, position, and racing status.
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public abstract class DefaultPlayer implements Player {
    protected final Car playerCar;
    protected Position position;
    protected boolean isRacing;

    /**
     * Constructs a DefaultPlayer with the specified car.
     *
     * @param playerCar the car associated with the player.
     * @throws NullPointerException if the playerCar is null.
     */
    public DefaultPlayer(Car playerCar) {
        if (playerCar == null) throw new NullPointerException("Cannot create a player without a car");
        this.playerCar = playerCar;
        this.position = new Position(0, 0);
        this.isRacing = false;
    }

    @Override
    public Acceleration getPlayerAcceleration() {
        return this.playerCar.getAcceleration();
    }

    @Override
    public void setPlayerAcceleration(Acceleration acceleration) {
        if (acceleration == null) throw new NullPointerException("Player acceleration cannot be null");
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
        this.position = position;
    }

    @Override
    public boolean isRacing() {
        return this.isRacing;
    }

    @Override
    public void setRacing(boolean isRacing) {
        this.isRacing = isRacing;
    }
}
