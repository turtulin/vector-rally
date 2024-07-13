package it.unicam.cs.mpmgc.vectorrally.api.controller.setup;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.AStar;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.*;

import java.util.HashMap;
import java.util.Map;

public class BotStrategyFactory {
    private final Map<BotStrategy, DecisionStrategy> strategyMap = new HashMap<>();

    public BotStrategyFactory(AStar aStarAlgorithm) {
        strategyMap.put(BotStrategy.EASY, new EasyBotStrategy());
        strategyMap.put(BotStrategy.MEDIUM, new MediumBotStrategy());
        strategyMap.put(BotStrategy.HARD, new HardBotStrategy(aStarAlgorithm));
    }

    public DecisionStrategy getStrategy(BotStrategy botStrategy) {
        return strategyMap.get(botStrategy);
    }
}
