package model.cars;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.RaceCar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;

public class RaceCarTest {

    private RaceCar raceCar;

    @BeforeEach
    void setUp() {
        raceCar = new RaceCar(CarColour.RED);
    }

    @Test
    void constructorShouldThrowExceptionWhenCarColourIsNull() {
        assertThrows(NullPointerException.class, () -> new RaceCar(null));
    }

    @Test
    void getAccelerationShouldReturnInitialAcceleration() {
        assertEquals(new Acceleration(0, 0), raceCar.getAcceleration());
    }

    @Test
    void setAccelerationShouldThrowExceptionWhenAccelerationIsNull() {
        assertThrows(NullPointerException.class, () -> raceCar.setAcceleration(null));
    }

    @Test
    void setAccelerationShouldUpdateAcceleration() {
        Acceleration newAcceleration = new Acceleration(3, 4);
        raceCar.setAcceleration(newAcceleration);
        assertEquals(newAcceleration, raceCar.getAcceleration());
    }

    @Test
    void getCarColourShouldReturnCarColour() {
        assertEquals(CarColour.RED, raceCar.getCarColour());
    }
}

