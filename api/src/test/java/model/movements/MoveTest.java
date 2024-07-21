package model.movements;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    @Test
    void constructorShouldInitializeFields() {
        Acceleration acceleration = new Acceleration(3, 4);
        Position position = new Position(5, 6);
        Move move = new Move(acceleration, position);

        assertEquals(acceleration, move.acceleration());
        assertEquals(position, move.position());
    }

    @Test
    void getDestinationShouldReturnCorrectPosition() {
        Acceleration acceleration = new Acceleration(3, 4);
        Position position = new Position(5, 6);
        Move move = new Move(acceleration, position);

        Position destination = move.getDestination();
        assertEquals(new Position(8, 10), destination);
    }

    @Test
    void getDestinationShouldHandleNegativeAcceleration() {
        Acceleration acceleration = new Acceleration(-2, -3);
        Position position = new Position(5, 6);
        Move move = new Move(acceleration, position);

        Position destination = move.getDestination();
        assertEquals(new Position(3, 3), destination);
    }

    @Test
    void getDestinationShouldHandleZeroAcceleration() {
        Acceleration acceleration = new Acceleration(0, 0);
        Position position = new Position(5, 6);
        Move move = new Move(acceleration, position);

        Position destination = move.getDestination();
        assertEquals(new Position(5, 6), destination);
    }

    @Test
    void equalsShouldReturnTrueForEqualMoves() {
        Acceleration acceleration1 = new Acceleration(3, 4);
        Position position1 = new Position(5, 6);
        Move move1 = new Move(acceleration1, position1);

        Acceleration acceleration2 = new Acceleration(3, 4);
        Position position2 = new Position(5, 6);
        Move move2 = new Move(acceleration2, position2);

        assertEquals(move1, move2);
    }

    @Test
    void equalsShouldReturnFalseForDifferentMoves() {
        Acceleration acceleration1 = new Acceleration(3, 4);
        Position position1 = new Position(5, 6);
        Move move1 = new Move(acceleration1, position1);

        Acceleration acceleration2 = new Acceleration(4, 5);
        Position position2 = new Position(6, 7);
        Move move2 = new Move(acceleration2, position2);

        assertNotEquals(move1, move2);
    }

    @Test
    void hashCodeShouldReturnSameHashCodeForEqualMoves() {
        Acceleration acceleration1 = new Acceleration(3, 4);
        Position position1 = new Position(5, 6);
        Move move1 = new Move(acceleration1, position1);

        Acceleration acceleration2 = new Acceleration(3, 4);
        Position position2 = new Position(5, 6);
        Move move2 = new Move(acceleration2, position2);

        assertEquals(move1.hashCode(), move2.hashCode());
    }

    @Test
    void toStringShouldReturnCorrectStringRepresentation() {
        Acceleration acceleration = new Acceleration(3, 4);
        Position position = new Position(5, 6);
        Move move = new Move(acceleration, position);

        String expected = "Move[acceleration=Acceleration(dx=3, dy=4), position=Position(x=5, y=6)]";
        assertEquals(expected, move.toString());
    }
}
