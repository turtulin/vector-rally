package it.unicam.cs.mpmgc.vectorrally.api.model.algorithms;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;

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
    private final BiFunction<Position, Position, Float> heuristic;
    private final NeighborsGenerator neighborsGenerator;

    /**
     * Constructs an AStar algorithm instance with the specified heuristic function.
     *
     * @param heuristic the heuristic function used to estimate the cost from a node to the goal.
     * @param neighborsGenerator the generator used to produce possible accelerations.
     */
    public AStar(BiFunction<Position, Position, Float> heuristic, NeighborsGenerator neighborsGenerator) {
        this.heuristic = heuristic;
        this.neighborsGenerator = neighborsGenerator;
    }

    /**
     * Finds the shortest path from the start position to the goal position using the A* algorithm.
     *
     * @param start the starting position.
     * @param goal the goal position.
     * @param initialAcceleration the initial acceleration of the player.
     * @return a list of positions representing the path from the start to the goal, or an empty list if no path is found.
     */
    public List<Position> findPath(Position start, Position goal, Acceleration initialAcceleration) {
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
     * @param start the starting position.
     * @param goal the goal position.
     * @param initialAcceleration the initial acceleration of the player.
     * @return the cost of the path, or Double.MAX_VALUE if no path is found.
     */
    public double calculateCost(Position start, Position goal, Acceleration initialAcceleration) {
        List<Position> path = findPath(start, goal, initialAcceleration);
        return !path.isEmpty() ? path.size() - 1 : Double.MAX_VALUE;
    }

    /**
     * Initializes the open list with the start node.
     *
     * @param start the starting position.
     * @param goal the goal position.
     * @param openList the priority queue used to store nodes to be evaluated.
     */
    private void initialize(Position start, Position goal, PriorityQueue<Node> openList) {
        Node startNode = new Node(start);
        startNode.setGCost(0);
        startNode.setHCost(this.heuristic.apply(start, goal));
        openList.add(startNode);
    }

    /**
     * Explores the neighbors of the current node and adds them to the open list if they provide a better path.
     *
     * @param currentNode the current node being evaluated.
     * @param goal the goal position.
     * @param currentAcceleration the current acceleration of the player.
     * @param openList the priority queue used to store nodes to be evaluated.
     */
    private void exploreNeighbors(Node currentNode, Position goal, Acceleration currentAcceleration, PriorityQueue<Node> openList) {
        for (Acceleration shift : neighborsGenerator.generateShifts(currentAcceleration)) {
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

    /**
     * Updates the neighbor node with new gCost, hCost, and parent if a better path is found.
     *
     * @param neighbor the neighbor node being updated.
     * @param currentNode the current node being evaluated.
     * @param tentativeGCost the tentative cost from the start node to the neighbor node.
     * @param goal the goal position.
     */
    private void updateNeighbor(Node neighbor, Node currentNode, double tentativeGCost, Position goal) {
        neighbor.setParent(currentNode);
        neighbor.setGCost(tentativeGCost);
        neighbor.setHCost(this.heuristic.apply(neighbor.getPosition(), goal));
    }

    /**
     * Calculates the Manhattan distance heuristic between two positions.
     *
     * @param position the starting position.
     * @param goal the goal position.
     * @return the Manhattan distance between the positions.
     */
    public static float calculateHeuristic(Position position, Position goal) {
        return Math.abs(position.getX() - goal.getX()) + Math.abs(position.getY() - goal.getY());
    }

    /**
     * Calculates the Euclidean distance between two positions.
     *
     * @param start the starting position.
     * @param end the ending position.
     * @return the Euclidean distance between the positions.
     */
    private double calculateDistance(Position start, Position end) {
        int dx = end.getX() - start.getX();
        int dy = end.getY() - start.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Reconstructs the path from the goal node to the start node by following the parent links.
     *
     * @param currentNode the goal node from which to start reconstructing the path.
     * @return a list of positions representing the path from the start to the goal.
     */
    private List<Position> reconstructPath(Node currentNode) {
        List<Position> path = new ArrayList<>();
        while (currentNode != null) {
            path.add(currentNode.getPosition());
            currentNode = currentNode.getParent();
        }
        Collections.reverse(path);
        return path;
    }
}
