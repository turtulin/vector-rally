package model.movements;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccelerationTest {

    private Acceleration acceleration;

    @BeforeEach
    void setUp() {
        acceleration = new Acceleration(3, 4);
    }

    @Test
    void accelerationShouldInitializeDxAndDyCorrectly() {
        assertEquals(3, acceleration.getDx());
        assertEquals(4, acceleration.getDy());
    }

    @Test
    void getDxShouldReturnCorrectValue() {
        assertEquals(3, acceleration.getDx());
    }

    @Test
    void getDyShouldReturnCorrectValue() {
        assertEquals(4, acceleration.getDy());
    }

    @Test
    void setDxShouldUpdateDxCorrectly() {
        acceleration.setDx(5);
        assertEquals(5, acceleration.getDx());
    }

    @Test
    void setDyShouldUpdateDyCorrectly() {
        acceleration.setDy(6);
        assertEquals(6, acceleration.getDy());
    }

    @Test
    void getMagnitudeShouldReturnCorrectValue() {
        assertEquals(5.0, acceleration.getMagnitude());
    }

    @Test
    void getDirectionShouldReturnCorrectDirection() {
        assertEquals(Direction.UP_RIGHT, acceleration.getDirection());

        acceleration.setDx(-3);
        acceleration.setDy(4);
        assertEquals(Direction.UP_LEFT, acceleration.getDirection());

        acceleration.setDx(3);
        acceleration.setDy(-4);
        assertEquals(Direction.DOWN_RIGHT, acceleration.getDirection());

        acceleration.setDx(-3);
        acceleration.setDy(-4);
        assertEquals(Direction.DOWN_LEFT, acceleration.getDirection());

        acceleration.setDx(0);
        acceleration.setDy(0);
        assertEquals(Direction.NONE, acceleration.getDirection());

        acceleration.setDx(0);
        acceleration.setDy(4);
        assertEquals(Direction.UP, acceleration.getDirection());

        acceleration.setDx(4);
        acceleration.setDy(0);
        assertEquals(Direction.RIGHT, acceleration.getDirection());

        acceleration.setDx(0);
        acceleration.setDy(-4);
        assertEquals(Direction.DOWN, acceleration.getDirection());

        acceleration.setDx(-4);
        acceleration.setDy(0);
        assertEquals(Direction.LEFT, acceleration.getDirection());
    }
}
