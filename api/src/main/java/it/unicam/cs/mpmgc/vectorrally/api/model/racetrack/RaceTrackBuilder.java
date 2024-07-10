package it.unicam.cs.mpmgc.vectorrally.api.model.racetrack;

import java.io.IOException;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * This class implements the interface, responsible for reading a file
 * and constructing the racetrack matrix.
 *
 * @version 1.0
 * @since 2024-07-10
 */
public class RaceTrackBuilder implements TrackBuilder {
    @Override
    public RaceTrack buildTrack(String filename) throws IOException {
        List<String> lines = readTrackFromFile(filename);
        int rows = lines.size();
        int cols = lines.get(0).length();
        TrackComponent[][] track = new TrackComponent[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                track[i][j] = TrackComponent.fromChar(lines.get(i).charAt(j));
            }
        }
        return new RaceTrack(track);
    }

    /**
     * Reads the racetrack from a file.
     *
     * @param filename the name of the file.
     * @return a list of strings representing the lines of the racetrack.
     * @throws IOException if an I/O error occurs.
     */
    private List<String> readTrackFromFile(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
