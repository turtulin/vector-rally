package it.unicam.cs.mpmgc.vectorrally.api.model.strategies;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

import java.util.List;
import java.util.Random;

/**
 * This class implements the DecisionStrategy interface for the easy difficulty level.
 * The strategy chooses a random move from the list of possible moves.
 *
 * @version 1.0
 * @since 2024-07-11
 */
public class EasyDecisionStrategy implements DecisionStrategy {
    private final Random random;

    /**
     * Constructs an EasyDecisionStrategy with a new random number generator.
     */
    public EasyDecisionStrategy() {
        this.random = new Random();
    }

    /**
     * Decides the next move for the player by choosing a random move from the list of possible moves.
     *
     * @param player the player for whom to decide the next move.
     * @param possibleMoves a list of possible moves the player can make.
     * @return the chosen move.
     * @throws IllegalArgumentException if no possible moves are available.
     */
    @Override
    public Position decideMove(Player player, List<Position> possibleMoves) {
        if (possibleMoves.isEmpty()) {
            throw new IllegalArgumentException("No possible moves available for the player.");
        }
        return possibleMoves.get(random.nextInt(possibleMoves.size()));
    }
}
