package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.SetupResult;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.Track;

import java.util.List;

/**
 * This interface defines the contract for a game engine that controls the
 * initialization, execution, and conclusion of a game match.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface GameEngine {

    /**
     * Starts the game application, initializing necessary components and
     * beginning the main game loop.
     *
     * @throws Exception if an error occurs while starting the game.
     */
    void startGame() throws Exception;

    /**
     * Sets up the match by initializing the track, players, and shift algorithm.
     * This method is responsible for preparing all necessary elements of the game
     * before the match begins. The setup process may be repeated until the
     * configuration is confirmed by the user.
     *
     * @return a {@link SetupResult} object containing the configured neighbors generator,
     *         racetrack, and players.
     * @throws Exception if an error occurs during setup.
     */
    SetupResult setupMatch() throws Exception;

    /**
     * Initializes the match with the provided players, racetrack, and neighbors generator.
     * This method prepares the game state and environment for the match to proceed.
     *
     * @param players the {@link List} of {@link Player} objects participating in the match.
     * @param raceTrack the {@link Track} on which the match is played.
     * @param neighborsGenerator the {@link NeighborsGenerator} used to determine possible moves
     *                           based on neighboring positions.
     */
    void initializeMatch(List<Player> players, Track raceTrack, NeighborsGenerator neighborsGenerator);

    /**
     * Handles the end of the match, determining whether the game should
     * continue or conclude. This method typically includes user interaction to
     * decide if a new match should be started.
     *
     * @return {@code true} if the user wants to play another match, {@code false} otherwise.
     */
    boolean playAgain();
}
