package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

import java.util.List;

/**
 * This interface defines the contract for managing the turns in a game.
 * Implementations of this interface are responsible for controlling the flow of
 * turns, tracking the current player, handling player eliminations, and maintaining
 * the sequence of players throughout the game.
 * <p>
 * The {@code TurnHandler} interface provides methods to start and end a turn,
 * retrieve the current player and turn counter, get the list of active players,
 * and remove a player from the game.
 *
 * @version 1.0
 * @since 2024-08-20
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface TurnHandler {

    /**
     * Initiates the actions required to start a new turn. This method is called
     * at the beginning of each player's turn to prepare the game state and
     * perform any necessary setup before the player takes their action.
     */
    void startTurn();

    /**
     * Concludes the current turn. This method should be called after the player
     * has completed their action for the turn, finalizing any updates to the
     * game state before the next player's turn begins.
     */
    void endTurn();

    /**
     * Returns the player whose turn is currently active. This method allows
     * retrieval of the player object representing the individual currently
     * taking their turn.
     *
     * @return the {@link Player} who is currently taking their turn
     */
    Player getCurrentPlayer();

    /**
     * Returns the current turn number in the game. This method provides a count
     * of how many turns have been completed, which can be useful for tracking
     * the progress of the game.
     *
     * @return the number of turns that have been completed
     */
    int getTurnCounter();

    /**
     * Returns a list of all active players remaining in the game. This method
     * provides access to the current list of players who are still participating
     * in the game.
     *
     * @return a {@link List} of active {@link Player} objects
     */
    List<Player> getPlayers();

    /**
     * Removes the specified player from the game. This method is typically called
     * when a player is eliminated from the game, ensuring they no longer participate
     * in subsequent turns.
     *
     * @param player the {@link Player} to be removed from the game
     */
    void removePlayer(Player player);
}
