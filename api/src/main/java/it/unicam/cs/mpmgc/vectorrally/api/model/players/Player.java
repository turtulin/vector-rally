package it.unicam.cs.mpmgc.vectorrally.api.model.players;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;

public interface Player {
    /**
     * Returns the player's speed
     *
     * @return the player's speed
     */
    Acceleration getSpeed();

    /**
     * Updates the player's speed
     *
     * @param newSpeed the new speed
     */
    void setSpeed(Acceleration newSpeed);

    /**
     * Returns the player's car color
     *
     * @return the player's car color
     */
    CarColour getPlayerCarColor();

    /**
     * Returns the player's current position
     *
     * @return the player's current position
     */
    Position getPosition();

    /**
     * Updates the player's position
     *
     * @param position the player's position
     */
    void setPosition(Position position);

    /**
     * Returns true if the player has crossed the starting line
     * (or in case of a closed racetrack, if the player has passed
     * the finishing line once)
     * Note: Only players that are racing can win a formula 1 game
     *
     * @return true if the player is racing
     *         otherwise false
     */
    boolean isRacing();

    /**
     * Updates the player's status to IS_RACING
     */
    void setIsRacing();

    /**
     * Returns the type of this player
     *
     * @return HUMAN or BOT
     */
    PlayerType getPlayerType();

}
