package it.unicam.cs.mpmgc.vectorrally.api.model.players;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Coordinates;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Vector;

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
    protected final String name;
    protected final Car playerCar;
    protected Coordinates position;
    protected boolean isRacing;

    public DefaultPlayer(Car playerCar) {
        if(playerCar == null) throw new NullPointerException("Cannot create a player without a car");
        this.name = playerCar.getCarColour().toString();
        this.playerCar = playerCar;
        this.position = new Position(0, 0);
        this.isRacing = false;
    }

    @Override
    public Vector getPlayerAcceleration() {
        return this.playerCar.getAcceleration();
    }

    @Override
    public void setPlayerAcceleration(Vector acceleration) {
        if(acceleration == null) throw new NullPointerException("Player acceleration cannot be null");
        this.playerCar.setAcceleration(acceleration);
    }

    @Override
    public CarColour getPlayerCarColour() {
        return this.playerCar.getCarColour();
    }

    @Override
    public Coordinates getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Coordinates position) {
        if(position == null) throw new NullPointerException("Player position cannot be null");
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

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void makeMove(Move move) {
        this.position = move.getDestination();
        this.playerCar.setAcceleration(move.acceleration());
    }
}
