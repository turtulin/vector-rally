package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Coordinates;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.Track;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;

import java.util.List;

/**
 * Defines the contract for validating moves in the Vector Rally game. Implementations of this
 * interface provide the logic to determine if a move is valid based on the current state of the
 * racetrack and the positions of all players.
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */

public interface MoveValidator {

    /**
     * Determines if the specified move is valid on the given racetrack, considering the positions
     * of all players.
     *
     * @param move the {@link Move} to be validated.
     * @param track the {@link Track} on which the move is to be validated.
     * @param allPlayers the {@link List} of all {@link Player} in the game.
     * @return {@code true} if the move is valid, {@code false} otherwise.
     */
    boolean isValid(Move move, Track track, List<Player> allPlayers);

    /**
     * Checks if the move ends in a specified component on the track.
     *
     * @param move the {@link Move} to be checked.
     * @param track the {@link Track} being validated.
     * @param component the {@link TrackComponent} to check for.
     * @return {@code true} if the move ends in the component, {@code false} otherwise.
     */
    boolean endsInComponent(Move move, Track track, TrackComponent component);

    /**
     * Checks if the given move passes through a specific component on the racetrack.
     * A move is considered to pass through the component if any of the intermediate coordinates
     * between the source and destination intersect with the specified {@link TrackComponent}.
     *
     * @param track the {@link Track} to check for the component.
     * @param move the {@link Move} being evaluated.
     * @param component the {@link TrackComponent} that the move may pass through.
     * @return {@code true} if the move passes through the specified component, {@code false} otherwise.
     */
    boolean passesThroughComponent(Track track, Move move, TrackComponent component);

    /**
     * Checks if the move passes through any player on the track.
     *
     * @param move the {@link Move} to be checked.
     * @param allPlayers the {@link List} of all {@link Player} in the game.
     * @return {@code true} if the move passes through any player, {@code false} otherwise.
     */
    boolean passesThroughPlayers(Move move, List<Player> allPlayers);

    /**
     * Gets the positions between the start and end of the move.
     *
     * @param move the {@link Move} to get positions for.
     * @return a {@link List} of {@link Coordinates} between the start and end of the move.
     */
    List<Coordinates> getPositionsBetween(Move move);

}
