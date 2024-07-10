package it.unicam.cs.mpmgc.vectorrally.api.model.racetrack;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;

import java.util.ArrayList;
import java.util.List;

public class RaceTrack implements Track {
    private final TrackComponent[][] Track;

    private final int length;

    private final int height;

    public RaceTrack(TrackComponent[][] raceTrack) {
        if (raceTrack == null)
            throw new NullPointerException("Null racetrack");
        this.Track = raceTrack;
        this.length = raceTrack.length;
        this.height = raceTrack[0].length;
    }

    @Override
    public boolean isInBounds(Position position) {
        if (position == null) throw new NullPointerException("Null position");
        if (position.getX() < 0 || position.getX() >= this.length
                || position.getY() < 0 || position.getY() >= this.height)
            return false;
        return Track[position.getX()][position.getY()] != TrackComponent.WALL;
    }

    @Override
    public TrackComponent getRaceTrackComponentAt(Position position) {
        return Track[position.getX()][position.getY()];
    }

    @Override
    public List<Position> getPositionsAssociatedTo(TrackComponent trackComponent) {
        if (trackComponent == null)
            throw new NullPointerException();
        List<Position> positions = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < length; x++) {
                Position inExam = new Position(x, y);
                if (getRaceTrackComponentAt(inExam).equals(trackComponent))
                    positions.add(inExam);
            }
        }
        return positions;
    }

    @Override
    public int getLength() {
        return this.length;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

}
