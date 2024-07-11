package it.unicam.cs.mpmgc.vectorrally.api.model.algorithms;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;

import java.util.List;

public interface MoveGenerator {

    /**
     * Generates a list of possible destination positions from the given move.
     *
     * @param move the move containing the start position and acceleration.
     * @return a list of possible destination positions.
     */
    List<Position> generatePossibleDestinations(Move move);
}
