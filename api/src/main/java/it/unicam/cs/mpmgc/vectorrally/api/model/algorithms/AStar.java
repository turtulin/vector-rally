package it.unicam.cs.mpmgc.vectorrally.api.model.algorithms;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Coordinates;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiFunction;

/**
 * Implements the A* pathfinding algorithm. The algorithm uses a heuristic function to
 * estimate the cost from the start node to the goal node and considers possible accelerations.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class AStar {
    private final BiFunction<Coordinates, Coordinates, Float> heuristic;
    private final NeighborsGenerator neighborsGenerator;

    public AStar(BiFunction<Coordinates, Coordinates, Float> heuristic, NeighborsGenerator neighborsGenerator) {
        this.heuristic = heuristic;
        this.neighborsGenerator = neighborsGenerator;
    }

    /**
     * Finds the shortest path from the start position to the goal position using the A* algorithm.
     *
     * @param start the starting {@link Coordinates}.
     * @param goal the goal {@link Coordinates}.
     * @param initialAcceleration the initial {@link Vector} acceleration of the player.
     * @return a {@link List} of {@link Coordinates} representing the path from the start to the goal, or an empty {@link List} if no path is found.
     */
    public List<Coordinates> findPath(Coordinates start, Coordinates goal, Vector initialAcceleration) {
        PriorityQueue<Node> openList = new PriorityQueue<>(100, (a, b) -> (int) (a.getFCost() - b.getFCost()));
        initialize(start, goal, openList);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();
            if (currentNode.getPosition().equals(goal)) {
                return reconstructPath(currentNode);
            }
            exploreNeighbors(currentNode, goal, initialAcceleration, openList);
        }

        return new ArrayList<>();
    }

    /**
     * Calculates the cost of the shortest path from the start position to the goal position.
     *
     * @param start the starting {@link Coordinates}.
     * @param goal the goal {@link Coordinates}.
     * @param initialAcceleration the initial {@link Vector} acceleration of the player.
     * @return the cost of the path, or Double.MAX_VALUE if no path is found.
     */
    public double calculateCost(Coordinates start, Coordinates goal, Vector initialAcceleration) {
        List<Coordinates> path = findPath(start, goal, initialAcceleration);
        return !path.isEmpty() ? path.size() - 1 : Double.MAX_VALUE;
    }

    /**
     * Calculates the heuristic cost from the given position to the goal position.
     *
     * @param position the current {@link Coordinates}.
     * @param goal the goal {@link Coordinates}.
     * @return the heuristic cost.
     */
    public static float calculateHeuristic(Coordinates position, Coordinates goal) {
        return Math.abs(position.getX() - goal.getX()) + Math.abs(position.getY() - goal.getY());
    }

    private void initialize(Coordinates start, Coordinates goal, PriorityQueue<Node> openList) {
        Node startNode = new Node(start);
        startNode.setGCost(0);
        startNode.setHCost(this.heuristic.apply(start, goal));
        openList.add(startNode);
    }

    private void exploreNeighbors(Node currentNode, Coordinates goal, Vector currentAcceleration, PriorityQueue<Node> openList) {
        for (Vector shift : neighborsGenerator.generateShifts(currentAcceleration)) {
            Position newPos = new Position(
                    currentNode.getPosition().getX() + shift.getDx(),
                    currentNode.getPosition().getY() + shift.getDy()
            );
            Node neighbor = new Node(newPos);
            double tentativeGCost = currentNode.getGCost() + calculateDistance(currentNode.getPosition(), neighbor.getPosition());
            if (tentativeGCost < neighbor.getGCost()) {
                updateNeighbor(neighbor, currentNode, tentativeGCost, goal);
                if (!openList.contains(neighbor)) {
                    openList.add(neighbor);
                }
            }
        }
    }

    private void updateNeighbor(Node neighbor, Node currentNode, double tentativeGCost, Coordinates goal) {
        neighbor.setParent(currentNode);
        neighbor.setGCost(tentativeGCost);
        neighbor.setHCost(this.heuristic.apply(neighbor.getPosition(), goal));
    }

    private double calculateDistance(Coordinates start, Coordinates end) {
        int dx = end.getX() - start.getX();
        int dy = end.getY() - start.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    private List<Coordinates> reconstructPath(Node currentNode) {
        List<Coordinates> path = new ArrayList<>();
        while (currentNode != null) {
            path.add(currentNode.getPosition());
            currentNode = currentNode.getParent();
        }
        Collections.reverse(path);
        return path;
    }
}
