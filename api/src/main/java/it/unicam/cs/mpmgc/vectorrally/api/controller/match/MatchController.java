package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.List;

/**
 * Defines methods for controlling the logic and flow of a game match.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface MatchController {

    /**
     * Initializes the match with the given players and racetrack.
     *
     * @param players the list of players participating in the match
     * @param raceTrack the racetrack on which the match will be played
     */
    void initializeMatch(List<Player> players, RaceTrack raceTrack);

    /**
     * Starts the match and manages the game loop.
     *
     * @throws Exception if an error occurs during the match
     */
    void startMatch() throws Exception;

    /**
     * Handles the turn for the given player.
     *
     * @param player the player whose turn is to be handled
     * @throws Exception if an error occurs during the player's turn
     */
    void handleTurn(Player player) throws Exception;

    /**
     * Handles the elimination of the given player.
     *
     * @param player the player to be eliminated
     * @throws Exception if an error occurs during the player's elimination
     */
    void handleElimination(Player player) throws Exception;

}
