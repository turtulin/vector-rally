package it.unicam.cs.mpmgc.vectorrally.api.controller;

import it.unicam.cs.mpmgc.vectorrally.api.model.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Vector;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HardPathSolver implements PathSolver {
    @Override
    public Vector calculateNextMove(Vector currentPosition) {
        PriorityQueue<Vector> openSet = new PriorityQueue<>(Comparator.comparingInt(this::heuristic));
        openSet.add(currentPosition);
        Vector bestMove = currentPosition.add(new Move(1, 1));
        while (!openSet.isEmpty()) {
            Vector current = openSet.poll();
            if (isGoal(current)) {
                return current;
            }
            for (Vector neighbor : getNeighbors(current)) {
                openSet.add(neighbor);
            }
        }
        return bestMove;
    }

    private int heuristic(Vector vector) {
        return vector.getX() + vector.getY();
    }

    private boolean isGoal(Vector vector) {
        return false;
    }

    private Vector[] getNeighbors(Vector vector) {
        return new Vector[]{
                vector.add(new Move(1, 0)),
                vector.add(new Move(0, 1)),
                vector.add(new Move(-1, 0)),
                vector.add(new Move(0, -1))
        };
    }
}
