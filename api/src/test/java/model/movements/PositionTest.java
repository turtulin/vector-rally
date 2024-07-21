package model.movements;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    private Position position;

    @BeforeEach
    void setUp() {
        position = new Position(10, 15);
    }

    @Test
    void positionShouldInitializeXAndYCorrectly() {
        assertEquals(10, position.getX());
        assertEquals(15, position.getY());
    }

    @Test
    void getXShouldReturnCorrectValue() {
        assertEquals(10, position.getX());
    }

    @Test
    void getYShouldReturnCorrectValue() {
        assertEquals(15, position.getY());
    }

    @Test
    void setXShouldUpdateXCorrectly() {
        position.setX(20);
        assertEquals(20, position.getX());
    }

    @Test
    void setYShouldUpdateYCorrectly() {
        position.setY(25);
        assertEquals(25, position.getY());
    }

    @Test
    void equalsShouldReturnTrueForEqualPositions() {
        Position otherPosition = new Position(10, 15);
        assertTrue(position.equals(otherPosition));
    }

    @Test
    void equalsShouldReturnFalseForDifferentPositions() {
        Position otherPosition = new Position(20, 25);
        assertFalse(position.equals(otherPosition));
    }

    @Test
    void hashCodeShouldReturnSameValueForEqualPositions() {
        Position otherPosition = new Position(10, 15);
        assertEquals(position.hashCode(), otherPosition.hashCode());
    }

    @Test
    void hashCodeShouldReturnDifferentValueForDifferentPositions() {
        Position otherPosition = new Position(20, 25);
        assertNotEquals(position.hashCode(), otherPosition.hashCode());
    }
}
