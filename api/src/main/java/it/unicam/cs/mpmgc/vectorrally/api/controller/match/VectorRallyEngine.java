package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.io.IOController;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.EightNeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.FourNeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.controller.io.TerminalIOController;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.RaceCar;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.BotPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.HumanPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrackBuilder;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMoveValidator;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMovesGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;

import java.util.ArrayList;
import java.util.Arrays;
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
    private final RaceTrackBuilder raceTrackBuilder;


    public VectorRallyEngine() {
        this.ioController = new TerminalIOController();
        this.raceTrackBuilder = new RaceTrackBuilder();
    }

    @Override
    public void startGame() throws Exception {
        // TODO: pass this responsibility to the IOController
        displayWelcomeAndRules();
        NeighborsGenerator neighborsGenerator = chooseRuleType();
        RaceTrack raceTrack = chooseTrack();
        int numHumanPlayers = ioController.askNumberOfHumanPlayers(raceTrack.getPositionsOfComponent(TrackComponent.START_POSITION).size());
        List<Player> players = new ArrayList<>();
        List<CarColour> availableColors = new ArrayList<>(Arrays.asList(CarColour.values()));
        setupHumanPlayers(numHumanPlayers, players, availableColors);
        List<Position> availablePositions = raceTrack.getPositionsOfComponent(TrackComponent.START_POSITION);
        chooseStartingPositions(numHumanPlayers, players, availablePositions);
        setupBotPlayers(availablePositions.size(), players, availableColors, availablePositions);
        if (!confirmConfiguration(raceTrack, players)) {
            startGame();
            return;
        }
        startMatch(players, raceTrack, neighborsGenerator);
    }


    private NeighborsGenerator createNeighborsGenerator(int ruleType) {
        return switch (ruleType) {
            case 1 -> new FourNeighborsGenerator();
            case 2 -> new EightNeighborsGenerator();
            default -> throw new IllegalArgumentException("Invalid rule type");
        };
    }

    private void displayWelcomeAndRules() {
        ioController.displayWelcomeMessage();
        if (!ioController.askIfPlayerKnowsRules()) {
            ioController.displayGameRules();
        }
    }

    private NeighborsGenerator chooseRuleType() {
        int ruleType = ioController.chooseRuleType();
        return ruleType == 1 ? new FourNeighborsGenerator() : new EightNeighborsGenerator();
    }

    private RaceTrack chooseTrack() throws Exception {
        String trackFile = ioController.chooseTrack();
        return raceTrackBuilder.buildTrack(trackFile);
    }

    private void setupHumanPlayers(int numHumanPlayers, List<Player> players, List<CarColour> availableColors) {
        for (int i = 0; i < numHumanPlayers; i++) {
            CarColour chosenColor = ioController.chooseCarColor(availableColors);
            availableColors.remove(chosenColor);
            Car car = new RaceCar(chosenColor);
            Player humanPlayer = new HumanPlayer(car);
            players.add(humanPlayer);
        }
    }

    private void chooseStartingPositions(int numHumanPlayers, List<Player> players, List<Position> availablePositions) {
        for (int i = 0; i < numHumanPlayers; i++) {
            Position chosenPosition = ioController.chooseStartingPosition(players.get(i), availablePositions);
            availablePositions.remove(chosenPosition);
            players.get(i).setPosition(chosenPosition);
        }
    }

    private void setupBotPlayers(int remainingPositions, List<Player> players, List<CarColour> availableColors, List<Position> availablePositions) {
        int numBots = Math.min(remainingPositions, availableColors.size());
        for (int i = 0; i < numBots; i++) {
            CarColour chosenColor = availableColors.get(i);
            BotStrategy difficulty = ioController.chooseBotStrategyDifficulty(chosenColor);
            Car car = new RaceCar(chosenColor);
            Player botPlayer = new BotPlayer(car, difficulty);
            Position botPosition = availablePositions.get(i);
            botPlayer.setPosition(botPosition);
            players.add(botPlayer);
        }
    }

    private boolean confirmConfiguration(RaceTrack raceTrack, List<Player> players) {
        ioController.printRaceTrack(raceTrack, players);
        for (Player player : players) {
            Position pos = player.getPosition();
            if (!raceTrack.isInBounds(pos.getX(), pos.getY())) {
                System.out.println("Initial position out of bounds: " + pos);
            }
        }
        return ioController.askIfSatisfiedWithConfiguration();
    }

    private void startMatch(List<Player> players, RaceTrack raceTrack, NeighborsGenerator neighborsGenerator) {
        MatchController matchController = new VectorRallyMatchController(ioController, new BasicMovesGenerator<>(neighborsGenerator, new BasicMoveValidator()));
        matchController.initializeMatch(players, raceTrack);
        matchController.startMatch();
    }

}
