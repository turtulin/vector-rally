package model.racetrack;

import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import java.util.List;

public class RaceTrackTest {

    private RaceTrack raceTrack;

    @BeforeEach
    void setUp() {
        TrackComponent[][] track = new TrackComponent[][]{
                {TrackComponent.ROAD, TrackComponent.START_LINE},
                {TrackComponent.END_LINE, TrackComponent.ROAD}
        };
        raceTrack = new RaceTrack(track);
    }

    @Test
    void constructorShouldThrowExceptionWhenTrackIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new RaceTrack(null));
    }

    @Test
    void constructorShouldThrowExceptionWhenTrackIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new RaceTrack(new TrackComponent[][] {}));
    }

    @Test
    void getComponentAtShouldReturnCorrectComponent() {
        assertEquals(TrackComponent.START_LINE, raceTrack.getComponentAt(0, 1));
        assertEquals(TrackComponent.END_LINE, raceTrack.getComponentAt(1, 0));
    }

    @Test
    void getComponentAtShouldThrowExceptionWhenOutOfBounds() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> raceTrack.getComponentAt(2, 2));
    }

    @Test
    void isInBoundsShouldReturnTrueForValidPositions() {
        assertTrue(raceTrack.isInBounds(0, 0));
        assertTrue(raceTrack.isInBounds(1, 1));
    }

    @Test
    void isInBoundsShouldReturnFalseForInvalidPositions() {
        assertFalse(raceTrack.isInBounds(-1, 0));
        assertFalse(raceTrack.isInBounds(0, -1));
        assertFalse(raceTrack.isInBounds(2, 2));
    }

    @Test
    void getPositionsOfComponentShouldReturnCorrectPositions() {
        List<Position> startLinePositions = raceTrack.getPositionsOfComponent(TrackComponent.START_LINE);
        List<Position> endLinePositions = raceTrack.getPositionsOfComponent(TrackComponent.END_LINE);

        assertEquals(1, startLinePositions.size());
        assertEquals(new Position(0, 1), startLinePositions.getFirst());

        assertEquals(1, endLinePositions.size());
        assertEquals(new Position(1, 0), endLinePositions.getFirst());
    }

    @Test
    void getWidthShouldReturnCorrectWidth() {
        assertEquals(2, raceTrack.getWidth());
    }

    @Test
    void getLengthShouldReturnCorrectLength() {
        assertEquals(2, raceTrack.getLength());
    }
}

