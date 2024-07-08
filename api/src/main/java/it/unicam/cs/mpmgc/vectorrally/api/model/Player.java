package it.unicam.cs.mpmgc.vectorrally.api.model;

public interface Player {
    Coordinates getPosition();
    void makeMove(Shift shift);
}
