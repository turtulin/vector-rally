package it.unicam.cs.mpmgc.vectorrally.api.model;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Vector;

public class Move implements Vector {
    private final int x;
    private final int y;

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Vector add(Vector other) {
        return new Move(this.x + other.getX(), this.y + other.getY());
    }

}
