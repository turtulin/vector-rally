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

/**
 * Provides the setup for a GUI-based game, implementing the GameSetup interface.
 * Manages the initialization of players and the race track based on selected difficulty and track.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class GUIGameSetup implements  GameSetup {
    private final String difficulty;
    private final String trackChosen;
    private final RaceTrackBuilder trackBuilder;

    /**
     * Constructs a GUIGameSetup with the specified difficulty and chosen track.
     *
     * @param difficulty the difficulty level for bot players
     * @param trackChosen the name of the chosen track
     */
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

    public String getDifficulty() {
        return difficulty;
    }


    @Override
    public RaceTrack initializeTrack() throws Exception {
        String directoryPath = IOController.checkRootPath();
        String trackPath = directoryPath + "/" + trackChosen;
        return trackBuilder.buildTrack(trackPath);
    }

    /**
     * Sets up bot players with the specified difficulty, colors, and positions.
     *
     * @param remainingPositions the number of remaining positions available on the track
     * @param players the list of players to add the bot players to
     * @param availableColors the list of available car colors
     * @param availablePositions the list of available positions on the track
     */
    private void setupBotPlayers(int remainingPositions, List<Player> players, List<CarColour> availableColors, List<Position> availablePositions) {
        BotStrategy difficulty = BotStrategy.valueOf(this.difficulty);
        for (int i = 0; i < remainingPositions; i++) {
            CarColour chosenColor = availableColors.get(i);
            addPlayer(players, chosenColor, difficulty, availablePositions.get(i));
        }
    }

    /**
     * Adds a bot player to the list of players with the specified color, difficulty, and position.
     *
     * @param players the list of players to add the bot player to
     * @param chosenColor the color of the car for the bot player
     * @param difficulty the difficulty level for the bot player
     * @param position the starting position for the bot player
     */
    private void addPlayer(List<Player> players, CarColour chosenColor, BotStrategy difficulty, Position position) {
        Car car = new RaceCar(chosenColor);
        Player player = new BotPlayer(car, difficulty);
        player.setPosition(position);
        players.add(player);
    }

}
