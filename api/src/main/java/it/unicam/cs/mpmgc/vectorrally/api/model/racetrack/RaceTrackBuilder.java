package it.unicam.cs.mpmgc.vectorrally.api.model.racetrack;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;

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
        TrackComponent[][] track = constructMatrix(lines);
        RaceTrack raceTrack = new RaceTrack(track);
        validateTrack(raceTrack);
        return raceTrack;
    }

    /**
     * Constructs the track matrix from the list of lines.
     *
     * @param lines the list of strings representing the lines of the racetrack.
     * @return a 2D array of TrackComponent representing the racetrack.
     */
    private TrackComponent[][] constructMatrix(List<String> lines) {
        int rows = lines.size();
        int cols = lines.get(0).length();
        TrackComponent[][] track = new TrackComponent[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                track[i][j] = TrackComponent.fromChar(lines.get(i).charAt(j));
            }
        }
        return track;
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

    /**
     * Validates the track to ensure start and end lines are straight and parallel.
     *
     * @param raceTrack the RaceTrack to validate.
     * @throws IllegalArgumentException if the start and end lines are not straight and parallel.
     */
    private void validateTrack(RaceTrack raceTrack) {
        List<Position> startPositions = raceTrack.getPositionsOfComponent(TrackComponent.START_LINE);
        List<Position> endPositions = raceTrack.getPositionsOfComponent(TrackComponent.END_LINE);

        if (!areLinesStraightAndParallel(startPositions, endPositions)) {
            throw new IllegalArgumentException("Start and end lines must be straight and parallel");
        }
    }

    private boolean areLinesStraightAndParallel(List<Position> startPositions, List<Position> endPositions) {
        boolean startLineIsHorizontal = startPositions.stream().allMatch(p -> p.getY() == startPositions.get(0).getY());
        boolean endLineIsHorizontal = endPositions.stream().allMatch(p -> p.getY() == endPositions.get(0).getY());
        boolean startLineIsVertical = startPositions.stream().allMatch(p -> p.getX() == startPositions.get(0).getX());
        boolean endLineIsVertical = endPositions.stream().allMatch(p -> p.getX() == endPositions.get(0).getX());

        return (startLineIsHorizontal && endLineIsHorizontal) || (startLineIsVertical && endLineIsVertical);
    }
}
