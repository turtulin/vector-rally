package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.GUIGameSetup;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMovesGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.view.GraphicalIOController;

import java.util.List;

public class GUIGameEngine implements GameEngine{


    private List<Player> players;
    private NeighborsGenerator neighborsGenerator;
    private RaceTrack raceTrack;
    private final GUIGameSetup setup;
    GraphicalIOController ioController = new GraphicalIOController();

    public GUIGameEngine(BasicMovesGenerator<NeighborsGenerator> movesGenerator, GUIGameSetup setup) {
        this.setup = setup;
        GUIMatchController matchController = new GUIMatchController(ioController, movesGenerator);
    }

    @Override
    public void startGame() throws Exception {

        this.setup.initializeTrack();
    }
}
