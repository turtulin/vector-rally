package it.unicam.cs.mpmgc.vectorrally.api.model.movements;

import java.util.Objects;

/**
 * This class represents the acceleration in the game, which is a vector quantity
 * having both magnitude and direction. It implements the {@link Vector} interface.
 *
 * @version 1.0
 * @since 2024-07-10
 */
public class Acceleration implements Vector {
    private int dx;
    private int dy;

    /**
     * Constructs an Acceleration with specified dx and dy values.
     *
     * @param dx the dx value, representing the change in the x direction.
     * @param dy the dy value, representing the change in the y direction.
     */
    public Acceleration(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public int getDx() {
        return this.dx;
    }

    @Override
    public int getDy() {
        return this.dy;
    }

    @Override
    public void setDx(int x) {
        this.dx = x;
    }

    @Override
    public void setDy(int y) {
        this.dy = y;
    }

    @Override
    public double getMagnitude() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public Direction getDirection() {
        return switch (Integer.signum(dx) + 3 * Integer.signum(dy)) {
            case 3 -> Direction.UP;
            case 4 -> Direction.UP_RIGHT;
            case -2 -> Direction.DOWN_RIGHT;
            case -3 -> Direction.DOWN;
            case -4 -> Direction.DOWN_LEFT;
            case -1 -> Direction.LEFT;
            case 2 -> Direction.UP_LEFT;
            case 1 -> Direction.RIGHT;
            case 0 -> Direction.NONE;
            default -> throw new IllegalStateException("Unexpected value: " + Integer.signum(dx) + ", " + Integer.signum(dy));
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acceleration that = (Acceleration) o;
        return dx == that.dx && dy == that.dy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dx, dy);
    }

    @Override
    public String toString() {
        return String.format("Acceleration(dx=%d, dy=%d)", this.dx, this.dy);
    }
}
