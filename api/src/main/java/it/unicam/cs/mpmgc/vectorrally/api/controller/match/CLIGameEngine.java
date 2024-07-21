package it.unicam.cs.mpmgc.vectorrally.api.controller.match;


import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.CLIGameSetup;
import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.GameSetup;
import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.SetupResult;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.RaceTrackBuilder;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMoveValidator;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMovesGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.view.CLIIOController;

import java.util.List;

/**
 * Represents the game engine for the vector rally game using a command-line interface.
 * Manages the game loop and overall game logic.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class CLIGameEngine implements GameEngine {
    private final CLIIOController ioController;
    private List<Player> players;
    private NeighborsGenerator neighborsGenerator;
    private RaceTrack raceTrack;
    private final GameSetup setup;

    /**
     * Constructs a CLIGameEngine with the specified IO controller.
     *
     * @param ioController the IO controller used for input/output operations
     */
    public CLIGameEngine(CLIIOController ioController) {
        this.ioController = ioController;
        RaceTrackBuilder raceTrackBuilder = new RaceTrackBuilder();
        this.setup = new CLIGameSetup(ioController, raceTrackBuilder);
    }

    @Override
    public void startGame() throws Exception {
        SetupResult setupResult = setupMatch();
        startMatch(setupResult.players(), setupResult.raceTrack(), setupResult.generator());
        while (endMatch()) {
            setupResult = setupMatch();
            startMatch(setupResult.players(), setupResult.raceTrack(), setupResult.generator());
        }
    }

    /**
     * Sets up the match by initializing the track, players, and shift algorithm.
     * Repeats the setup process until the user confirms the configuration.
     *
     * @return the result of the setup containing the neighbors generator, racetrack, and players
     * @throws Exception if an error occurs during setup
     */
    private SetupResult setupMatch() throws Exception {
        boolean confirmConfiguration = false;
        while (!confirmConfiguration) {
            this.neighborsGenerator = this.ioController.initializeShiftAlgorithm();
            this.raceTrack = this.setup.initializeTrack();
            this.players = this.setup.initializePlayers(raceTrack);
            confirmConfiguration = ioController.askIfSatisfiedWithConfiguration(raceTrack, players);
        }
        return new SetupResult(neighborsGenerator, raceTrack, players);
    }

    /**
     * Starts the match with the given players, racetrack, and neighbors generator.
     *
     * @param players the list of players participating in the match
     * @param raceTrack the racetrack on which the match is played
     * @param neighborsGenerator the generator for neighboring moves
     * @throws Exception if an error occurs during match initialization or execution
     */
    private void startMatch(List<Player> players, RaceTrack raceTrack, NeighborsGenerator neighborsGenerator) throws Exception {
        MatchController matchController = new CLIMatchController(ioController, new BasicMovesGenerator<>(neighborsGenerator, new BasicMoveValidator()));
        matchController.initializeMatch(players, raceTrack);
        matchController.startMatch();
    }

    /**
     * Handles the end of the match, displaying an end message and asking the user if they want to play another match.
     *
     * @return true if the user wants to play another match, false otherwise
     */
    private boolean endMatch() {
        ioController.displayEndMatchMessage();
        return ioController.askToPlayAnotherMatch();
    }

}
