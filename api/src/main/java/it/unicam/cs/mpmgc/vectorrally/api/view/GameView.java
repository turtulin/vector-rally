package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.List;

public interface GameView {
    void displayPossibleMoves(List<Player> players, RaceTrack raceTrack, List<Position> possibleDestinations);
    void displayWinner(Player winner);
    void displayGameOver();
    void displayTurn(Player player, int counter);
    void displayElimination(Player player);
    Move getMoveChoice(List<Move> possibleMoves);
    void goToNextTurn();
}
