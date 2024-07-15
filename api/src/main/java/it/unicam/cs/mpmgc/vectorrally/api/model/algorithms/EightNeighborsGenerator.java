package it.unicam.cs.mpmgc.vectorrally.api.model.algorithms;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the NeighborsGenerator interface, providing a method to generate shifts based on the eight-neighbors algorithm.
 *
 * @version 1.0
 * @since 2024-07-11
 */
public class EightNeighborsGenerator implements NeighborsGenerator {
    @Override
    public List<Acceleration> generateShifts(Acceleration speed) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1, 0};
        List<Acceleration> shifts = new ArrayList<>();
        for (int i = 0; i < dx.length; i++) {
            shifts.add(new Acceleration(speed.getDy() + dy[i], speed.getDy() + dy[i]));
        }
        return shifts;
    }
}
