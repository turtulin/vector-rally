package it.unicam.cs.mpmgc.vectorrally.api.controller;

import it.unicam.cs.mpmgc.vectorrally.api.model.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Vector;

import java.util.Random;

public class EasyPathSolver implements PathSolver {
    private Random random = new Random();

    @Override
    public Vector calculateNextMove(Vector currentPosition) {
        int deltaX = random.nextInt(3) - 1; // Random move between -1, 0, 1
        int deltaY = random.nextInt(3) - 1;
        return currentPosition.add(new Move(deltaX, deltaY));
    }
}
