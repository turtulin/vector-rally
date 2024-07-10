package it.unicam.cs.mpmgc.vectorrally.api.model.racetrack;

import java.io.IOException;

/**
 * This interface defines the contract for building a racetrack.
 *
 * @version 1.0
 * @since 2024-07-10
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface TrackBuilder {
    /**
     * Builds a Racetrack from the specified file.
     *
     * @param filename the name of the file containing the racetrack.
     * @return the constructed Racetrack.
     * @throws IOException if an I/O error occurs.
     */
    RaceTrack buildTrack(String filename) throws IOException;
}
