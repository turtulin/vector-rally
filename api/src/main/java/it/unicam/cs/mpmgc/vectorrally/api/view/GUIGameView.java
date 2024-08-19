package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.List;

public class GUIGameView implements GameView {

    @Override
    public void displayPossibleMoves(List<Player> players, RaceTrack raceTrack, List<Position> possibleDestinations) {

    }

    @Override
    public void displayWinner(Player winner) {

    }

    @Override
    public void displayGameOver() {

    }

    @Override
    public void displayTurn(Player player, int counter) {

    }

    @Override
    public void displayElimination(Player player) {

    }

    @Override
    public Move getMoveChoice(List<Move> possibleMoves) {
        return null;
    }

    @Override
    public void goToNextTurn() {

    }
}
