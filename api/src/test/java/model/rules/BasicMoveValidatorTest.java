package model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMoveValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BasicMoveValidatorTest {

    private BasicMoveValidator validator;
    private RaceTrack raceTrack;
    private Player player;

    @BeforeEach
    void setUp() {
        validator = new BasicMoveValidator();
        raceTrack = mock(RaceTrack.class);
        player = mock(Player.class);
    }

    @Test
    void isValidShouldReturnFalseWhenOutOfBounds() {
        Position startPosition = new Position(0, 0);
        Acceleration acceleration = new Acceleration(1, 1);
        Move move = new Move(acceleration, startPosition);

        when(raceTrack.isInBounds(anyInt(), anyInt())).thenReturn(false);

        boolean result = validator.isValid(move, raceTrack, Collections.emptyList());

        assertFalse(result);
        verify(raceTrack).isInBounds(1, 1);
    }

    @Test
    void isValidShouldReturnFalseWhenPassingThroughWall() {
        Position startPosition = new Position(0, 0);
        Acceleration acceleration = new Acceleration(2, 2);
        Move move = new Move(acceleration, startPosition);

        when(raceTrack.isInBounds(anyInt(), anyInt())).thenReturn(true);
        when(raceTrack.getComponentAt(anyInt(), anyInt())).thenReturn(TrackComponent.ROAD, TrackComponent.WALL);

        boolean result = validator.isValid(move, raceTrack, Collections.emptyList());

        assertFalse(result);
    }


    @Test
    void isValidShouldReturnTrueForValidMove() {
        Position startPosition = new Position(0, 0);
        Acceleration acceleration = new Acceleration(1, 1);
        Move move = new Move(acceleration, startPosition);

        when(raceTrack.isInBounds(anyInt(), anyInt())).thenReturn(true);
        when(raceTrack.getComponentAt(anyInt(), anyInt())).thenReturn(TrackComponent.ROAD);
        when(player.getPosition()).thenReturn(new Position(2, 2));

        boolean result = validator.isValid(move, raceTrack, Collections.singletonList(player));

        assertTrue(result);
    }

    @Test
    void passesThroughComponentShouldReturnTrueWhenPassingThroughComponent() {
        Position startPosition = new Position(0, 0);
        Acceleration acceleration = new Acceleration(2, 2);
        Move move = new Move(acceleration, startPosition);

        when(raceTrack.getComponentAt(anyInt(), anyInt())).thenReturn(TrackComponent.WALL);

        boolean result = validator.passesThroughComponent(raceTrack, move, TrackComponent.WALL);

        assertTrue(result);
    }

    @Test
    void passesThroughPlayersShouldReturnTrueWhenPassingThroughPlayer() {
        Position startPosition = new Position(0, 0);
        Acceleration acceleration = new Acceleration(1, 1);
        Move move = new Move(acceleration, startPosition);

        Player otherPlayer = mock(Player.class);
        when(otherPlayer.getPosition()).thenReturn(new Position(1, 1));

        boolean result = validator.passesThroughPlayers(move, Collections.singletonList(otherPlayer));

        assertTrue(result);
    }

    @Test
    void getPositionsBetweenShouldReturnCorrectPositions() {
        Position startPosition = new Position(0, 0);
        Acceleration acceleration = new Acceleration(2, 2);
        Move move = new Move(acceleration, startPosition);

        List<Position> positions = validator.getPositionsBetween(move);

        assertEquals(3, positions.size());
        assertEquals(new Position(0, 0), positions.get(0));
        assertEquals(new Position(1, 1), positions.get(1));
        assertEquals(new Position(2, 2), positions.get(2));
    }

}

