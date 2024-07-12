package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.ArrayList;
import java.util.List;

public class BasicMovesGenerator {
    private final NeighborsGenerator neighborsGenerator;
    private final MoveValidator moveValidator;

    public BasicMovesGenerator(NeighborsGenerator neighborsGenerator, MoveValidator moveValidator) {
        this.neighborsGenerator = neighborsGenerator;
        this.moveValidator = moveValidator;
    }

    public List<Move> generatePossibleMoves(Player player, RaceTrack track, List<Player> allPlayers) {
        List<Move> possibleMoves = new ArrayList<>();
        Position currentPosition = player.getPosition();
        List<Position> shifts = neighborsGenerator.generateShifts(currentPosition);
        for (Position shift : shifts) {
            Acceleration acceleration = new Acceleration(shift.getX() - currentPosition.getX(), shift.getY() - currentPosition.getY());
            Move move = new Move(acceleration, new Position(currentPosition.getX() + acceleration.getDx(), currentPosition.getY() + acceleration.getDy()));
            if (moveValidator.isValid(move, track, allPlayers)) {
                possibleMoves.add(move);
            }
        }
        return possibleMoves;
    }
}