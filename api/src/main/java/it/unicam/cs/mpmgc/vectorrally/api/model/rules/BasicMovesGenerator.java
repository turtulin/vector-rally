package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.*;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.Track;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Generates possible moves for a player in the Vector Rally game using a specified
 * neighbors generator and move validator.
 *
 * @param <T> the type of {@link NeighborsGenerator} used to generate possible shifts in acceleration.
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class BasicMovesGenerator<T extends NeighborsGenerator> {
    private final T neighborsGenerator;
    private final MoveValidator moveValidator;

    public BasicMovesGenerator(T neighborsGenerator, MoveValidator moveValidator) {
        this.neighborsGenerator = neighborsGenerator;
        this.moveValidator = moveValidator;
    }

    /**
     * Generates a list of possible moves for the specified player on the given racetrack,
     * considering the positions of all players.
     *
     * @param player the {@link Player} for whom to generate possible moves.
     * @param track the {@link Track} on which to generate moves.
     * @param allPlayers the {@link List} of all {@link Player} in the game.
     * @return a {@link List} of valid possible {@link Move} for the player.
     */
    public List<Move> generatePossibleMoves(Player player, Track track, List<Player> allPlayers) {
        List<Move> possibleMoves = new ArrayList<>();
        List<Vector> shifts = neighborsGenerator.generateShifts(player.getPlayerAcceleration());
        for (Vector shift : shifts) {
            Move move = new Move(new Acceleration(shift.getDx(), shift.getDy()), player.getPosition());
            if (moveValidator.isValid(move, track, allPlayers)) possibleMoves.add(move);
        }
        return possibleMoves;
    }

    /**
     * Extracts the destination coordinates from a list of moves.
     *
     * @param moves the {@link List} of {@link Move} from which to extract the destinations.
     * @return a {@link List} of {@link Coordinates} representing the possible destinations.
     */
    public List<Coordinates> getPossibleDestinations(List<Move> moves) {
        return moves.stream().map(Move::getDestination).collect(Collectors.toList());
    }

    /**
     * Returns the neighbors generator used in this generator.
     *
     * @return the {@link NeighborsGenerator} used to generate possible shifts.
     */
    public NeighborsGenerator getNeighborsGenerator() {
        return neighborsGenerator;
    }

    /**
     * Determines if the specified move is a winning move, i.e., if it crosses the finish line.
     *
     * @param move the {@link Move} to be evaluated.
     * @param track the {@link Track} on which the move is being made.
     * @return {@code true} if the move crosses the finish line, {@code false} otherwise.
     */
    public boolean isWinningMove(Move move, Track track) {
        Coordinates end = move.getDestination();
        return track.getComponentAt(end.getX(), end.getY()) == TrackComponent.END_LINE ||
                moveValidator.passesThroughComponent(track, move, TrackComponent.END_LINE);
    }
}
