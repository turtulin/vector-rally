package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.BasicComponentPassChecker;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.ComponentPassChecker;
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
public class BasicMoveValidator implements GameRule {
    private final RaceTrack raceTrack;
    private final ComponentPassChecker componentPassChecker;

    /**
     * Constructs a BasicMoveValidator with the specified racetrack.
     *
     * @param raceTrack the racetrack to be used for validation.
     * @throws NullPointerException if raceTrack is null.
     */
    public BasicMoveValidator(RaceTrack raceTrack) {
        if (raceTrack == null) throw new NullPointerException("RaceTrack cannot be null");
        this.raceTrack = raceTrack;
        this.componentPassChecker = new BasicComponentPassChecker(raceTrack);
    }


    public boolean isRespected(Player player, Position newPosition) {
        Position currentPosition = player.getPosition();
        if (!raceTrack.isInBounds(newPosition.getX(), newPosition.getY())) {
            return false;
        }
        return !componentPassChecker.passesThroughComponent(currentPosition, newPosition, TrackComponent.WALL);
    }

    @Override
    public boolean isRespected(Player player) {
        Position currentPosition = player.getPosition();
        Acceleration acceleration = player.getPlayerAcceleration();
        Position newPosition = new Position(
                currentPosition.getX() + acceleration.getDx(),
                currentPosition.getY() + acceleration.getDy()
        );
        return isRespected(player, newPosition);
    }
}
