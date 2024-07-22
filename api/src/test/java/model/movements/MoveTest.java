package model.movements;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    private Acceleration acceleration;
    private Position position;
    private Move move;

    @BeforeEach
    void setUp() {
        acceleration = new Acceleration(3, 4);
        position = new Position(10, 15);
        move = new Move(acceleration, position);
    }

    @Test
    void accelerationShouldBeInitializedCorrectly() {
        assertEquals(acceleration, move.acceleration());
    }

    @Test
    void positionShouldBeInitializedCorrectly() {
        assertEquals(position, move.position());
    }

    @Test
    void getDestinationShouldReturnCorrectPosition() {
        Position expectedDestination = new Position(13, 19);
        Position actualDestination = move.getDestination();

        assertEquals(expectedDestination.getX(), actualDestination.getX());
        assertEquals(expectedDestination.getY(), actualDestination.getY());
    }

    @Test
    void getDestinationShouldHandleNegativeAcceleration() {
        Acceleration negativeAcceleration = new Acceleration(-3, -4);
        Move negativeMove = new Move(negativeAcceleration, position);

        Position expectedDestination = new Position(7, 11);
        Position actualDestination = negativeMove.getDestination();

        assertEquals(expectedDestination.getX(), actualDestination.getX());
        assertEquals(expectedDestination.getY(), actualDestination.getY());
    }

    @Test
    void getDestinationShouldHandleZeroAcceleration() {
        Acceleration zeroAcceleration = new Acceleration(0, 0);
        Move zeroMove = new Move(zeroAcceleration, position);

        Position expectedDestination = new Position(10, 15);
        Position actualDestination = zeroMove.getDestination();

        assertEquals(expectedDestination.getX(), actualDestination.getX());
        assertEquals(expectedDestination.getY(), actualDestination.getY());
    }
}
