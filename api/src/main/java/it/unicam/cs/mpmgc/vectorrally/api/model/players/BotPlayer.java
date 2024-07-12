package it.unicam.cs.mpmgc.vectorrally.api.model.players;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategyDifficulty;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.DecisionStrategy;

public class BotPlayer extends DefaultPlayer {
    private final BotStrategyDifficulty strategy;

    public BotPlayer(Car playerCar, BotStrategyDifficulty strategy) {
        super(playerCar);
        if (strategy == null) throw new NullPointerException("Strategy cannot be null");
        this.strategy = strategy;
    }

    public BotStrategyDifficulty getStrategy() {
        return strategy;
    }
}
