package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates possible moves for a player in the Vector Rally game using a specified
 * neighbors generator and move validator.
 *
 * @param <T> the type of NeighborsGenerator used to generate possible shifts in acceleration.
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class BasicMovesGenerator<T extends NeighborsGenerator> {
    private final T neighborsGenerator;
    private final MoveValidator moveValidator;

    /**
     * Constructs a BasicMovesGenerator with the specified neighbors generator and move validator.
     *
     * @param neighborsGenerator the neighbors generator used to generate possible shifts in acceleration.
     * @param moveValidator the move validator used to validate generated moves.
     */
    public BasicMovesGenerator(T neighborsGenerator, MoveValidator moveValidator) {
        this.neighborsGenerator = neighborsGenerator;
        this.moveValidator = moveValidator;
    }

    /**
     * Generates a list of possible moves for the specified player on the given racetrack,
     * considering the positions of all players.
     *
     * @param player the player for whom to generate possible moves.
     * @param track the racetrack on which to generate moves.
     * @param allPlayers the list of all players in the game.
     * @return a list of valid possible moves for the player.
     */
    public List<Move> generatePossibleMoves(Player player, RaceTrack track, List<Player> allPlayers) {
        List<Move> possibleMoves = new ArrayList<>();
        List<Acceleration> shifts = neighborsGenerator.generateShifts(player.getPlayerAcceleration());
        for (Acceleration shift : shifts) {
            Move move = new Move(new Acceleration(shift.getDx(), shift.getDy()), player.getPosition());
            if (moveValidator.isValid(move, track, allPlayers)) {
                possibleMoves.add(move);
            }
        }
        return possibleMoves;
    }

    public NeighborsGenerator getNeighborsGenerator() {
        return neighborsGenerator;
    }
}
