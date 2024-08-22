package it.unicam.cs.mpmgc.vectorrally.api.controller.setup;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.RaceCar;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.BotPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.HumanPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;
import it.unicam.cs.mpmgc.vectorrally.api.view.SetupGameView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicGameSetup implements GameSetup {
    private final SetupGameView setupGameView;

    public BasicGameSetup(SetupGameView setupGameView) {
        this.setupGameView = setupGameView;
    }

    @Override
    public List<Player> initializePlayers(RaceTrack raceTrack) {
        List<Player> players = new ArrayList<>();
        List<CarColour> availableColors = new ArrayList<>(Arrays.asList(CarColour.values()));
        List<Position> availablePositions = raceTrack.getPositionsOfComponent(TrackComponent.START_POSITION);
        int numHumanPlayers = setupGameView.getNumHumanPlayers(maxPlayers(raceTrack));
        setupHumanPlayers(numHumanPlayers, players, availableColors, availablePositions);
        setupBotPlayers(availablePositions.size(), players, availableColors, availablePositions);
        return players;
    }

    @Override
    public RaceTrack initializeTrack() throws Exception {
        String track = setupGameView.getChosenTrack();
        RaceTrackBuilder raceTrackBuilder = new RaceTrackBuilder();
        return raceTrackBuilder.buildTrack(track);
    }

    @Override
    public NeighborsGenerator initializeShiftAlgorithm() {
        return setupGameView.getShiftAlgorithm();
    }

    @Override
    public int maxPlayers(RaceTrack raceTrack) {
        return raceTrack.getPositionsOfComponent(TrackComponent.START_POSITION).size();
    }

    private void setupHumanPlayers(int numHumanPlayers, List<Player> players, List<CarColour> availableColors, List<Position> availablePositions) {
        for (int i = 0; i < numHumanPlayers; i++) {
            Player humanPlayer = new HumanPlayer(new RaceCar(availableColors.getFirst()));
            availableColors.remove(availableColors.getFirst());
            humanPlayer.setPosition(availablePositions.getFirst());
            availablePositions.remove(availablePositions.getFirst());
            players.add(humanPlayer);
        }
    }

    private void setupBotPlayers(int remainingPositions, List<Player> players, List<CarColour> availableColours, List<Position> availablePositions) {
        BotStrategy difficulty = setupGameView.chooseStrategyDifficulty();
        for (int i = 0; i < remainingPositions; i++) {
            Player botPlayer = new BotPlayer(new RaceCar(availableColours.get(i)), difficulty);
            botPlayer.setPosition(availablePositions.get(i));
            players.add(botPlayer);
        }
    }
}
