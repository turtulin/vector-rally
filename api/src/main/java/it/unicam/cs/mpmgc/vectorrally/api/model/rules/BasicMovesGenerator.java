package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.ArrayList;
import java.util.List;

public class BasicMovesGenerator<T extends NeighborsGenerator> {
    private final T neighborsGenerator;
    private final MoveValidator moveValidator;

    public BasicMovesGenerator(T neighborsGenerator, MoveValidator moveValidator) {
        this.neighborsGenerator = neighborsGenerator;
        this.moveValidator = moveValidator;
    }

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

}
