package it.unicam.cs.mpmgc.vectorrally.api.controller.setup;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.List;

/**
 * This interface defines methods for setting up the game.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface GameSetup {

    public NeighborsGenerator initializeShiftAlgorithm() throws Exception;

    public List<Player> initializePlayers(RaceTrack raceTrack) throws Exception;

    public RaceTrack initializeTrack() throws Exception;
}
