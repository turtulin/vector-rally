package it.unicam.cs.mpmgc.vectorrally.api.controller.setup;

import it.unicam.cs.mpmgc.vectorrally.api.controller.io.IOController;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.EightNeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.FourNeighborsGenerator;
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
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.Track;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class implements the GameSetup interface, handling the setup process for the game.
 *
 * @version 1.0
 * @since 2024-07-11
 */
public class VectorRallySetup implements GameSetup {

    private final IOController ioController;
    private final RaceTrackBuilder trackBuilder;

    public VectorRallySetup(IOController ioController, RaceTrackBuilder trackBuilder) {
        this.ioController = ioController;
        this.trackBuilder = trackBuilder;
    }

    @Override
    public NeighborsGenerator initializeShiftAlgorithm() {
        displayWelcomeAndRules();
        return chooseRuleType();
    }

    @Override
    public List<Player> initializePlayers(Track raceTrack) {
        int numHumanPlayers = ioController.askNumberOfHumanPlayers(raceTrack.getPositionsOfComponent(TrackComponent.START_POSITION).size());
        List<Player> players = new ArrayList<>();
        List<CarColour> availableColors = new ArrayList<>(Arrays.asList(CarColour.values()));
        setupHumanPlayers(numHumanPlayers, players, availableColors);
        List<Position> availablePositions = raceTrack.getPositionsOfComponent(TrackComponent.START_POSITION);
        chooseStartingPositions(numHumanPlayers, players, availablePositions);
        setupBotPlayers(availablePositions.size(), players, availableColors, availablePositions);
        return players;
    }

    @Override
    public RaceTrack initializeTrack() throws Exception {
        return selectTrack();
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

    public RaceTrack selectTrack() throws Exception {
        String trackFilePath = ioController.chooseTrack();
        return trackBuilder.buildTrack(trackFilePath);
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
//    public List<Player> configurePlayers(int maxPlayers, List<Player> existingPlayers) {
//        List<Player> players = new ArrayList<>(existingPlayers);
//        int numHumanPlayers = ioController.askNumberOfHumanPlayers(maxPlayers);
//        List<CarColour> availableColors = new ArrayList<>(List.of(CarColour.values()));
//
//        for (int i = 0; i < numHumanPlayers; i++) {
//            CarColour chosenColor = ioController.chooseCarColor(availableColors);
//            availableColors.remove(chosenColor);
//            Car car = new RaceCar(chosenColor);
//            Player humanPlayer = new HumanPlayer(car);
//            players.add(humanPlayer);
//        }
//
//        int remainingSpots = maxPlayers - numHumanPlayers;
//        for (int i = 0; i < remainingSpots; i++) {
//            CarColour chosenColor = availableColors.getFirst();
//            availableColors.remove(chosenColor);
//            BotStrategy difficulty = ioController.chooseBotStrategyDifficulty(chosenColor);
//            Car car = new RaceCar(chosenColor);
//            Player botPlayer = new BotPlayer(car, difficulty);
//            players.add(botPlayer);
//        }
//
//        return players;
//    }


    public BotStrategy chooseBotStrategy(CarColour botColor) {
        return ioController.chooseBotStrategyDifficulty(botColor);
    }



}
