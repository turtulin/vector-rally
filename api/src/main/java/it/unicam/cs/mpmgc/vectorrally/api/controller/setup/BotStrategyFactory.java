package it.unicam.cs.mpmgc.vectorrally.api.controller.setup;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.AStar;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.*;

import java.util.HashMap;
import java.util.Map;

public class BotStrategyFactory {
    private final Map<BotStrategy, DecisionStrategy> strategyMap = new HashMap<>();

    public BotStrategyFactory(NeighborsGenerator neighborsGenerator) {
        strategyMap.put(BotStrategy.EASY, new EasyBotStrategy());
        strategyMap.put(BotStrategy.MEDIUM, new MediumBotStrategy());
        strategyMap.put(BotStrategy.HARD, new HardBotStrategy(
                new AStar(AStar::calculateHeuristic, neighborsGenerator)
        ));
    }

    public DecisionStrategy getStrategy(BotStrategy botStrategy) {
        return strategyMap.get(botStrategy);
    }
}
