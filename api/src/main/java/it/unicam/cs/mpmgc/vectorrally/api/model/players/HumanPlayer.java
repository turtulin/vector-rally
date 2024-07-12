package it.unicam.cs.mpmgc.vectorrally.api.model.players;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;

/**
 * This class represents a human player in the vector rally game.
 * It extends the {@link DefaultPlayer} class.
 *
 * @version 1.0
 * @since 2024-07-10
 */
public class HumanPlayer extends DefaultPlayer {

    /**
     * Constructs a HumanPlayer with the specified car.
     *
     * @param playerCar the car associated with the player.
     * @throws NullPointerException if the playerCar is null.
     */
    public HumanPlayer(Car playerCar) {
        super(playerCar);
    }
}
