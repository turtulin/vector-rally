package it.unicam.cs.mpmgc.vectorrally.api.model.racetrack;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Coordinates;

import java.util.List;

/**
 * Represents a track in the Vector Rally game. This interface provides methods
 * to interact with the track components and positions, allowing for querying
 * and manipulating the track.
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface Track {

    /**
     * Retrieves the component at the specified position on the track.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return the {@link TrackComponent} at the specified position.
     */
    TrackComponent getComponentAt(int x, int y);

    /**
     * Checks if the specified position is within the bounds of the track.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return {@code true} if the position is within bounds, {@code false} otherwise.
     */
    boolean isInBounds(int x, int y);

    /**
     * Retrieves all positions that have the specified component.
     *
     * @param component the {@link TrackComponent} to search for.
     * @return a {@link List} of {@link Coordinates} that have the specified component.
     */
    List<Coordinates> getPositionsOfComponent(TrackComponent component);

    /**
     * Retrieves the width of the track.
     *
     * @return the width of the track.
     */
    int getWidth();

    /**
     * Retrieves the length of the track.
     *
     * @return the length of the track.
     */
    int getLength();
}
