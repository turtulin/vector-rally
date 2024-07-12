package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.ArrayList;
import java.util.List;

public class BasicDestinationsGenerator<T extends NeighborsGenerator> {
    private final BasicMovesGenerator moveGenerator;

    public BasicDestinationsGenerator(BasicMovesGenerator moveGenerator) {
        this.moveGenerator = moveGenerator;
    }

    public List<Position> generatePossibleDestinations(Player player, RaceTrack track, List<Player> allPlayers) {
        List<Position> possibleDestinations = new ArrayList<>();
        List<Move> possibleMoves = moveGenerator.generatePossibleMoves(player, track, allPlayers);
        for (Move move : possibleMoves) {
            possibleDestinations.add(move.position());
        }
        return possibleDestinations;
    }
}
