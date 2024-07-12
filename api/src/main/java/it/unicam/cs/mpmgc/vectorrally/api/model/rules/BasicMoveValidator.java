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
    private final ComponentPassChecker componentPassChecker;

    /**
     * Constructs a BasicMoveValidator with the specified racetrack.
     *
     * @param raceTrack the racetrack to be used for validation.
     * @throws NullPointerException if raceTrack is null.
     */
    public BasicMoveValidator(RaceTrack raceTrack) {
        if (raceTrack == null) throw new NullPointerException("RaceTrack cannot be null");
        this.componentPassChecker = new BasicComponentPassChecker(raceTrack);
    }

    @Override
    public boolean isRespected(Player player) {
        Position currentPosition = player.getPosition();
        Position newPosition = new Position(currentPosition.getX() + player.getPlayerAcceleration().getDx(),
                currentPosition.getY() + player.getPlayerAcceleration().getDy());
        return componentPassChecker.passesThroughComponent(currentPosition, newPosition, TrackComponent.ROAD)
                || componentPassChecker.passesThroughComponent(currentPosition, newPosition, TrackComponent.OIL_SPOT);
    }
}
