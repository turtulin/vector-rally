package it.unicam.cs.mpmgc.vectorrally.api.model.algorithms;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;

import java.util.List;

/**
 * This interface defines the A* algorithm for finding the shortest path in the vector rally game.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface AStar {
    /**
     * Finds the shortest path from the start position to the goal position using the A* algorithm.
     *
     * @param start the start position.
     * @param goal the goal position.
     * @return a list of positions representing the shortest path from start to goal, or an empty list if no path is found.
     */
    List<Position> findPath(Position start, Position goal);

    /**
     * Calculates the cost of moving from the start position to the goal position.
     *
     * @param start the start position.
     * @param goal the goal position.
     * @return the cost of moving from start to goal.
     */
    double calculateCost(Position start, Position goal);
}
