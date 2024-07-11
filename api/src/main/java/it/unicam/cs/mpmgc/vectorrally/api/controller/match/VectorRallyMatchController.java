package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.io.Utils;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.List;

public class VectorRallyMatchController implements MatchController {
    private final GameSetup gameSetup;
    private final Utils utilities;
    private RaceTrack raceTrack;
    private List<Player> players;
    private int turn;

    public VectorRallyMatchController(GameSetup gameSetup, Utils utilities) {
        this.gameSetup = gameSetup;
        this.utilities = utilities;
    }

    @Override
    public void startGame() {
        gameSetup.initializeGameSetup();
        raceTrack = gameSetup.getRaceTrack();
        players = gameSetup.getPlayers();
        turn = 0;
        runGameLoop();
    }

    private void runGameLoop() {
        while (!isGameOver()) {
            handleTurn();
            turn++;
        }
        utilities.printWinMessageFor(determineWinner());
    }

    private void handleTurn() {
        for (Player player : players) {
            utilities.printTurn(turn, player);
            // Add logic to handle player's turn
            // Use utilities to display available moves and get user input
        }
    }

    private boolean isGameOver() {
        // Implement logic to determine if the game is over
        return false;
    }

    private Player determineWinner() {
        // Implement logic to determine the winner
        return null;
    }
}
