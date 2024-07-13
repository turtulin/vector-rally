package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.io.IOController;
import it.unicam.cs.mpmgc.vectorrally.api.controller.io.Utils;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.BasicComponentPassChecker;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.ComponentPassChecker;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicDestinationsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMovesGenerator;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

public class VectorRallyMatchController implements MatchController {
    private List<Player> players;
    private RaceTrack raceTrack;
    private final IOController ioController;
    private final BasicMovesGenerator moveGenerator;
    private Queue<Player> turnQueue;
    private boolean gameOver;


    public VectorRallyMatchController(IOController ioController, BasicMovesGenerator moveGenerator) {
        this.ioController = ioController;
        this.moveGenerator = moveGenerator;
    }

    @Override
    public void initializeMatch(List<Player> players, RaceTrack raceTrack) {
        this.players = players;
        this.raceTrack = raceTrack;
        this.turnQueue = new LinkedList<>(players);
        this.gameOver = false;
    }

    @Override
    public void startMatch() {
        while (!gameOver) {
            handleTurn(Objects.requireNonNull(turnQueue.poll()));
        }
        endMatch();
    }

    @Override
    public void handleTurn(Player player) {
        Utils.printTurnMessage(turnQueue.size(), player);
        List<Move> possibleMoves = moveGenerator.generatePossibleMoves(player, raceTrack, players);
        if (possibleMoves.isEmpty()) {
            handleElimination(player);
        } else {
            List<Position> possibleDestinations = possibleMoves.stream()
                    .map(move -> new Position(player.getPosition().getX() + move.acceleration().getDx(),
                            player.getPosition().getY() + move.acceleration().getDy()))
                    .collect(Collectors.toList());
            Utils.printRaceTrack(raceTrack, players, possibleDestinations);
            int chosenMoveIndex = ioController.chooseMove(possibleMoves);
            Move chosenMove = possibleMoves.get(chosenMoveIndex);
            handleMove(player, chosenMove);
        }
    }

    @Override
    public void handleMove(Player player, Move move) {
        Position newPosition = new Position(move.position().getX() + move.acceleration().getDx(), move.position().getY() + move.acceleration().getDy());
        if (checkIfPlayerWins(move)) {
            Utils.printWinMessage(player);
            gameOver = true;
            return;
        }
        player.setPosition(move.position());
        player.setPlayerAcceleration(move.acceleration());

        if (!moveGenerator.generatePossibleMoves(player, raceTrack, players).isEmpty()) {
            turnQueue.add(player);
        } else {
            handleElimination(player);
        }
    }

    @Override
    public void handleElimination(Player player) {
        Utils.printEliminationMessage(player);
        turnQueue.remove(player);
        players.remove(player);
        if (players.isEmpty()) {
            gameOver = true;
        }
    }

    @Override
    public void endMatch() {
        ioController.displayEndMatchMessage();
    }

    private boolean checkIfPlayerWins(Move move) {
        Position start = move.position();
        Position end = new Position(start.getX() + move.acceleration().getDx(), start.getY() + move.acceleration().getDy());
        System.out.println("Checking win condition from " + start + " to " + end);
        ComponentPassChecker checker = new BasicComponentPassChecker(raceTrack);
        boolean result = raceTrack.getComponentAt(end.getX(), end.getY()) == TrackComponent.END_LINE || checker.passesThroughComponent(start, end, TrackComponent.END_LINE);
        System.out.println("Win condition: " + result);
        return result;
        //return raceTrack.getComponentAt(end.getX(), end.getY()) == TrackComponent.END_LINE || checker.passesThroughComponent(start, end, TrackComponent.END_LINE);
    }

    @Override
    public boolean checkGameOver() {
        return gameOver;
    }
}

