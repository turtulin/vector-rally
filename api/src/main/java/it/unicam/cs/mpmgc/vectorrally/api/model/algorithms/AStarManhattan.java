package it.unicam.cs.mpmgc.vectorrally.api.model.algorithms;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;

import java.util.*;

public class AStarManhattan implements AStar {
    private final RaceTrack raceTrack;

    public AStarManhattan(RaceTrack raceTrack) {
        this.raceTrack = raceTrack;
    }

    @Override
    public List<Position> findPath(Position start, Position goal) {
        PriorityQueue<Node> openList = new PriorityQueue<>();
        Map<Position, Node> allNodes = new HashMap<>();
        Node startNode = createStartNode(start, goal, openList, allNodes);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();
            if (currentNode.getPosition().equals(goal)) {
                return reconstructPath(currentNode);
            }
            exploreNeighbors(currentNode, goal, openList, allNodes);
        }

        return new ArrayList<>();
    }

    @Override
    public double calculateCost(Position start, Position goal) {
        List<Position> path = findPath(start, goal);
        return path.size() > 0 ? path.size() - 1 : Double.MAX_VALUE;
    }

    private Node createStartNode(Position start, Position goal, PriorityQueue<Node> openList, Map<Position, Node> allNodes) {
        Node startNode = new Node(start);
        startNode.setGCost(0);
        startNode.setHCost(calculateHeuristic(start, goal));
        openList.add(startNode);
        allNodes.put(start, startNode);
        return startNode;
    }

    private void exploreNeighbors(Node currentNode, Position goal, PriorityQueue<Node> openList, Map<Position, Node> allNodes) {
        for (Node neighbor : getNeighbors(currentNode, goal)) {
            double tentativeGCost = currentNode.getGCost() + calculateDistance(currentNode.getPosition(), neighbor.getPosition());
            if (tentativeGCost < neighbor.getGCost()) {
                updateNeighbor(neighbor, currentNode, tentativeGCost, goal);
                if (!openList.contains(neighbor)) {
                    openList.add(neighbor);
                }
            }
        }
    }

    private void updateNeighbor(Node neighbor, Node currentNode, double tentativeGCost, Position goal) {
        neighbor.setParent(currentNode);
        neighbor.setGCost(tentativeGCost);
        neighbor.setHCost(calculateHeuristic(neighbor.getPosition(), goal));
    }

    private double calculateHeuristic(Position position, Position goal) {
        return Math.abs(position.getX() - goal.getX()) + Math.abs(position.getY() - goal.getY());
    }

    private double calculateDistance(Position start, Position end) {
        int dx = end.getX() - start.getX();
        int dy = end.getY() - start.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    private List<Node> getNeighbors(Node node, Position goal) {
        List<Node> neighbors = new ArrayList<>();
        int x = node.getPosition().getX();
        int y = node.getPosition().getY();
        int[][] directions = {
                {0, -1}, {0, 1}, {-1, 0}, {1, 0}
        };

        for (int[] direction : directions) {
            addNeighborIfValid(neighbors, x + direction[0], y + direction[1], goal);
        }

        return neighbors;
    }

    private void addNeighborIfValid(List<Node> neighbors, int newX, int newY, Position goal) {
        if (raceTrack.isInBounds(newX, newY) && (raceTrack.getComponentAt(newX, newY) == TrackComponent.ROAD
                || (newX == goal.getX() && newY == goal.getY()))) {
            neighbors.add(new Node(new Position(newX, newY)));
        }
    }

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
