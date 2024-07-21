package it.unicam.cs.mpmgc.vectorrally.api.model.players;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;

/**
 * Represents a player in the Vector Rally game. This interface provides essential functionalities
 * to manage a player's status in the game, including their position, acceleration, and car colour.
 * Additionally, it offers the capability to update the player's racing status and execute game moves.
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface Player {

    /**
     * Retrieves the current acceleration of the player.
     *
     * @return the current acceleration.
     */
    Acceleration getPlayerAcceleration();

    /**
     * Updates the player's acceleration.
     *
     * @param acceleration the new acceleration to be set.
     * @throws NullPointerException if the new acceleration is null, to ensure player always has valid data.
     */
    void setPlayerAcceleration(Acceleration acceleration);

    /**
     * Retrieves the colour of the player's car.
     *
     * @return the car colour.
     */
    CarColour getPlayerCarColour();

    /**
     * Retrieves the current position of the player on the game board.
     *
     * @return the current position.
     */
    Position getPosition();

    /**
     * Updates the player's position on the game board.
     *
     * @param position the new position to be set.
     * @throws NullPointerException if the position is null, ensuring all players have a valid location.
     */
    void setPosition(Position position);

    /**
     * Checks whether the player is currently participating in the race.
     *
     * @return true if the player is actively racing, false if not.
     */
    boolean isRacing();

    /**
     * Updates the racing status of the player.
     *
     * @param isRacing the new racing status of the player, true to set racing, false to stop.
     */
    void setRacing(boolean isRacing);
}
