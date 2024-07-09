package it.unicam.cs.mpmgc.vectorrally.api.model.racetrack;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;

import java.util.List;

public class RaceTrack implements Track {
    @Override
    public List<Move> getTrackPoints() {
        return null;
    }

    @Override
    public boolean isWithinBounds(Move position) {
        return false;
    }

    @Override
    public boolean isWall(Move position) {
        return false;
    }

    @Override
    public boolean isOilSpot(Move position) {
        return false;
    }
}
