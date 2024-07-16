package model.movements;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    private Position position;

    @BeforeEach
    void setUp() {
        position = new Position(3, 4);
    }

    @Test
    void constructorShouldInitializeXAndY() {
        Position position = new Position(5, 6);
        assertEquals(5, position.getX());
        assertEquals(6, position.getY());
    }

    @Test
    void setXShouldUpdateX() {
        position.setX(7);
        assertEquals(7, position.getX());
    }

    @Test
    void setYShouldUpdateY() {
        position.setY(8);
        assertEquals(8, position.getY());
    }

    @Test
    void equalsShouldReturnTrueForEqualPositions() {
        Position otherPosition = new Position(3, 4);
        assertEquals(position, otherPosition);
    }

    @Test
    void equalsShouldReturnFalseForDifferentPositions() {
        Position otherPosition = new Position(4, 5);
        assertNotEquals(position, otherPosition);
    }

    @Test
    void hashCodeShouldReturnSameHashCodeForEqualPositions() {
        Position otherPosition = new Position(3, 4);
        assertEquals(position.hashCode(), otherPosition.hashCode());
    }

    @Test
    void toStringShouldReturnCorrectStringRepresentation() {
        assertEquals("Position(x=3, y=4)", position.toString());
    }

    @Test
    void calculateDistanceShouldReturnCorrectDistance() {
        Position start = new Position(0, 0);
        Position end = new Position(3, 4);
        assertEquals(5.0, Position.calculateDistance(start, end), 0.001);
    }

    @Test
    void calculateDistanceShouldReturnZeroForSamePosition() {
        assertEquals(0.0, Position.calculateDistance(position, position), 0.001);
    }
}

