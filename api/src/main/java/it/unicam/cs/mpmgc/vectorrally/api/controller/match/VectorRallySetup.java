package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.io.IOController;
import it.unicam.cs.mpmgc.vectorrally.api.controller.io.TerminalIOController;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.RaceCar;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.BotPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.HumanPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrackBuilder;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategyDifficulty;

import java.io.IOException;
import java.util.ArrayList;
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
    public RaceTrack selectTrack() throws Exception {
        String trackFilePath = ioController.chooseTrack();
        return trackBuilder.buildTrack(trackFilePath);
    }

    @Override
    public List<Player> configurePlayers(int maxPlayers, List<Player> existingPlayers) {
        List<Player> players = new ArrayList<>(existingPlayers);
        int numHumanPlayers = ioController.askNumberOfHumanPlayers(maxPlayers);
        List<CarColour> availableColors = new ArrayList<>(List.of(CarColour.values()));

        for (int i = 0; i < numHumanPlayers; i++) {
            CarColour chosenColor = ioController.chooseCarColor(availableColors);
            availableColors.remove(chosenColor);
            Car car = new RaceCar(chosenColor);
            Player humanPlayer = new HumanPlayer(car);
            players.add(humanPlayer);
        }

        int remainingSpots = maxPlayers - numHumanPlayers;
        for (int i = 0; i < remainingSpots; i++) {
            BotStrategyDifficulty difficulty = ioController.chooseBotStrategyDifficulty();
            CarColour chosenColor = availableColors.getFirst();
            availableColors.remove(chosenColor);
            Car car = new RaceCar(chosenColor);
            Player botPlayer = new BotPlayer(car, difficulty);
            players.add(botPlayer);
        }

        return players;
    }

    @Override
    public BotStrategyDifficulty chooseBotStrategy() {
        return ioController.chooseBotStrategyDifficulty();
    }
}
