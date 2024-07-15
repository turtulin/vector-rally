package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.io.IOController;
import it.unicam.cs.mpmgc.vectorrally.api.controller.io.Utils;
import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.VectorRallySetup;
import it.unicam.cs.mpmgc.vectorrally.api.controller.io.TerminalIOController;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrackBuilder;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMoveValidator;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMovesGenerator;
import jdk.jshell.execution.Util;

import java.util.List;

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
    private final IOController ioController;

    private final VectorRallySetup setup;

    private List<Player> players;

    private NeighborsGenerator neighborsGenerator;

    private RaceTrack raceTrack;

    public VectorRallyEngine() {
        this.ioController = new TerminalIOController();
        RaceTrackBuilder raceTrackBuilder = new RaceTrackBuilder();
        this.setup = new VectorRallySetup(ioController, raceTrackBuilder);
    }

    @Override
    public void startGame() throws Exception {
        displayWelcomeAndRules();
        boolean confirmConfiguration = false;
        while (!confirmConfiguration) {
            this.neighborsGenerator = this.setup.initializeShiftAlgorithm();
            this.raceTrack = this.setup.initializeTrack();
            this.players = this.setup.initializePlayers(raceTrack);
            confirmConfiguration = ioController.askIfSatisfiedWithConfiguration(raceTrack, players);
        }
        startMatch(players, raceTrack, neighborsGenerator);
    }

    private void startMatch(List<Player> players, RaceTrack raceTrack, NeighborsGenerator neighborsGenerator) throws Exception {
        MatchController matchController = new VectorRallyMatchController(ioController, new BasicMovesGenerator<>(neighborsGenerator, new BasicMoveValidator()));
        matchController.initializeMatch(players, raceTrack);
        matchController.startMatch();
    }

    private void displayWelcomeAndRules() {
        Utils.displayWelcomeMessage();
        if (!ioController.askIfPlayerKnowsRules()) {
            Utils.displayGameRules();
        }
    }

}
