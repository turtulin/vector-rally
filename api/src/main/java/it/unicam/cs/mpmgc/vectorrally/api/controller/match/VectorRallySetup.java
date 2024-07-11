package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.io.Utils;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrackBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * This class implements the GameSetup interface, handling the setup process for the game.
 *
 * @version 1.0
 * @since 2024-07-11
 */
public class VectorRallySetup implements GameSetup {

    private RaceTrack raceTrack;
    private List<Player> players;
    private final Utils utilities;

    public VectorRallySetup(Utils utilities) {
        this.utilities = utilities;
    }

    @Override
    public void initializeGameSetup() {
        try {
            setupRaceTrack();
            setupPlayers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupRaceTrack() throws IOException {
        Scanner scanner = new Scanner(System.in);
        RaceTrackBuilder builder = new RaceTrackBuilder();
        System.out.println("Choose the racetrack file:");
        String filename = scanner.nextLine();
        raceTrack = builder.buildTrack(filename);
    }

    private void setupPlayers() {
        // Implementation for setting up players goes here
        // Use utilities to display options and get user input
    }

    @Override
    public RaceTrack getRaceTrack() {
        return raceTrack;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }
}
