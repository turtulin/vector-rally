package model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMovesGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.MoveValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import static org.mockito.Mockito.*;

class BasicMovesGeneratorTest {

    private BasicMovesGenerator<NeighborsGenerator> movesGenerator;
    private NeighborsGenerator neighborsGenerator;
    private MoveValidator moveValidator;
    private RaceTrack raceTrack;
    private Player player;

    @BeforeEach
    void setUp() {
        neighborsGenerator = mock(NeighborsGenerator.class);
        moveValidator = mock(MoveValidator.class);
        movesGenerator = new BasicMovesGenerator<>(neighborsGenerator, moveValidator);
        raceTrack = mock(RaceTrack.class);
        player = mock(Player.class);
    }

    @Test
    void generatePossibleMovesShouldReturnEmptyListWhenNoValidMoves() {
        when(player.getPlayerAcceleration()).thenReturn(new Acceleration(0, 0));
        when(neighborsGenerator.generateShifts(any())).thenReturn(Collections.singletonList(new Acceleration(1, 1)));
        when(moveValidator.isValid(any(), any(), any())).thenReturn(false);
        when(player.getPosition()).thenReturn(new Position(0, 0));

        List<Move> possibleMoves = movesGenerator.generatePossibleMoves(player, raceTrack, Collections.singletonList(player));

        assertTrue(possibleMoves.isEmpty());
    }

    @Test
    void generatePossibleMovesShouldReturnValidMoves() {
        when(player.getPlayerAcceleration()).thenReturn(new Acceleration(0, 0));
        when(neighborsGenerator.generateShifts(any())).thenReturn(Arrays.asList(
                new Acceleration(1, 0),
                new Acceleration(0, 1)
        ));
        when(moveValidator.isValid(any(), any(), any())).thenReturn(true);
        when(player.getPosition()).thenReturn(new Position(0, 0));

        List<Move> possibleMoves = movesGenerator.generatePossibleMoves(player, raceTrack, Collections.singletonList(player));

        assertEquals(2, possibleMoves.size());
    }

    @Test
    void generatePossibleMovesShouldFilterInvalidMoves() {
        when(player.getPlayerAcceleration()).thenReturn(new Acceleration(0, 0));
        when(neighborsGenerator.generateShifts(any())).thenReturn(Arrays.asList(
                new Acceleration(1, 0),
                new Acceleration(0, 1)
        ));
        when(moveValidator.isValid(any(Move.class), any(RaceTrack.class), anyList())).thenReturn(true).thenReturn(false);
        when(player.getPosition()).thenReturn(new Position(0, 0));

        List<Move> possibleMoves = movesGenerator.generatePossibleMoves(player, raceTrack, Collections.singletonList(player));

        assertEquals(1, possibleMoves.size());
    }

    @Test
    void generatePossibleMovesShouldUseNeighborsGenerator() {
        when(player.getPlayerAcceleration()).thenReturn(new Acceleration(0, 0));
        when(neighborsGenerator.generateShifts(any())).thenReturn(Collections.singletonList(new Acceleration(1, 1)));
        when(moveValidator.isValid(any(), any(), any())).thenReturn(true);
        when(player.getPosition()).thenReturn(new Position(0, 0));

        movesGenerator.generatePossibleMoves(player, raceTrack, Collections.singletonList(player));

        verify(neighborsGenerator).generateShifts(any());
    }

    @Test
    void generatePossibleMovesShouldUseMoveValidator() {
        when(player.getPlayerAcceleration()).thenReturn(new Acceleration(0, 0));
        when(neighborsGenerator.generateShifts(any())).thenReturn(Collections.singletonList(new Acceleration(1, 1)));
        when(moveValidator.isValid(any(), any(), any())).thenReturn(true);
        when(player.getPosition()).thenReturn(new Position(0, 0));

        movesGenerator.generatePossibleMoves(player, raceTrack, Collections.singletonList(player));

        verify(moveValidator).isValid(any(Move.class), any(RaceTrack.class), anyList());
    }
}
