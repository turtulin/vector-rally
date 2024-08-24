package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

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
     * Starts the match and manages the game loop.
     *
     */
    void startMatch();

    /**
     * Handles the turn for the given player.
     *
     * @param player the player whose turn is to be handled
     */
    void handleTurn(Player player);

    /**
     * Handles the elimination of the given player.
     *
     * @param player the player to be eliminated
     */
    void handleElimination(Player player);

    void handleEndGame();

    boolean isGameEnded();

    void setGameEnded(boolean isGameEnded);

    Move findMove(Player player, List<Move> possibleMoves);
}
