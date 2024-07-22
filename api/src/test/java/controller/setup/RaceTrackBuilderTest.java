package controller.setup;

import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.RaceTrackBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class RaceTrackBuilderTest {

    private RaceTrackBuilder raceTrackBuilder;

    @BeforeEach
    void setUp() {
        raceTrackBuilder = new RaceTrackBuilder();
    }


    @Test
    void buildTrackShouldThrowIOExceptionForInvalidFile() {
        assertThrows(IOException.class, () -> {
            raceTrackBuilder.buildTrack(String.valueOf(new BufferedReader(new StringReader("invalid-file"))));
        });
    }
}

