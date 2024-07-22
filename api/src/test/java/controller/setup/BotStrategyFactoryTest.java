package controller.setup;

import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.BotStrategyFactory;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.DecisionStrategy;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.EasyBotStrategy;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.HardBotStrategy;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.MediumBotStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BotStrategyFactoryTest {

    private BotStrategyFactory factory;
    private NeighborsGenerator neighborsGenerator;

    @BeforeEach
    void setUp() {
        neighborsGenerator = new NeighborsGenerator() {
            @Override
            public List<Acceleration> generateShifts(Acceleration speed) {
                return List.of(new Acceleration(1, 0), new Acceleration(0, 1));
            }
        };
        factory = new BotStrategyFactory(neighborsGenerator);
    }

    @Test
    void getStrategyShouldReturnEasyBotStrategy() {
        DecisionStrategy strategy = factory.getStrategy(BotStrategy.EASY);
        assertTrue(strategy instanceof EasyBotStrategy);
    }

    @Test
    void getStrategyShouldReturnMediumBotStrategy() {
        DecisionStrategy strategy = factory.getStrategy(BotStrategy.MEDIUM);
        assertTrue(strategy instanceof MediumBotStrategy);
    }

    @Test
    void getStrategyShouldReturnHardBotStrategy() {
        DecisionStrategy strategy = factory.getStrategy(BotStrategy.HARD);
        assertTrue(strategy instanceof HardBotStrategy);
    }

    @Test
    void getStrategyShouldReturnNullForUnknownBotStrategy() {
        DecisionStrategy strategy = factory.getStrategy(null);
        assertNull(strategy);
    }
}
