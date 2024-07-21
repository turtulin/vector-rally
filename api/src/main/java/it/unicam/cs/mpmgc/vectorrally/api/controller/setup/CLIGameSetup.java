package it.unicam.cs.mpmgc.vectorrally.api.controller.setup;


import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.RaceCar;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.BotPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.HumanPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;
import it.unicam.cs.mpmgc.vectorrally.api.view.IOController;
import it.unicam.cs.mpmgc.vectorrally.api.view.TerminalUtils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class implements the GameSetup interface, handling the setup process for the game
 * through a command-line interface (CLI). It interacts with the user via the terminal to set up
 * the game's rules, players, and racetrack.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class CLIGameSetup implements GameSetup {
    private final IOController ioController;
    private final RaceTrackBuilder trackBuilder;
    private final TerminalUtils terminalUtils = new TerminalUtils();

    /**
     * Constructs a CLIGameSetup instance with the specified IO controller and track builder.
     *
     * @param ioController the IO controller for interacting with the user.
     * @param trackBuilder the track builder for constructing the racetrack.
     */
    public CLIGameSetup(IOController ioController, RaceTrackBuilder trackBuilder) {
        this.ioController = ioController;
        this.trackBuilder = trackBuilder;
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
        List<String> tracksFilePath = ioController.findTrack();
        String trackFilePath = ioController.pickTrack(tracksFilePath);
        return trackBuilder.buildTrack(trackFilePath);
    }

    /**
     * Sets up the human players based on user input.
     *
     * @param numHumanPlayers the number of human players to set up.
     * @param players the list of players to which the human players will be added.
     * @param availableColors the list of available car colors.
     */
    private void setupHumanPlayers(int numHumanPlayers, List<Player> players, List<CarColour> availableColors) {
        for (int i = 0; i < numHumanPlayers; i++) {
            CarColour chosenColor = ioController.chooseCarColor(availableColors);
            availableColors.remove(chosenColor);
            Car car = new RaceCar(chosenColor);
            Player humanPlayer = new HumanPlayer(car);
            players.add(humanPlayer);
        }
    }

    /**
     * Allows players to choose their starting positions on the racetrack.
     *
     * @param track the racetrack on which the players will race.
     * @param numHumanPlayers the number of human players.
     * @param players the list of players.
     * @param availablePositions the list of available starting positions.
     */
    private void chooseStartingPositions(RaceTrack track, int numHumanPlayers, List<Player> players, List<Position> availablePositions) {
        terminalUtils.printRaceTrack(track, players, availablePositions);
        for (int i = 0; i < numHumanPlayers; i++) {
            Position chosenPosition = ioController.chooseStartingPosition(players.get(i), availablePositions);
            availablePositions.remove(chosenPosition);
            players.get(i).setPosition(chosenPosition);
        }
    }

    /**
     * Sets up the bot players based on user input.
     *
     * @param remainingPositions the number of remaining positions for the bots.
     * @param players the list of players to which the bot players will be added.
     * @param availableColours the list of available car colors.
     * @param availablePositions the list of available starting positions.
     */
    private void setupBotPlayers(int remainingPositions, List<Player> players, List<CarColour> availableColours, List<Position> availablePositions) {
        boolean chooseForEachBot = ioController.askToChooseForEachBot();
        if (chooseForEachBot) {
            for (int i = 0; i < remainingPositions; i++) {
                CarColour chosenColor = availableColours.get(i);
                BotStrategy difficulty = ioController.chooseEachBotStrategyDifficulty(chosenColor);
                addPlayer(players, chosenColor, difficulty, availablePositions.get(i));
            }
        } else {
            BotStrategy difficulty = ioController.chooseAllBotsStrategyDifficulty();
            for (int i = 0; i < remainingPositions; i++) {
                CarColour chosenColor = availableColours.get(i);
                addPlayer(players, chosenColor, difficulty, availablePositions.get(i));
            }
        }
    }

    /**
     * Adds a player to the list of players.
     *
     * @param players the list of players.
     * @param chosenColor the chosen color for the player's car.
     * @param difficulty the difficulty level for the bot player (null for human players).
     * @param position the starting position for the player.
     */
    private void addPlayer(List<Player> players, CarColour chosenColor, BotStrategy difficulty, Position position) {
        Car car = new RaceCar(chosenColor);
        Player player = (difficulty == null) ? new HumanPlayer(car) : new BotPlayer(car, difficulty);
        player.setPosition(position);
        players.add(player);
    }
}
