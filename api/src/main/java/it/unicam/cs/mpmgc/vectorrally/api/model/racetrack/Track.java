package it.unicam.cs.mpmgc.vectorrally.api.model.racetrack;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;

import java.util.List;

public interface Track {
    List<Move> getTrackPoints();
    boolean isWithinBounds(Move position);
    boolean isWall(Move position);
    boolean isOilSpot(Move position);
}
