package controller.setup;

import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.RaceTrackBuilder;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.GUIGameSetup;
import it.unicam.cs.mpmgc.vectorrally.api.view.TrackPathController;
import it.unicam.cs.mpmgc.vectorrally.api.view.IOController;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class GUIGameSetupTest {

    @BeforeEach
    void setUp() {
        RaceTrackBuilder trackBuilder = mock(RaceTrackBuilder.class);
        IOController ioController = mock(IOController.class);

        mockStatic(IOController.class);
        when(TrackPathController.checkRootPath()).thenReturn("/mock/path");

        GUIGameSetup gameSetup = new GUIGameSetup("EASY", "track1.txt");
    }

    private RaceTrack createMockRaceTrack() {
        RaceTrack raceTrack = mock(RaceTrack.class);
        when(raceTrack.getPositionsOfComponent(TrackComponent.START_POSITION)).thenReturn(
                Arrays.asList(new Position(0, 0), new Position(1, 0), new Position(2, 0), new Position(3, 0))
        );
        return raceTrack;
    }
}
