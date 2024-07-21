package it.unicam.cs.mpmgc.vectorrally.api.model.algorithms;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;

/**
 * Represents a node in the A* algorithm. Each node corresponds to a position on the racetrack
 * and contains the costs associated with reaching that position.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class Node implements Comparable<Node>{
    private final Position position;
    private double gCost;
    private double hCost;
    private Node parent;

    /**
     * Constructs a Node with the specified position.
     *
     * @param position the position represented by this node.
     */
    public Node(Position position) {
        this.position = position;
        this.gCost = Double.MAX_VALUE;
        this.hCost = 0;
    }

    /**
     * Retrieves the position represented by this node.
     *
     * @return the position.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Retrieves the G cost, which is the cost from the start node to this node.
     *
     * @return the G cost.
     */
    public double getGCost() {
        return gCost;
    }

    /**
     * Sets the G cost, which is the cost from the start node to this node.
     *
     * @param gCost the G cost to set.
     */
    public void setGCost(double gCost) {
        this.gCost = gCost;
    }

    /**
     * Retrieves the H cost, which is the estimated cost from this node to the goal node.
     *
     * @return the H cost.
     */
    public double getHCost() {
        return hCost;
    }

    /**
     * Sets the H cost, which is the estimated cost from this node to the goal node.
     *
     * @param hCost the H cost to set.
     */
    public void setHCost(double hCost) {
        this.hCost = hCost;
    }

    /**
     * Retrieves the F cost, which is the sum of the G cost and the H cost.
     *
     * @return the F cost.
     */
    public double getFCost() {
        return gCost + hCost;
    }

    /**
     * Retrieves the parent node, which is the previous node on the path from the start node.
     *
     * @return the parent node.
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Sets the parent node, which is the previous node on the path from the start node.
     *
     * @param parent the parent node to set.
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     * Compares this node to another node based on their F costs.
     *
     * @param other the other node to compare to.
     * @return a negative integer, zero, or a positive integer as this node's F cost is less than, equal to, or greater than the other node's F cost.
     */
    @Override
    public int compareTo(Node other) {
        return Double.compare(this.getFCost(), other.getFCost());
    }
}
