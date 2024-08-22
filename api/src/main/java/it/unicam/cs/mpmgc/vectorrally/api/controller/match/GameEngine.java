package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.GameSetup;
import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.SetupResult;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.List;

/**
 * This interface defines methods for controlling the game engine.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface GameEngine {

    /**
     * Starts the game application.
     *
     * @throws Exception if an error occurs while starting the game.
     */
    void startGame() throws Exception;

    /**
     * Sets up the match by initializing the track, players, and shift algorithm.
     * Repeats the setup process until the user confirms the configuration.
     *
     * @return the result of the setup containing the neighbors generator, racetrack, and players
     * @throws Exception if an error occurs during setup
     */
    SetupResult setupMatch() throws Exception;


    /**
     * Starts the match with the given players, racetrack, and neighbors generator.
     *
     * @param players the list of players participating in the match
     * @param raceTrack the racetrack on which the match is played
     * @param neighborsGenerator the generator for neighboring moves
     * @throws Exception if an error occurs during match initialization or execution
     */
    void initializeMatch(List<Player> players, RaceTrack raceTrack, NeighborsGenerator neighborsGenerator) throws Exception;

    /**
     * Handles the end of the match, displaying an end message and asking the user if they want to play another match.
     *
     * @return true if the user wants to play another match, false otherwise
     */
    boolean endGame();
}
