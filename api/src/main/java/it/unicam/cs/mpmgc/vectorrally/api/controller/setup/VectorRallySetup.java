package it.unicam.cs.mpmgc.vectorrally.api.controller.setup;

import it.unicam.cs.mpmgc.vectorrally.api.controller.io.IOController;
import it.unicam.cs.mpmgc.vectorrally.api.controller.io.Utils;
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
import jdk.jshell.execution.Util;

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
        int ruleType = ioController.chooseRuleType();
        return ruleType == 1 ? new FourNeighborsGenerator() : new EightNeighborsGenerator();
    }

    @Override
    public List<Player> initializePlayers(RaceTrack raceTrack) {
        int numHumanPlayers = ioController.askNumberOfHumanPlayers(raceTrack.getPositionsOfComponent(TrackComponent.START_POSITION).size());
        List<Player> players = new ArrayList<>();
        List<CarColour> availableColors = new ArrayList<>(Arrays.asList(CarColour.values()));
        setupHumanPlayers(numHumanPlayers, players, availableColors);
        List<Position> availablePositions = raceTrack.getPositionsOfComponent(TrackComponent.START_POSITION);
        chooseStartingPositions(raceTrack, numHumanPlayers, players, availablePositions);
        setupBotPlayers(availablePositions.size(), players, availableColors, availablePositions);
        return players;
    }

    @Override
    public RaceTrack initializeTrack() throws Exception {
        String trackFilePath = ioController.findTrack();
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

    private void chooseStartingPositions(RaceTrack track, int numHumanPlayers, List<Player> players, List<Position> availablePositions) {
        Utils.printRaceTrack(track, players, availablePositions);
        for (int i = 0; i < numHumanPlayers; i++) {
            Position chosenPosition = ioController.chooseStartingPosition(players.get(i), availablePositions);
            availablePositions.remove(chosenPosition);
            players.get(i).setPosition(chosenPosition);
        }
    }

    private void setupBotPlayers(int remainingPositions, List<Player> players, List<CarColour> availableColors, List<Position> availablePositions) {
        boolean chooseForEachBot = ioController.askToChooseForEachBot();
        if (chooseForEachBot) {
            for (int i = 0; i < remainingPositions; i++) {
                CarColour chosenColor = availableColors.get(i);
                BotStrategy difficulty = ioController.chooseEachBotStrategyDifficulty(chosenColor);
                addPlayer(players, chosenColor, difficulty, availablePositions.get(i));
            }
        } else {
            BotStrategy difficulty = ioController.chooseAllBotStrategyDifficulty();
            for (int i = 0; i < remainingPositions; i++) {
                CarColour chosenColor = availableColors.get(i);
                addPlayer(players, chosenColor, difficulty, availablePositions.get(i));
            }
        }
    }

    private void addPlayer(List<Player> players, CarColour chosenColor, BotStrategy difficulty, Position position) {
        Car car = new RaceCar(chosenColor);
        Player player = (difficulty == null) ? new HumanPlayer(car) : new BotPlayer(car, difficulty);
        player.setPosition(position);
        players.add(player);
    }
}
