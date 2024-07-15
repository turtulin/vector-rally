package it.unicam.cs.mpmgc.vectorrally.api.controller.setup;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.AStarManhattan;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.*;

import java.util.HashMap;
import java.util.Map;

public class BotStrategyFactory {
    private final Map<BotStrategy, DecisionStrategy> strategyMap = new HashMap<>();

    public BotStrategyFactory() {
        strategyMap.put(BotStrategy.EASY, new EasyBotStrategy());
        strategyMap.put(BotStrategy.MEDIUM, new MediumBotStrategy());
        strategyMap.put(BotStrategy.HARD, new HardBotStrategy(new AStarManhattan()));
    }

    public DecisionStrategy getStrategy(BotStrategy botStrategy) {
        return strategyMap.get(botStrategy);
    }
}
