package model.players;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.RaceCar;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.BotPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BotPlayerTest {

    private BotPlayer botPlayer;
    private Car car;
    private BotStrategy strategy;

    @BeforeEach
    void setUp() {
        car = new RaceCar(CarColour.BLUE);
        strategy = BotStrategy.EASY;
        botPlayer = new BotPlayer(car, strategy);
    }

    @Test
    void constructorShouldThrowExceptionWhenCarIsNull() {
        assertThrows(NullPointerException.class, () -> new BotPlayer(null, strategy));
    }

    @Test
    void constructorShouldThrowExceptionWhenStrategyIsNull() {
        assertThrows(NullPointerException.class, () -> new BotPlayer(car, null));
    }

    @Test
    void getStrategyShouldReturnStrategy() {
        assertEquals(strategy, botPlayer.getStrategy());
    }
}
