package it.unicam.cs.mpmgc.vectorrally.api.model.players;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.DecisionStrategy;

public class BotPlayer extends DefaultPlayer {
    private final BotStrategy strategy;

    public BotPlayer(Car playerCar, BotStrategy strategy) {
        super(playerCar);
        if (strategy == null) throw new NullPointerException("Strategy cannot be null");
        this.strategy = strategy;
    }

    public BotStrategy getStrategy() {
        return strategy;
    }
}
