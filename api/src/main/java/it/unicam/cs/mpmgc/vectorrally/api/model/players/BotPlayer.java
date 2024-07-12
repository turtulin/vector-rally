package it.unicam.cs.mpmgc.vectorrally.api.model.players;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.DecisionStrategy;

public class BotPlayer extends DefaultPlayer {
    private final DecisionStrategy strategy;

    public BotPlayer(Car playerCar, DecisionStrategy strategy) {
        super(playerCar);
        if (strategy == null) throw new NullPointerException("Strategy cannot be null");
        this.strategy = strategy;
    }

    public DecisionStrategy getStrategy() {
        return strategy;
    }
}
