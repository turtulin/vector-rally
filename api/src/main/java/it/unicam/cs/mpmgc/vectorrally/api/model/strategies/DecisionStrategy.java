package it.unicam.cs.mpmgc.vectorrally.api.model.strategies;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

import java.util.List;

/**
 * Defines the strategy for making decisions in the Vector Rally game. Implementations of this
 * interface provide the logic to decide the next move for a player based on a given list of possible moves.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface DecisionStrategy {
    /**
     * Decides the next move for the player based on the strategy.
     *
     * @param player the player for whom to decide the next move.
     * @param possibleMoves a list of possible moves the player can make.
     * @return the chosen move.
     */
    Move decideMove(Player player, List<Move> possibleMoves);
}
