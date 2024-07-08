package it.unicam.cs.mpmgc.vectorrally.api.controller;
import it.unicam.cs.mpmgc.vectorrally.api.model.Coordinates;
import it.unicam.cs.mpmgc.vectorrally.api.model.Shift;

public interface PathSolver {
    Coordinates solvePath(Coordinates currentPos, Shift shift);
}
