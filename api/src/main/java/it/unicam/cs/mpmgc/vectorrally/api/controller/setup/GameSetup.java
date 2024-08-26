package it.unicam.cs.mpmgc.vectorrally.api.controller.setup;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.Track;

import java.util.List;

/**
 * The {@code GameSetup} interface defines the methods required for setting up the essential elements of the Vector Rally game,
 * such as initializing players, the racetrack, and the shift algorithm.
 * Implementations of this interface are responsible for configuring the game's initial state before the match begins.
 * <p>
 * Implementations of {@code GameSetup} should handle the process of setting up a list of players, constructing
 * the track on which they will compete, and determining the algorithm used for generating possible moves.</p>
 *
 * @version 1.0
 * @since 2024-08-20
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface GameSetup {

    /**
     * Initializes and returns a list of players for the game.
     *
     * @param raceTrack the {@link Track} on which the players will race.
     * @return a {@link List} of initialized {@link Player} objects.
     */
    List<Player> initializePlayers(Track raceTrack);

    /**
     * Initializes and returns the racetrack for the game.
     *
     * @return the initialized {@link Track}.
     * @throws Exception if an error occurs during initialization.
     */
    Track initializeTrack() throws Exception;

    /**
     * Initializes and returns the neighbors generator algorithm to be used during the game.
     * The neighbors generator algorithm is responsible for determining possible moves for players.
     *
     * @return the {@link NeighborsGenerator} object representing the shift algorithm.
     */
    NeighborsGenerator initializeShiftAlgorithm();
}
