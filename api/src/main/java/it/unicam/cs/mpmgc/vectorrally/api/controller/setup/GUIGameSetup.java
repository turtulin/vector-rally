package it.unicam.cs.mpmgc.vectorrally.api.controller.setup;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrackBuilder;
import it.unicam.cs.mpmgc.vectorrally.api.view.IOController;

import java.util.List;

public class GUIGameSetup implements  GameSetup {
    
    private String difficulty;
    private String trackChosen;
    private String shiftAlgorithm;
    
    private final RaceTrackBuilder trackBuilder;
    
    public GUIGameSetup(String difficulty, String trackChosen, String shiftAlgorithm) {
        this.difficulty = difficulty;
        this.trackChosen = trackChosen;
        this.shiftAlgorithm = shiftAlgorithm;
        this.trackBuilder = new RaceTrackBuilder();
    }

    @Override
    public NeighborsGenerator initializeShiftAlgorithm() throws Exception {
        return null;
    }

    @Override
    public List<Player> initializePlayers(RaceTrack raceTrack) throws Exception {
        return null;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getShiftAlgorithm() {
        return shiftAlgorithm;
    }

    @Override
    public RaceTrack initializeTrack() throws Exception {

        String directoryPath = IOController.checkRootPath();
        String trackPath = directoryPath + "/" + trackChosen;
        return trackBuilder.buildTrack(trackPath);
    }
}
