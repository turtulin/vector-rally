package it.unicam.cs.mpmgc.vectorrally.api.model.players;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;

/**
 * Represents a bot player in the Vector Rally game. This class extends {@link DefaultPlayer}
 * and includes a strategy for the bot to make decisions during the game.
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class BotPlayer extends DefaultPlayer {
    private final BotStrategy strategy;

    /**
     * Constructs a BotPlayer with the specified car and strategy.
     *
     * @param playerCar the car associated with the player.
     * @param strategy the strategy that the bot will use to make decisions.
     * @throws NullPointerException if the playerCar or strategy is null.
     */
    public BotPlayer(Car playerCar, BotStrategy strategy) {
        super(playerCar);
        if (strategy == null) throw new NullPointerException("Strategy cannot be null");
        this.strategy = strategy;
    }

    /**
     * Retrieves the strategy used by the bot to make decisions.
     *
     * @return the strategy used by the bot.
     */
    public BotStrategy getStrategy() {
        return strategy;
    }
}
