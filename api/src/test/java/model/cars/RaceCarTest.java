package model.cars;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.RaceCar;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RaceCarTest {

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
        Acceleration initialAcceleration = new Acceleration(0, 0);
        assertTrue(compareAccelerations(initialAcceleration, raceCar.getAcceleration()));
    }

    @Test
    void setAccelerationShouldUpdateAccelerationCorrectly() {
        Acceleration newAcceleration = new Acceleration(5, 10);
        raceCar.setAcceleration(newAcceleration);

        assertTrue(compareAccelerations(newAcceleration, raceCar.getAcceleration()));
    }

    @Test
    void setAccelerationShouldThrowExceptionWhenAccelerationIsNull() {
        assertThrows(NullPointerException.class, () -> raceCar.setAcceleration(null));
    }

    @Test
    void getCarColourShouldReturnCorrectColour() {
        assertEquals(CarColour.RED, raceCar.getCarColour());
    }

    private boolean compareAccelerations(Acceleration a1, Acceleration a2) {
        return a1.getDx() == a2.getDx() && a1.getDy() == a2.getDy();
    }
}
