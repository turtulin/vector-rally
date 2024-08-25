package it.unicam.cs.mpmgc.vectorrally.api.controller.setup;

import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.Track;

import java.io.IOException;

/**
 * Defines the contract for building a racetrack in the Vector Rally game.
 * Implementations of this interface are responsible for constructing a {@link Track}
 * from a specified file.
 *
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface TrackBuilder {

    /**
     * Builds a {@link Track} from the specified file.
     *
     * @param filename the name of the file containing the racetrack.
     * @return the constructed {@link Track}.
     * @throws IOException if an I/O error occurs.
     */
    Track buildTrack(String filename) throws IOException;
}
