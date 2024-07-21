package it.unicam.cs.mpmgc.vectorrally.api.controller.setup;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.RaceCar;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.BotPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;
import it.unicam.cs.mpmgc.vectorrally.api.view.IOController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GUIGameSetup implements  GameSetup {
    private final String difficulty;
    private final String trackChosen;
    private final RaceTrackBuilder trackBuilder;
    
    public GUIGameSetup(String difficulty, String trackChosen) {
        this.difficulty = difficulty;
        this.trackChosen = trackChosen;
        this.trackBuilder = new RaceTrackBuilder();
    }

    @Override
    public List<Player> initializePlayers(RaceTrack raceTrack) {
        List<Player> players = new ArrayList<>();
        List<CarColour> availableColors = new ArrayList<>(Arrays.asList(CarColour.values()));
        List<Position> availablePositions = raceTrack.getPositionsOfComponent(TrackComponent.START_POSITION);
        setupBotPlayers(availablePositions.size(), players, availableColors, availablePositions);
        return players;
    }

    private void setupBotPlayers(int remainingPositions, List<Player> players, List<CarColour> availableColors, List<Position> availablePositions) {
        BotStrategy difficulty = BotStrategy.valueOf(this.difficulty);
        for (int i = 0; i < remainingPositions; i++) {
            CarColour chosenColor = availableColors.get(i);
            addPlayer(players, chosenColor, difficulty, availablePositions.get(i));
        }
    }

    private void addPlayer(List<Player> players, CarColour chosenColor, BotStrategy difficulty, Position position) {
        Car car = new RaceCar(chosenColor);
        Player player = new BotPlayer(car, difficulty);
        player.setPosition(position);
        players.add(player);
    }


    public String getDifficulty() {
        return difficulty;
    }


    @Override
    public RaceTrack initializeTrack() throws Exception {
        String directoryPath = IOController.checkRootPath();
        String trackPath = directoryPath + "/" + trackChosen;
        return trackBuilder.buildTrack(trackPath);
    }
}
