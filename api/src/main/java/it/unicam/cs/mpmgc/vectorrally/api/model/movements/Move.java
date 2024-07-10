package it.unicam.cs.mpmgc.vectorrally.api.model.movements;

public class Move implements Shift {
    private Acceleration speed;
    private Coordinates position;

    public Move(Acceleration speed, Coordinates position) {
        this.speed = speed;
        this.position = position;
    }

    public Acceleration speed() {
        return this.speed;
    }

    public Coordinates position() {
        return this.position;
    }

}
