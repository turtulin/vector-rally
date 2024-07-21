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
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface GameSetup {

    /**
     * Initializes and returns a list of players for the game.
     *
     * @param raceTrack the racetrack on which the players will race.
     * @return a list of initialized players.
     * @throws Exception if an error occurs during initialization.
     */
    List<Player> initializePlayers(RaceTrack raceTrack) throws Exception;

    /**
     * Initializes and returns the racetrack for the game.
     *
     * @return the initialized racetrack.
     * @throws Exception if an error occurs during initialization.
     */
    RaceTrack initializeTrack() throws Exception;
}
