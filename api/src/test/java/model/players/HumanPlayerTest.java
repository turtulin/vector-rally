package model.players;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.RaceCar;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.HumanPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;

public class HumanPlayerTest {

    private HumanPlayer humanPlayer;
    private Car car;

    @BeforeEach
    void setUp() {
        car = new RaceCar(CarColour.GREEN);
        humanPlayer = new HumanPlayer(car);
    }

    @Test
    void constructorShouldThrowExceptionWhenCarIsNull() {
        assertThrows(NullPointerException.class, () -> new HumanPlayer(null));
    }
}

