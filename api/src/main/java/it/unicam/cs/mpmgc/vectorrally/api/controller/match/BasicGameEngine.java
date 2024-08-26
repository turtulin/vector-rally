package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.*;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.Track;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMoveValidator;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMovesGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.view.FinishGameView;
import it.unicam.cs.mpmgc.vectorrally.api.view.MatchGameView;
import it.unicam.cs.mpmgc.vectorrally.api.view.SetupGameView;

import java.util.List;


/**
 * The {@code BasicGameEngine} class is responsible for managing the overall flow of the Vector Rally game,
 * including initializing the game setup, running matches, and determining if the player wishes to play again.
 * <p>
 * This class orchestrates the game's lifecycle by interacting with various components such as the {@link GameSetup}
 * for initializing game settings, the {@link MatchGameView} for managing the match interface, and the {@link FinishGameView}
 * to prompt the player for replaying the game.
 *
 * @version 1.0
 * @since 2024-08-21
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class BasicGameEngine implements GameEngine {
    private final MatchGameView matchGameView;
    private final FinishGameView finishGameView;
    private final GameSetup setup;

    public BasicGameEngine(MatchGameView matchGameView, FinishGameView finishGameView, SetupGameView setupGameView) {
        this.matchGameView = matchGameView;
        this.finishGameView = finishGameView;
        this.setup = new BasicGameSetup(setupGameView);
    }

    @Override
    public void startGame() throws Exception {
        SetupResult setupResult = setupMatch();
        initializeMatch(setupResult.players(), setupResult.raceTrack(), setupResult.generator());
        while (playAgain()) {
            setupResult = setupMatch();
            initializeMatch(setupResult.players(), setupResult.raceTrack(), setupResult.generator());
        }
    }

    @Override
    public SetupResult setupMatch() throws Exception {
        NeighborsGenerator neighborsGenerator = this.setup.initializeShiftAlgorithm();
        Track raceTrack = this.setup.initializeTrack();
        List<Player> players = this.setup.initializePlayers(raceTrack);
        return new SetupResult(neighborsGenerator, raceTrack, players);
    }

    @Override
    public void initializeMatch(List<Player> players, Track raceTrack, NeighborsGenerator neighborsGenerator) {
        MatchController matchController = new BasicMatchController(matchGameView, new BasicMovesGenerator<>(neighborsGenerator, new BasicMoveValidator()), players, raceTrack);
        matchController.startMatch();
    }

    @Override
    public boolean playAgain() {
        return finishGameView.playAnotherMatch();
    }
}
