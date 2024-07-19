package it.unicam.cs.mpmgc.vectorrally.api.model.strategies;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.AStarManhattan;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

import java.util.List;

/**
 * This class implements the DecisionStrategy interface for the hard difficulty level.
 * The strategy uses the A* algorithm to choose the best move from the list of possible moves.
 *
 * @version 1.0
 * @since 2024-07-11
 */
public class HardBotStrategy implements DecisionStrategy {
    private final AStarManhattan aStarManhattan;

    public HardBotStrategy(AStarManhattan aStarManhattan) {
        this.aStarManhattan = aStarManhattan;
    }

    /**
     * Decides the next move for the player by using the A* algorithm to choose the best move from the list of possible moves.
     *
     * @param player the player for whom to decide the next move.
     * @param possibleMoves a list of possible moves the player can make.
     * @return the chosen move.
     * @throws IllegalArgumentException if no possible moves are available.
     */
    @Override
    public Move decideMove(Player player, List<Move> possibleMoves) {
        Move bestMove = possibleMoves.getFirst();
        double minCost = Double.MAX_VALUE;
        for (Move move : possibleMoves) {
            double cost = aStarManhattan.calculateCost(move.position(), move.getDestination());
            if (cost < minCost) {
                minCost = cost;
                bestMove = move;
            }
        }
        return bestMove;
    }
}
