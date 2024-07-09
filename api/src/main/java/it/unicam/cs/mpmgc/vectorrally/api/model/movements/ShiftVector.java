package it.unicam.cs.mpmgc.vectorrally.api.model.movements;

public class ShiftVector implements Vector{
    private int x;

    private int y;

    public ShiftVector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
