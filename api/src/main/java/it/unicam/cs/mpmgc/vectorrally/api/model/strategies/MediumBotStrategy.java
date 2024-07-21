package it.unicam.cs.mpmgc.vectorrally.api.model.strategies;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

import java.util.List;

/**
 * This class implements the DecisionStrategy interface for the medium difficulty level.
 * The strategy chooses the longest move from the list of possible moves that does not lead to a crash.
 *
 * @version 1.0
 * @since 2024-07-11
 */
public class MediumBotStrategy implements DecisionStrategy {

    /**
     * Decides the next move for the player by choosing the longest move from the list of possible moves
     * that does not lead to a crash.
     *
     * @param player the player for whom to decide the next move.
     * @param possibleMoves a list of possible moves the player can make.
     * @return the chosen move.
     * @throws IllegalArgumentException if no possible moves are available.
     */
    @Override
    public Move decideMove(Player player, List<Move> possibleMoves) {
        Move bestMove = possibleMoves.getFirst();
        double maxDistance = 0;
        for (Move move : possibleMoves) {
            double distance = distance(move.position(), move.getDestination());
            if (distance > maxDistance) {
                maxDistance = distance;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private double distance(Position position, Position destination) {
        return Math.sqrt(Math.pow(destination.getX() - position.getX(), 2) + Math.pow(destination.getY() - position.getY(), 2));
    }
}
