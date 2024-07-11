package it.unicam.cs.mpmgc.vectorrally.api.model.algorithms;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;

import java.util.ArrayList;
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
    public List<Position> generateShifts(Position position) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        List<Position> shifts = new ArrayList<>();
        for (int i = 0; i < dx.length; i++) {
            shifts.add(new Position(position.getX() + dx[i], position.getY() + dy[i]));
        }
        return shifts;
    }
}
