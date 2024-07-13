package it.unicam.cs.mpmgc.vectorrally.api.model.strategies;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.AStar;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
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
public class HardBotStrategy implements DecisionStrategy {
    private final AStar aStarAlgorithm;

    /**
     * Constructs a HardBotStrategy with the specified A* algorithm.
     *
     * @param aStarAlgorithm the A* algorithm to use for decision-making.
     */
    public HardBotStrategy(AStar aStarAlgorithm) {
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
    public Move decideMove(Player player, List<Move> possibleMoves) {
        Move bestMove = possibleMoves.getFirst();
        double minCost = Double.MAX_VALUE;
        for (Move move : possibleMoves) {
            double cost = aStarAlgorithm.calculateCost(move.position(), move.getDestination());
            if (cost < minCost) {
                minCost = cost;
                bestMove = move;
            }
        }
        return bestMove;
    }
}
