package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.io.IOController;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.EightNeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.FourNeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.controller.io.TerminalIOController;
import it.unicam.cs.mpmgc.vectorrally.api.controller.io.IOUtils;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.BasicComponentPassChecker;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrackBuilder;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMoveValidator;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMovesGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.MoveValidator;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategyDifficulty;

import java.io.IOException;
import java.util.ArrayList;
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
    private final GameSetup gameSetup;

    public VectorRallyEngine() {
        this.ioController = new TerminalIOController();
        RaceTrackBuilder trackBuilder = new RaceTrackBuilder();
        this.gameSetup = new VectorRallySetup(ioController, trackBuilder);
    }

    @Override
    public void startGame() {
        ioController.displayWelcomeMessage();
        if (!ioController.askIfPlayerKnowsRules()) {
            ioController.displayGameRules();
        }
        int ruleType = ioController.chooseRuleType();
        NeighborsGenerator neighborsGenerator = createNeighborsGenerator(ruleType);
        MoveValidator moveValidator;
        BasicMovesGenerator moveGenerator;

        boolean playAgain;
        do {
            RaceTrack raceTrack = null;
            try {
                raceTrack = gameSetup.selectTrack();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            moveValidator = new BasicMoveValidator(new BasicComponentPassChecker(raceTrack)); // Update with the selected track
            moveGenerator = new BasicMovesGenerator(neighborsGenerator, moveValidator);

            int maxPlayers = raceTrack.getPositionsOfComponent(TrackComponent.START_POSITION).size();
            List<Player> players = gameSetup.configurePlayers(maxPlayers, new ArrayList<>());

            MatchController matchController = new VectorRallyMatchController(ioController, moveGenerator);
            matchController.initializeMatch(players, raceTrack);
            matchController.startMatch();

            playAgain = ioController.askToPlayAnotherMatch();
            if (playAgain && !ioController.sameConfiguration()) {
                gameSetup.configurePlayers(maxPlayers, players);
            }
        } while (playAgain);

        ioController.displayEndMatchMessage();
    }

    private NeighborsGenerator createNeighborsGenerator(int ruleType) {
        return switch (ruleType) {
            case 1 -> new FourNeighborsGenerator();
            case 2 -> new EightNeighborsGenerator();
            default -> throw new IllegalArgumentException("Invalid rule type");
        };
    }
}
