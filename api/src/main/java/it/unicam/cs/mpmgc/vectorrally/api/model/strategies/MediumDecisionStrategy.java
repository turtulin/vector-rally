package it.unicam.cs.mpmgc.vectorrally.api.model.strategies;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;

import java.util.List;

/**
 * This class implements the DecisionStrategy interface for the medium difficulty level.
 * The strategy chooses the longest move from the list of possible moves that does not lead to a crash.
 *
 * @version 1.0
 * @since 2024-07-11
 */
public class MediumDecisionStrategy implements DecisionStrategy {
    private final RaceTrack raceTrack;

    /**
     * Constructs a MediumDecisionStrategy with the specified race track.
     *
     * @param raceTrack the race track to use for decision making.
     */
    public MediumDecisionStrategy(RaceTrack raceTrack) {
        this.raceTrack = raceTrack;
    }

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
    public Position decideMove(Player player, List<Position> possibleMoves) {
        if (possibleMoves.isEmpty()) {
            throw new IllegalArgumentException("No possible moves available for the player.");
        }

        Position bestMove = possibleMoves.get(0);
        double maxDistance = 0;

        for (Position move : possibleMoves) {
            if (raceTrack.getComponentAt(move.getX(), move.getY()) != TrackComponent.WALL) {
                double distance = calculateDistance(player.getPosition(), move);
                if (distance > maxDistance) {
                    maxDistance = distance;
                    bestMove = move;
                }
            }
        }
        return bestMove;
    }

    /**
     * Calculates the Euclidean distance between two positions.
     *
     * @param start the starting position.
     * @param end the ending position.
     * @return the Euclidean distance between the two positions.
     */
    private double calculateDistance(Position start, Position end) {
        int dx = end.getX() - start.getX();
        int dy = end.getY() - start.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
