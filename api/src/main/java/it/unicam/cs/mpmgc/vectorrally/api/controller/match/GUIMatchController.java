package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMoveValidator;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMovesGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.view.GraphicalIOController;

import java.util.List;

public class GUIMatchController extends DefaultMatchController implements MatchController {
    private List<Player> players;
    private RaceTrack raceTrack;
    private final BasicMovesGenerator<NeighborsGenerator> moveGenerator;
    private boolean gameOver;
    private final BasicMoveValidator moveValidator;
    private Player currentPlayer;


    public GUIMatchController(GraphicalIOController ioController, BasicMovesGenerator<NeighborsGenerator> moveGenerator) {
        this.moveGenerator = moveGenerator;
        this.moveValidator = new BasicMoveValidator();
    }

    @Override
    public void initializeMatch(List<Player> players, RaceTrack raceTrack) {
        this.players = players;
        this.raceTrack = raceTrack;
    }

    @Override
    public void startMatch() {
        while (!gameOver) {
            nextTurn();
        }
    }

    public void nextTurn() {
        if (currentPlayer == null) return;
        handleTurn(currentPlayer);
    }

    @Override
    public void handleTurn(Player player) {
        List<Move> possibleMoves = moveGenerator.generatePossibleMoves(player, raceTrack, players);
        if (possibleMoves.isEmpty()) {
            handleElimination(player);
        }
    }

    @Override
    public void handleElimination(Player player) {
        players.remove(player);
        if (players.isEmpty()) {
            gameOver = true;
        }
    }

    public boolean checkIfPlayerWins(Move move) {
        Position end = move.getDestination();
        return raceTrack.getComponentAt(end.getX(), end.getY()) == TrackComponent.END_LINE ||
                moveValidator.passesThroughComponent(raceTrack, move, TrackComponent.END_LINE);
    }
}
