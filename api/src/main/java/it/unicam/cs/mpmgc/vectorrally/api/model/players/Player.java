package it.unicam.cs.mpmgc.vectorrally.api.model.players;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.*;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;

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
     * @return the current {@link Vector} representing the player's acceleration.
     */
    Vector getPlayerAcceleration();

    /**
     * Updates the player's acceleration.
     *
     * @param acceleration the new {@link Vector} representing the player's acceleration.
     * @throws NullPointerException if the new acceleration is {@code null}, to ensure the player always has valid data.
     */
    void setPlayerAcceleration(Vector acceleration);

    /**
     * Retrieves the colour of the player's car.
     *
     * @return the {@link CarColour} of the player's car.
     */
    CarColour getPlayerCarColour();

    /**
     * Retrieves the current position of the player on the game board.
     *
     * @return the current {@link Coordinates} representing the player's position.
     */
    Coordinates getPosition();

    /**
     * Updates the player's position on the game board.
     *
     * @param position the new {@link Coordinates} to be set as the player's position.
     * @throws NullPointerException if the position is {@code null}, ensuring all players have a valid location.
     */
    void setPosition(Coordinates position);

    /**
     * Checks whether the player is currently participating in the race.
     *
     * @return {@code true} if the player is actively racing, {@code false} if not.
     */
    boolean isRacing();

    /**
     * Updates the racing status of the player.
     *
     * @param isRacing the new racing status of the player, {@code true} to set racing, {@code false} to stop.
     */
    void setRacing(boolean isRacing);

    /**
     * Retrieves the name of the player.
     *
     * @return the name of the player.
     */
    String getName();

    /**
     * Executes a move in the game, updating the player's position and acceleration.
     *
     * @param move the {@link Move} to be executed by the player.
     */
    void makeMove(Move move);
}
