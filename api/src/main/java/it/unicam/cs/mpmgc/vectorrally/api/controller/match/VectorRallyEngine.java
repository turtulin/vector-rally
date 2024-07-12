package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.EightNeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.FourNeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.controller.io.TerminalIOController;
import it.unicam.cs.mpmgc.vectorrally.api.controller.io.TerminalUtils;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrackBuilder;

/**
 * This class implements the GameEngine interface, managing the game loop and overall game logic.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class VectorRallyEngine implements GameEngine {
    private final TerminalIOController ioController;
    private final RaceTrackBuilder trackBuilder;

    public VectorRallyEngine() {
        this.ioController = new TerminalIOController();
        this.trackBuilder = new RaceTrackBuilder();
    }

    @Override
    public void startGame() {
        TerminalUtils.printWelcomeMessage();
        GameSetup gameSetup = new VectorRallySetup(ioController, trackBuilder);
        gameSetup.initializeGameSetupSequence();
        MatchController matchController = new VectorRallyMatchController(gameSetup, ioController, new FourNeighborsGenerator());
        matchController.startSimulation();
    }
}
