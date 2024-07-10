package it.unicam.cs.mpmgc.vectorrally.api.model.movements;

public class Acceleration implements Vector{
    private int dx;

    private int dy;

    public Acceleration(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return this.dx;
    }

    public int getDy() {
        return this.dy;
    }

    public void setDx(int x) {
        this.dx = dx;
    }

    public void setDy(int y) {
        this.dy = dy;
    }

}
