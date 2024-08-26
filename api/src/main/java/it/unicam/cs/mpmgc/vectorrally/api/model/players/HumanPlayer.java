package it.unicam.cs.mpmgc.vectorrally.api.model.players;


import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;

/**
 * Represents a human player in the Vector Rally game. This class extends {@link DefaultPlayer}
 * and inherits its basic functionalities while specifying the player as a human.
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class HumanPlayer extends DefaultPlayer {
    public HumanPlayer(Car playerCar) {
        super(playerCar);
    }
}
