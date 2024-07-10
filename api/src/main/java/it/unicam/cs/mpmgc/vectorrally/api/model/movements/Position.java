package it.unicam.cs.mpmgc.vectorrally.api.model.movements;

/**
 * This class represents a position in the graph paper axis.
 * It implements the {@link Coordinates} interface.
 *
 * @version 1.0
 * @since 2024-07-10
 */
public class Position implements Coordinates {
    private int x;

    private int y;

    /**
     * Constructs a Position with specified x and y values.
     *
     * @param x the x value.
     * @param y the y value.
     * @throws IllegalArgumentException if x or y are negative.
     */
    public Position(int x, int y) {
        if(x < 0 || y < 0) throw new IllegalArgumentException("x and y must be positive");
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    // Do I need a method to compare 2 positions?
    // public boolean equals(Position other) {
    //     return this.x == other.getX() && this.y == other.getY();
    // }



}
