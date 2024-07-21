package it.unicam.cs.mpmgc.vectorrally.api.controller.setup;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.AStar;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory class for creating different bot strategies based on the given difficulty level.
 * It initializes the strategies and provides a method to retrieve them.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class BotStrategyFactory {
    private final Map<BotStrategy, DecisionStrategy> strategyMap = new HashMap<>();

    /**
     * Constructs a BotStrategyFactory with the specified neighbors' generator.
     * Initializes the strategies for EASY, MEDIUM, and HARD difficulties.
     *
     * @param neighborsGenerator the generator used to produce possible accelerations for the A* algorithm.
     */
    public BotStrategyFactory(NeighborsGenerator neighborsGenerator) {
        strategyMap.put(BotStrategy.EASY, new EasyBotStrategy());
        strategyMap.put(BotStrategy.MEDIUM, new MediumBotStrategy());
        strategyMap.put(BotStrategy.HARD, new HardBotStrategy(
                new AStar(AStar::calculateHeuristic, neighborsGenerator)
        ));
    }

    /**
     * Retrieves the decision strategy for the specified bot strategy.
     *
     * @param botStrategy the bot strategy for which to retrieve the decision strategy.
     * @return the corresponding decision strategy.
     */
    public DecisionStrategy getStrategy(BotStrategy botStrategy) {
        return strategyMap.get(botStrategy);
    }
}
