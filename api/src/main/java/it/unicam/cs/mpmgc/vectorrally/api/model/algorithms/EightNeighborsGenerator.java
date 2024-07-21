package it.unicam.cs.mpmgc.vectorrally.api.model.algorithms;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;

import java.util.List;

/**
 * Implements the {@link NeighborsGenerator} interface, providing a method to generate shifts
 * based on the eight-neighbors algorithm. This generator produces possible accelerations in
 * the eight cardinal and inter cardinal directions (up, down, left, right, and the four diagonals),
 * as well as no acceleration (stationary).
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class EightNeighborsGenerator implements NeighborsGenerator {
    @Override
    public List<Acceleration> generateShifts(Acceleration speed) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1, 0};
        return getAccelerations(speed, dx, dy);
    }
}
