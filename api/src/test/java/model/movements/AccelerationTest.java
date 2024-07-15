package model.movements;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Direction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccelerationTest {

    @Test
    void constructorShouldInitializeDxAndDy() {
        Acceleration acceleration = new Acceleration(3, 4);
        assertEquals(3, acceleration.getDx());
        assertEquals(4, acceleration.getDy());
    }

    @Test
    void setDxShouldUpdateDx() {
        Acceleration acceleration = new Acceleration(3, 4);
        acceleration.setDx(5);
        assertEquals(5, acceleration.getDx());
    }

    @Test
    void setDyShouldUpdateDy() {
        Acceleration acceleration = new Acceleration(3, 4);
        acceleration.setDy(6);
        assertEquals(6, acceleration.getDy());
    }

    @Test
    void getMagnitudeShouldReturnCorrectMagnitude() {
        Acceleration acceleration = new Acceleration(3, 4);
        assertEquals(5.0, acceleration.getMagnitude(), 0.001);
    }

    @Test
    void getDirectionShouldReturnCorrectDirection() {
        Acceleration accelerationUp = new Acceleration(0, 1);
        assertEquals(Direction.UP, accelerationUp.getDirection());

        Acceleration accelerationUpRight = new Acceleration(1, 1);
        assertEquals(Direction.UP_RIGHT, accelerationUpRight.getDirection());

        Acceleration accelerationDownRight = new Acceleration(1, -1);
        assertEquals(Direction.DOWN_RIGHT, accelerationDownRight.getDirection());

        Acceleration accelerationDown = new Acceleration(0, -1);
        assertEquals(Direction.DOWN, accelerationDown.getDirection());

        Acceleration accelerationDownLeft = new Acceleration(-1, -1);
        assertEquals(Direction.DOWN_LEFT, accelerationDownLeft.getDirection());

        Acceleration accelerationLeft = new Acceleration(-1, 0);
        assertEquals(Direction.LEFT, accelerationLeft.getDirection());

        Acceleration accelerationUpLeft = new Acceleration(-1, 1);
        assertEquals(Direction.UP_LEFT, accelerationUpLeft.getDirection());

        Acceleration accelerationRight = new Acceleration(1, 0);
        assertEquals(Direction.RIGHT, accelerationRight.getDirection());

        Acceleration accelerationNone = new Acceleration(0, 0);
        assertEquals(Direction.NONE, accelerationNone.getDirection());
    }

    @Test
    void equalsShouldReturnTrueForEqualAccelerations() {
        Acceleration acceleration1 = new Acceleration(3, 4);
        Acceleration acceleration2 = new Acceleration(3, 4);
        assertEquals(acceleration1, acceleration2);
    }

    @Test
    void equalsShouldReturnFalseForDifferentAccelerations() {
        Acceleration acceleration1 = new Acceleration(3, 4);
        Acceleration acceleration2 = new Acceleration(4, 3);
        assertNotEquals(acceleration1, acceleration2);
    }

    @Test
    void hashCodeShouldReturnSameHashCodeForEqualAccelerations() {
        Acceleration acceleration1 = new Acceleration(3, 4);
        Acceleration acceleration2 = new Acceleration(3, 4);
        assertEquals(acceleration1.hashCode(), acceleration2.hashCode());
    }

    @Test
    void toStringShouldReturnCorrectStringRepresentation() {
        Acceleration acceleration = new Acceleration(3, 4);
        assertEquals("Acceleration(dx=3, dy=4)", acceleration.toString());
    }
}

