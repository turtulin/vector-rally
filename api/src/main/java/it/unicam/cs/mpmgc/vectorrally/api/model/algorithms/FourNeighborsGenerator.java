package it.unicam.cs.mpmgc.vectorrally.api.model.algorithms;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import java.util.List;


/**
 * This class implements the NeighborsGenerator interface, providing a method to generate shifts
 * based on the four-neighbors algorithm.
 *
 * @version 1.0
 * @since 2024-07-11
 */
public class FourNeighborsGenerator implements NeighborsGenerator {
    @Override
    public List<Acceleration> generateShifts(Acceleration speed) {
        int[] dx = {-1, 1, 0, 0, 0};
        int[] dy = {0, 0, -1, 1, 0};
        return NeighborsGenerator.getAccelerations(speed, dx, dy);
    }
}
