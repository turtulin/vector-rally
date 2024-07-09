package it.unicam.cs.mpmgc.vectorrally.api.controller;

import it.unicam.cs.mpmgc.vectorrally.api.model.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Vector;

public class MediumPathSolver implements PathSolver {
    @Override
    public Vector calculateNextMove(Vector currentPosition) {
        // Simplified longest path logic
        // In a real implementation, this would involve more sophisticated pathfinding
        return currentPosition.add(new Move(1, 1)); // Example move
    }
}
