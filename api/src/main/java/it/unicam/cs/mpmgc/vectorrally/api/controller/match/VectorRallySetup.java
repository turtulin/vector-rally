package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.io.TerminalIOController;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrackBuilder;

import java.io.IOException;
import java.util.List;

/**
 * This class implements the GameSetup interface, handling the setup process for the game.
 *
 * @version 1.0
 * @since 2024-07-11
 */
public class VectorRallySetup implements GameSetup {

    private final TerminalIOController ioController;
    private final RaceTrackBuilder trackBuilder;
    private RaceTrack raceTrack;
    private List<Player> players;

    public VectorRallySetup(TerminalIOController ioController, RaceTrackBuilder trackBuilder) {
        this.ioController = ioController;
        this.trackBuilder = trackBuilder;
    }

    @Override
    public void initializeGameSetupSequence() throws IOException {
        String trackFile = ioController.chooseTrack();
        this.raceTrack = trackBuilder.buildTrack(trackFile);
        int numHumanPlayers = ioController.getNumberOfHumanPlayers();
        int numBotPlayers = ioController.getNumberOfBotPlayers();
        this.players = ioController.setupPlayers(raceTrack, numHumanPlayers, numBotPlayers);
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
