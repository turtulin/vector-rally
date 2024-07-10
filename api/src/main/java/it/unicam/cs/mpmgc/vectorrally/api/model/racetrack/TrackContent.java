package it.unicam.cs.mpmgc.vectorrally.api.model.racetrack;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;

/**
 * This interface represents the content of a racetrack, associating a position with a track component.
 *
 * @version 1.0
 * @since 2024-07-10
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface TrackContent {

    /**
     * Gets the position on the racetrack.
     *
     * @return the position.
     */
    Position position();

    /**
     * Gets the component at the specified position.
     *
     * @return the track component.
     */
    TrackComponent component();
}
