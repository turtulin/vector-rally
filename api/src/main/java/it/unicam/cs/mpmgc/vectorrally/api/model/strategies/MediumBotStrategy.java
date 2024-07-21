package it.unicam.cs.mpmgc.vectorrally.api.model.strategies;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

import java.util.List;

/**
 * Implements the {@link DecisionStrategy} interface for the medium difficulty level.
 * This strategy chooses the longest move from the list of possible moves that does not lead to a crash.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class MediumBotStrategy implements DecisionStrategy {

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

    /**
     * Calculates the Euclidean distance between two positions.
     *
     * @param position the starting position.
     * @param destination the destination position.
     * @return the Euclidean distance between the two positions.
     */
    private double distance(Position position, Position destination) {
        return Math.sqrt(Math.pow(destination.getX() - position.getX(), 2) + Math.pow(destination.getY() - position.getY(), 2));
    }
}
