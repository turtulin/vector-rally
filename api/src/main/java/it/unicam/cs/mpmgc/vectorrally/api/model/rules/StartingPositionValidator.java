package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.Track;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;

import java.util.List;

public class StartingPositionValidator implements GameRule {

    private final Track raceTrack;

    /**
     * Constructs a StartingPositionValidator with the specified racetrack.
     *
     * @param raceTrack the racetrack to be used for validation.
     */
    public StartingPositionValidator(Track raceTrack) {
        this.raceTrack = raceTrack;
    }

    @Override
    public boolean isRespected(Player player) {
        Position playerPosition = player.getPosition();
        return raceTrack.getComponentAt(playerPosition.getX(), playerPosition.getY()) == TrackComponent.START_POSITION;
    }
}
