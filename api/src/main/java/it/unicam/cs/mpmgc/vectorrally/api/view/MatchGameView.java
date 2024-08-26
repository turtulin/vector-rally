package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Coordinates;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.Track;

import java.util.List;

/**
 * This interface defines the methods for managing the game's match view in the Vector Rally application.
 * It provides methods for displaying the game state, interacting with the player, and managing turns.
 *
 * @version 1.0
 * @since 2024-08-21
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface MatchGameView {

    /**
     * Displays the possible moves available to the players on the given racetrack.
     *
     * @param players the list of {@link Player} objects participating in the match.
     * @param raceTrack the {@link Track} where the race is taking place.
     * @param possibleDestinations the list of {@link Coordinates} representing possible move destinations.
     */
    void displayPossibleMoves(List<Player> players, Track raceTrack, List<Coordinates> possibleDestinations);

    /**
     * Displays the winner of the match.
     *
     * @param winner the {@link Player} who has won the match.
     */
    void displayWinner(Player winner);

    /**
     * Displays the game over screen when no players remain in the match.
     */
    void displayGameOver();

    /**
     * Displays the current turn information, including the player and the turn counter.
     *
     * @param player the {@link Player} whose turn it is.
     * @param counter the current turn number.
     */
    void displayTurn(Player player, int counter);

    /**
     * Displays an elimination message when a player is removed from the match.
     *
     * @param player the {@link Player} who has been eliminated.
     */
    void displayElimination(Player player);

    /**
     * Allows the player to choose a move from a list of possible moves.
     *
     * @param possibleMoves the list of {@link Move} objects representing possible moves.
     * @return the {@link Move} chosen by the player.
     */
    Move getMoveChoice(List<Move> possibleMoves);

    /**
     * Proceeds to the next turn in the match.
     */
    void goToNextTurn();
}
