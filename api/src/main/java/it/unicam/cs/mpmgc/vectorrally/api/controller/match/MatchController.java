package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

import java.util.List;

/**
 * This interface defines methods for controlling the logic and flow of a game match.
 * It manages the progression of the game, including player turns, move selections,
 * and determining when the game ends.
 *
 * @version 1.0
 * @since 2024-08-20
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface MatchController {

    /**
     * Starts the match and manages the main game loop, which continues until
     * the game is concluded.
     */
    void startMatch();

    /**
     * Handles the actions required for a player's turn, including move selection
     * and applying game rules.
     *
     * @param player the {@link Player} whose turn is currently being handled.
     */
    void handleTurn(Player player);

    /**
     * Manages the process of eliminating a player from the game, including
     * any necessary updates to the game state and notifying other players.
     *
     * @param player the {@link Player} to be eliminated.
     */
    void handleElimination(Player player);

    /**
     * Handles the end-of-game processes, such as determining the final game state.
     */
    void handleEndGame();

    /**
     * Checks whether the game is currently ongoing.
     *
     * @return {@code true} if the game is still ongoing, {@code false} if it has ended.
     */
    boolean isGameOn();

    /**
     * Sets the state of the game to either ongoing or ended.
     *
     * @param isGameOn {@code true} to indicate the game is ongoing, {@code false} to indicate it has ended.
     */
    void setGameOn(boolean isGameOn);

    /**
     * Determines the move for a given player based on the list of possible moves.
     * This could involve player input or AI decision-making.
     *
     * @param player the {@link Player} for whom the move is being determined.
     * @param possibleMoves the {@link List} of possible {@link Move}s available to the player.
     * @return the {@link Move} that the player chooses or is determined by AI.
     */
    Move findMove(Player player, List<Move> possibleMoves);

    /**
     * Retrieves the turn handler for the match, which manages player turns.
     *
     * @return the {@link TurnHandler} for the match.
     */
    TurnHandler getTurnHandler();
}
