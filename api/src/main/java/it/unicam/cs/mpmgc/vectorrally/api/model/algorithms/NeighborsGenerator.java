package it.unicam.cs.mpmgc.vectorrally.api.model.algorithms;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * This interface defines a method for generating shifts from a given position.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface NeighborsGenerator {

    /**
     * Generates all possible shifts from the given position.
     * @return a list of possible new positions representing the shifts.
     */
    List<Acceleration> generateShifts(Acceleration speed);

    default List<Acceleration> getAccelerations(Acceleration speed, int[] dx, int[] dy) {
        List<Acceleration> shifts = new ArrayList<>();
        for (int i = 0; i < dx.length; i++) {
            shifts.add(new Acceleration(speed.getDx() + dx[i], speed.getDy() + dy[i]));
        }
        return shifts;
    }
}
