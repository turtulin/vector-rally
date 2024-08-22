package it.unicam.cs.mpmgc.vectorrally.api.controller.match;


import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.*;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMoveValidator;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMovesGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.view.FinishGameView;
import it.unicam.cs.mpmgc.vectorrally.api.view.MatchGameView;
import it.unicam.cs.mpmgc.vectorrally.api.view.SetupGameView;

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
public class BasicGameEngine implements GameEngine {
    private final MatchGameView matchGameView;
    private final FinishGameView finishGameView;
    private final GameSetup setup;

    /**
     * Constructs a BasicGameEngine with the specified IO controller.
     *
     * @param matchGameView the IO controller used for input/output operations
     */
    public BasicGameEngine(MatchGameView matchGameView, FinishGameView finishGameView, SetupGameView setupGameView) {
        this.matchGameView = matchGameView;
        this.finishGameView = finishGameView;
        this.setup = new BasicGameSetup(setupGameView);
    }

    @Override
    public void startGame() throws Exception {
        SetupResult setupResult = setupMatch();
        initializeMatch(setupResult.players(), setupResult.raceTrack(), setupResult.generator());
        while (endGame()) {
            setupResult = setupMatch();
            initializeMatch(setupResult.players(), setupResult.raceTrack(), setupResult.generator());
        }
    }

    @Override
    public SetupResult setupMatch() throws Exception {
        NeighborsGenerator neighborsGenerator = this.setup.initializeShiftAlgorithm();
        RaceTrack raceTrack = this.setup.initializeTrack();
        List<Player> players = this.setup.initializePlayers(raceTrack);
        return new SetupResult(neighborsGenerator, raceTrack, players);
    }

    @Override
    public void initializeMatch(List<Player> players, RaceTrack raceTrack, NeighborsGenerator neighborsGenerator) throws Exception {
        MatchController matchController = new BasicMatchController(matchGameView, new BasicMovesGenerator<>(neighborsGenerator, new BasicMoveValidator()), players, raceTrack);
        matchController.startMatch();
    }

    /**
     * Handles the end of the match, displaying an end message and asking the user if they want to play another match.
     *
     * @return true if the user wants to play another match, false otherwise
     */
    public boolean endGame() {
        return finishGameView.playAnotherMatch();
    }

}
