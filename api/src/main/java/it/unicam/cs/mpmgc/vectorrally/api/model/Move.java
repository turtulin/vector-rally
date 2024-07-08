package it.unicam.cs.mpmgc.vectorrally.api.model;

public class Move implements Shift{
    private final int deltaX;
    private final int deltaY;

    public Move(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    @Override
    public int getDeltaX() { return deltaX; }

    @Override
    public int getDeltaY() { return deltaY; }

}
