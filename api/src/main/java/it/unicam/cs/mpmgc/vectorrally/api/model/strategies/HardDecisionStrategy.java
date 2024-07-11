package it.unicam.cs.mpmgc.vectorrally.api.model.strategies;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.AStar;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

import java.util.List;

/**
 * This class implements the DecisionStrategy interface for the hard difficulty level.
 * The strategy uses the A* algorithm to choose the best move from the list of possible moves.
 *
 * @version 1.0
 * @since 2024-07-11
 */
public class HardDecisionStrategy implements DecisionStrategy {
    private final AStar aStarAlgorithm;

    /**
     * Constructs a HardDecisionStrategy with the specified A* algorithm.
     *
     * @param aStarAlgorithm the A* algorithm to use for decision-making.
     */
    public HardDecisionStrategy(AStar aStarAlgorithm) {
        this.aStarAlgorithm = aStarAlgorithm;
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
    public Position decideMove(Player player, List<Position> possibleMoves) {
        if (possibleMoves.isEmpty()) throw new IllegalArgumentException("No possible moves available for the player.");
        Position bestMove = possibleMoves.getFirst();
        double minCost = Double.MAX_VALUE;
        for (Position move : possibleMoves) {
            double cost = aStarAlgorithm.calculateCost(player.getPosition(), move);
            if (cost < minCost) {
                minCost = cost;
                bestMove = move;
            }
        }
        return bestMove;
    }
}
