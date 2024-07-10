package it.unicam.cs.mpmgc.vectorrally.api.model.racetrack;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;

import java.util.List;

public interface Track {
    /**
     * Checks if a certain position is part of the racetrack
     *
     * @param position the position to check
     * @return true if the position is in bounds
     *         false otherwise
     * @throws NullPointerException if the position is null
     */
    boolean isInBounds(Position position);

    /**
     * Returns the racetrack component at the specified position
     *
     * @param position the specified position
     * @return the racetrack component at the specified position
     *         or null if the position is out of bounds
     * @throws NullPointerException if the position is null
     */
    TrackComponent getRaceTrackComponentAt(Position position);

    /**
     * Returns a list containing all the positions that are associated
     * to a specific racetrack feature
     *
     * @param raceTrackComponent the racetrack feature from which
     *                           to derive the list of positions
     * @return a list containing positions
     * @throws NullPointerException if raceTrackComponent is null
     */
    List<Position> getPositionsAssociatedTo(TrackComponent raceTrackComponent);

    /**
     * Returns the racetrack length
     *
     * @return the racetrack length
     */
    int getLength();

    /**
     * Returns the racetrack height
     *
     * @return the racetrack height
     */
    int getHeight();

}
