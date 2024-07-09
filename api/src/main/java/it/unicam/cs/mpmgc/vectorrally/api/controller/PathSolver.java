package it.unicam.cs.mpmgc.vectorrally.api.controller;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Vector;

public interface PathSolver {
    Vector calculateNextMove(Vector currentPosition);
}
