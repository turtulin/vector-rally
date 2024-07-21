package it.unicam.cs.mpmgc.vectorrally.api.model.racetrack;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a racetrack in the Vector Rally game. This class handles operations
 * related to the racetrack matrix, including querying and manipulating track components.
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
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
        if (!isInBounds(x, y)) throw new ArrayIndexOutOfBoundsException("Position out of bounds: (" + x + ", " + y + ")");
        return track[x][y];
    }
    @Override
    public boolean isInBounds(int x, int y) {
        return x >= 0 && x < length && y >= 0 && y < width;
    }

    @Override
    public List<Position> getPositionsOfComponent(TrackComponent component) {
        List<Position> positions = new ArrayList<>();
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < width; y++) {
                if (track[x][y] == component) {
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
