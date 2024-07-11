package it.unicam.cs.mpmgc.vectorrally.api.model.algorithms;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMoveValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the MoveGenerator interface, providing a method to generate all possible moves for a player from a given position.
 *
 * @param <T> the type of ShiftGenerator used to generate possible shifts.
 *
 * @version 1.0
 * @since 2024-07-11
 */
public class BasicMoveGenerator<T extends NeighborsGenerator> implements MoveGenerator {
    private final T neighborsGenerator;

    public BasicMoveGenerator(T neighborsGenerator) {
        this.neighborsGenerator = neighborsGenerator;
    }

    @Override
    public List<Position> generatePossibleDestinations(Move move) {
        List<Position> possibleDestinations = new ArrayList<>();
        Position start = (Position) move.position();
        int dx = move.acceleration().getDx();
        int dy = move.acceleration().getDy();

        List<Position> shifts = neighborsGenerator.generateShifts(start);
        for (Position shift : shifts) {
            int newX = shift.getX() + dx;
            int newY = shift.getY() + dy;
            possibleDestinations.add(new Position(newX, newY));
        }

        return possibleDestinations;
    }
}
