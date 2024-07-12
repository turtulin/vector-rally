package it.unicam.cs.mpmgc.vectorrally.api.model.players;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;

/**
 * This interface represents a player in the vector rally game.
 * It provides methods to get and set the player's acceleration, position, and racing status.
 * It also includes methods to get the type of player (BOT or HUMAN) and the colour of
 * the player's car.
 *
 * @version 1.0
 * @since 2024-07-10
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */

public interface Player {

    /**
     * Gets the current acceleration of the player.
     *
     * @return the current acceleration.
     */
    Acceleration getPlayerAcceleration();

    /**
     * Sets the acceleration of the player.
     *
     * @param acceleration the new acceleration.
     * @throws NullPointerException if the new acceleration is null.
     */
    void setPlayerAcceleration(Acceleration acceleration);

    /**
     * Gets the colour of the player's car.
     *
     * @return the car colour.
     */
    CarColour getPlayerCarColour();

    /**
     * Gets the current position of the player.
     *
     * @return the current position.
     */
    Position getPosition();

    /**
     * Sets the position of the player.
     *
     * @param position the new position.
     * @throws NullPointerException if the position is null.
     */
    void setPosition(Position position);

    /**
     * Checks if the player is currently racing.
     *
     * @return true if the player is racing, false otherwise.
     */
    boolean isRacing();

    /**
     * Sets the racing status of the player.
     *
     * @param isRacing the new racing status of the player.
     */
    void setRacing(boolean isRacing);
}
