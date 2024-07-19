package it.unicam.cs.mpmgc.vectorrally.api.controller.setup;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.List;

public class GUIGameSetup implements  GameSetup {
    @Override
    public NeighborsGenerator initializeShiftAlgorithm() throws Exception {
        return null;
    }

    @Override
    public List<Player> initializePlayers(RaceTrack raceTrack) throws Exception {
        return null;
    }

    @Override
    public RaceTrack initializeTrack() throws Exception {
        return null;
    }
}
