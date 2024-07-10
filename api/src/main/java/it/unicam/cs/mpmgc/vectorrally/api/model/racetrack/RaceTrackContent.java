package it.unicam.cs.mpmgc.vectorrally.api.model.racetrack;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;

/**
 * This record represents the content of a racetrack, associating a position with a track component.
 *
 * @param position the position on the racetrack.
 * @param component the track component at the specified position.
 *
 * @version 1.0
 * @since 2024-07-10
 */
public record RaceTrackContent(Position position, TrackComponent component) implements TrackContent {
}
