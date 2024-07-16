package model.rules;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Direction;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.DefaultPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMoveValidator;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.RaceCar;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;

import java.util.List;
import java.util.ArrayList;

public class BasicMoveValidatorTest {

    private BasicMoveValidator validator;
    private RaceTrack track;
    private List<Player> players;

    @BeforeEach
    void setUp() {
        validator = new BasicMoveValidator();
        TrackComponent[][] trackComponents = {
                { TrackComponent.ROAD, TrackComponent.ROAD, TrackComponent.ROAD },
                { TrackComponent.WALL, TrackComponent.ROAD, TrackComponent.END_LINE },
                { TrackComponent.ROAD, TrackComponent.ROAD, TrackComponent.START_LINE }
        };
        track = new RaceTrack(trackComponents);

        // Create players for testing
        players = new ArrayList<>();
        Car car = new RaceCar(CarColour.RED);
        Player player = new DefaultPlayer(car) {};
        player.setPosition(new Position(0, 0));
        players.add(player);
    }

    @Test
    void isValidShouldReturnFalseWhenMoveIsOutOfBounds() {
        Move move = new Move(new Acceleration(1, 1), new Position(2, 2));

        boolean result = validator.isValid(move, track, players);
        assertFalse(result);
    }

    @Test
    void isValidShouldReturnFalseWhenMovePassesThroughWall() {
        Move move = new Move(new Acceleration(1, 0), new Position(0, 1));
        boolean result = validator.isValid(move, track, players);
        assertFalse(result);
    }

    @Test
    void isValidShouldReturnFalseWhenMovePassesThroughPlayers() {
        Move move = new Move(new Acceleration(0, 1), new Position(0, 0));

        boolean result = validator.isValid(move, track, players);
        assertFalse(result);
    }

    @Test
    void isValidShouldReturnFalseWhenMoveEndsOnAnotherPlayer() {
        Move move = new Move(new Acceleration(0, 0), new Position(0, 0));

        boolean result = validator.isValid(move, track, players);
        assertFalse(result);
    }

    @Test
    void isValidShouldReturnTrueForValidMove() {
        Move move = new Move(new Acceleration(1, 1), new Position(0, 0));

        boolean result = validator.isValid(move, track, players);
        assertTrue(result);
    }

    @Test
    void passesThroughComponentShouldReturnTrueWhenPassingThroughComponent() {
        Move move = new Move(new Acceleration(2, 0), new Position(0, 1));

        boolean result = validator.passesThroughComponent(track, move, TrackComponent.WALL);
        assertTrue(result);
    }

    @Test
    void passesThroughComponentShouldReturnFalseWhenNotPassingThroughComponent() {
        Move move = new Move(new Acceleration(1, 0), new Position(0, 2));

        boolean result = validator.passesThroughComponent(track, move, TrackComponent.WALL);
        assertFalse(result);
    }

    @Test
    void passesThroughPlayersShouldReturnTrueWhenPassingThroughPlayer() {
        Move move = new Move(new Acceleration(0, 0), new Position(0, 0));

        boolean result = validator.passesThroughPlayers(move, players);
        assertTrue(result);
    }

    @Test
    void passesThroughPlayersShouldReturnFalseWhenNotPassingThroughPlayer() {
        Move move = new Move(new Acceleration(1, 1), new Position(1, 1));

        boolean result = validator.passesThroughPlayers(move, players);
        assertFalse(result);
    }

    @Test
    void getPositionsBetweenShouldReturnCorrectPositions() {
        Move move = new Move(new Acceleration(2, 0), new Position(0, 0));
        List<Position> positions = validator.getPositionsBetween(move);

        assertEquals(3, positions.size());
        assertTrue(positions.contains(new Position(0, 0)));
        assertTrue(positions.contains(new Position(1, 0)));
        assertTrue(positions.contains(new Position(2, 0)));
    }

    @Test
    void getPositionsBetweenShouldHandleDiagonalMoves() {
        Move move = new Move(new Acceleration(2, 2), new Position(0, 0));
        List<Position> positions = validator.getPositionsBetween(move);

        assertEquals(3, positions.size());
        assertTrue(positions.contains(new Position(0, 0)));
        assertTrue(positions.contains(new Position(1, 1)));
        assertTrue(positions.contains(new Position(2, 2)));
    }
}
