package it.unicam.cs.mpmgc.vectorrally.api.model.strategies;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.AStar;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

import java.util.List;


/**
 * Implements the {@link DecisionStrategy} interface for the hard difficulty level.
 * This strategy uses the A* algorithm to choose the best move from the list of possible moves.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class HardBotStrategy implements DecisionStrategy {
    private final AStar aStar;

    /**
     * Constructs a HardBotStrategy with the specified A* algorithm implementation.
     *
     * @param aStar the A* algorithm implementation used to evaluate moves.
     */
    public HardBotStrategy(AStar aStar) {
        this.aStar = aStar;
    }

    @Override
    public Move decideMove(Player player, List<Move> possibleMoves) {
        Move bestMove = possibleMoves.getFirst();
        double minCost = Double.MAX_VALUE;
        for (Move move : possibleMoves) {
            double cost = aStar.calculateCost(move.position(), move.getDestination(), player.getPlayerAcceleration());
            if (cost < minCost) {
                minCost = cost;
                bestMove = move;
            }
        }
        return bestMove;
    }
}
