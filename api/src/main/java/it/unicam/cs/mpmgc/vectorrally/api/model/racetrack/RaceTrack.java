package it.unicam.cs.mpmgc.vectorrally.api.model.racetrack;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a racetrack in the vector rally game.
 * It handles operations related to the racetrack matrix.
 *
 * @version 1.0
 * @since 2024-07-10
 */
public class RaceTrack implements Track {
    private final TrackComponent[][] track;
    private final int width;
    private final int length;

    /**
     * Constructs a RaceTrack with the given track matrix.
     *
     * @param track the track matrix.
     * @throws IllegalArgumentException if the track matrix is null or empty.
     */
    public RaceTrack(TrackComponent[][] track) {
        if (track == null || track.length == 0 || track[0].length == 0) throw new IllegalArgumentException("Invalid track matrix");
        this.track = track;
        this.width = track[0].length;
        this.length = track.length;
    }

    @Override
    public TrackComponent getComponentAt(int x, int y) {
        return track[x][y];
    }

    @Override
    public boolean isInBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < length && y < width;
    }

    @Override
    public List<Position> getPositionsOfComponent(TrackComponent component) {
        List<Position> positions = new ArrayList<>();
        for (int y = 0; y < track.length; y++) {
            for (int x = 0; x < track[y].length; x++) {
                if (track[y][x] == component) {
                    positions.add(new Position(x, y));
                }
            }
        }
        return positions;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getLength() {
        return this.length;
    }

}
