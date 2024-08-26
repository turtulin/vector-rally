package it.unicam.cs.mpmgc.vectorrally.api.controller.setup;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.RaceCar;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Coordinates;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.BotPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.HumanPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.controller.builders.RaceTrackBuilder;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.Track;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;
import it.unicam.cs.mpmgc.vectorrally.api.view.SetupGameView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Handles the setup process for initializing the game components, including
 * creating players, building the racetrack, and configuring movement algorithms.
 * This class interacts with the user interface to obtain necessary details.
 *
 * @version 1.0
 * @since 2024-08-21
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class BasicGameSetup implements GameSetup {
    private final SetupGameView setupGameView;

    public BasicGameSetup(SetupGameView setupGameView) {
        this.setupGameView = setupGameView;
    }

    @Override
    public List<Player> initializePlayers(Track raceTrack) {
        List<Player> players = new ArrayList<>();
        List<CarColour> availableColors = new ArrayList<>(Arrays.asList(CarColour.values()));
        List<Coordinates> availablePositions = raceTrack.getPositionsOfComponent(TrackComponent.START_POSITION);
        int numHumanPlayers = setupGameView.getNumHumanPlayers(availablePositions.size());
        setupHumanPlayers(numHumanPlayers, players, availableColors, availablePositions);
        setupBotPlayers(availablePositions.size(), players, availableColors, availablePositions);
        return players;
    }

    @Override
    public Track initializeTrack() throws Exception {
        String track = setupGameView.getChosenTrack();
        RaceTrackBuilder raceTrackBuilder = new RaceTrackBuilder();
        return raceTrackBuilder.buildTrack(track);
    }

    @Override
    public NeighborsGenerator initializeShiftAlgorithm() {
        return setupGameView.getShiftAlgorithm();
    }

    private void setupHumanPlayers(int numHumanPlayers, List<Player> players, List<CarColour> availableColors, List<Coordinates> availablePositions) {
        for(int i = 0; i < numHumanPlayers; i++) {
            Player humanPlayer = new HumanPlayer(new RaceCar(availableColors.getFirst()));
            availableColors.remove(availableColors.getFirst());
            humanPlayer.setPosition(availablePositions.getFirst());
            availablePositions.remove(availablePositions.getFirst());
            players.add(humanPlayer);
        }
    }

    private void setupBotPlayers(int remainingPositions, List<Player> players, List<CarColour> availableColours, List<Coordinates> availablePositions) {
        BotStrategy difficulty = setupGameView.chooseStrategyDifficulty();
        for(int i = 0; i < remainingPositions; i++) {
            Player botPlayer = new BotPlayer(new RaceCar(availableColours.get(i)), difficulty);
            botPlayer.setPosition(availablePositions.get(i));
            players.add(botPlayer);
        }
    }
}
