package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Coordinates;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;

/**
 * This class validates basic moves in the vector rally game.
 *
 * @version 1.0
 * @since 2024-07-11
 */
public class BasicMoveValidator implements MoveValidator {
    private final RaceTrack raceTrack;

    public BasicMoveValidator(RaceTrack raceTrack) {
        this.raceTrack = raceTrack;
    }

    @Override
    public boolean validateMove(Move move) {
        Coordinates startPosition = move.position();
        Coordinates endPosition = new Position(
                startPosition.getX() + move.acceleration().getDx(),
                startPosition.getY() + move.acceleration().getDy()
        );
        if (!raceTrack.isInBounds(endPosition.getX(), endPosition.getY())) {
            return false;
        }
        return raceTrack.getComponentAt(endPosition.getX(), endPosition.getY()) != TrackComponent.WALL;
    }

    @Override
    public boolean isRespected(Player player) {
        Move move = new Move(player.getPlayerAcceleration(), player.getPosition());
        return validateMove(move);
    }
}
