package controller.setup;


import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.CLIGameSetup;
import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.RaceTrackBuilder;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.BotPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.HumanPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;
import it.unicam.cs.mpmgc.vectorrally.api.view.CLIIOController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CLIGameSetupTest {

    private CLIIOController ioController;
    private RaceTrackBuilder trackBuilder;
    private CLIGameSetup gameSetup;

    @BeforeEach
    void setUp() {
        ioController = mock(CLIIOController.class);
        trackBuilder = mock(RaceTrackBuilder.class);
        gameSetup = new CLIGameSetup(ioController, trackBuilder);
    }

    @Test
    void initializeTrackShouldReturnRaceTrack() throws Exception {
        List<String> trackFilePaths = Arrays.asList("track1.txt", "track2.txt");
        when(ioController.findTrack()).thenReturn(trackFilePaths);
        when(ioController.pickTrack(trackFilePaths)).thenReturn("track1.txt");

        RaceTrack expectedTrack = createMockRaceTrack();
        when(trackBuilder.buildTrack("track1.txt")).thenReturn(expectedTrack);

        RaceTrack actualTrack = gameSetup.initializeTrack();

        assertEquals(expectedTrack, actualTrack);
    }

    private RaceTrack createMockRaceTrack() {
        RaceTrack raceTrack = mock(RaceTrack.class);
        when(raceTrack.getPositionsOfComponent(TrackComponent.START_POSITION)).thenReturn(
                Arrays.asList(new Position(0, 0), new Position(1, 0), new Position(2, 0), new Position(3, 0))
        );
        return raceTrack;
    }
}

