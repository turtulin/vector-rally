package it.unicam.cs.mpmgc.vectorrally.api.model.algorithms;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
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
    private final BasicMoveValidator moveValidator;
    private final RaceTrack raceTrack;
    private final T shiftGenerator;
    /**
     * Constructs a BasicMoveGenerator with the specified move validator, race track, and shift generator.
     *
     * @param moveValidator the move validator to use for validating moves.
     * @param raceTrack the racetrack to use for generating moves.
     * @param shiftGenerator the shift generator to use for generating possible shifts.
     */
    public BasicMoveGenerator(BasicMoveValidator moveValidator, RaceTrack raceTrack, T shiftGenerator) {
        this.moveValidator = moveValidator;
        this.raceTrack = raceTrack;
        this.shiftGenerator = shiftGenerator;
    }

    @Override
    public List<Position> generateMoves(Player player, Position position) {
        List<Position> possibleMoves = new ArrayList<>();
        Acceleration playerAcceleration = player.getPlayerAcceleration();
        List<Position> shifts = shiftGenerator.generateShifts(position);
        for (Position shift : shifts) {
            Position newPosition = calculateNewPosition(position, playerAcceleration, shift);
            if (isMoveValid(player, newPosition)) {
                possibleMoves.add(newPosition);
            }
        }
        return possibleMoves;
    }

    /**
     * Calculates the new position based on the current position, player acceleration, and shift.
     *
     * @param position the current position.
     * @param acceleration the player acceleration.
     * @param shift the shift to apply.
     * @return the new position.
     */
    private Position calculateNewPosition(Position position, Acceleration acceleration, Position shift) {
        return new Position(
                position.getX() + acceleration.getDx() + shift.getX(),
                position.getY() + acceleration.getDy() + shift.getY()
        );
    }

    /**
     * Checks if the move to the new position is valid.
     *
     * @param player the player making the move.
     * @param newPosition the new position.
     * @return true if the move is valid, false otherwise.
     */
    private boolean isMoveValid(Player player, Position newPosition) {
        return raceTrack.isInBounds(newPosition.getX(), newPosition.getY())
                && (raceTrack.getComponentAt(newPosition.getX(), newPosition.getY()) == TrackComponent.ROAD
                || raceTrack.getComponentAt(newPosition.getX(), newPosition.getY()) == TrackComponent.OIL_SPOT)
                && moveValidator.isRespected(player, newPosition);
    }
}
