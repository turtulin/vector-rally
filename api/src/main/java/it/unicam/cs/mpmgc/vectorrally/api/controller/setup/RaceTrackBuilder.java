package it.unicam.cs.mpmgc.vectorrally.api.controller.setup;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Coordinates;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.Track;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;

import java.io.IOException;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Implements the {@link TrackBuilder} interface, responsible for reading a file
 * and constructing the racetrack matrix.
 * The class also validates the track to ensure the start and end lines are straight and parallel.
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class RaceTrackBuilder implements TrackBuilder {
    @Override
    public Track buildTrack(String filename) throws IOException {
        List<String> lines = readTrackFromFile(filename);
        TrackComponent[][] track = constructMatrix(lines);
        Track raceTrack = new RaceTrack(track);
        validateTrack(raceTrack);
        return raceTrack;
    }

    private TrackComponent[][] constructMatrix(List<String> lines) {
        int rows = lines.size();
        int cols = lines.getFirst().length();
        TrackComponent[][] track = new TrackComponent[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                track[i][j] = TrackComponent.fromChar(lines.get(i).charAt(j));
            }
        }
        return track;
    }

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

    private void validateTrack(Track raceTrack) {
        List<Coordinates> startPositions = raceTrack.getPositionsOfComponent(TrackComponent.START_LINE);
        List<Coordinates> endPositions = raceTrack.getPositionsOfComponent(TrackComponent.END_LINE);
        if (!areLinesStraightAndParallel(startPositions, endPositions)) throw new IllegalArgumentException("Start and end lines must be straight and parallel");
    }

    private boolean areLinesStraightAndParallel(List<Coordinates> startPositions, List<Coordinates> endPositions) {
        boolean startLineIsHorizontal = startPositions.stream().allMatch(p -> p.getY() == startPositions.getFirst().getY());
        boolean endLineIsHorizontal = endPositions.stream().allMatch(p -> p.getY() == endPositions.getFirst().getY());
        boolean startLineIsVertical = startPositions.stream().allMatch(p -> p.getX() == startPositions.getFirst().getX());
        boolean endLineIsVertical = endPositions.stream().allMatch(p -> p.getX() == endPositions.getFirst().getX());
        return (startLineIsHorizontal && endLineIsHorizontal) || (startLineIsVertical && endLineIsVertical);
    }
}
