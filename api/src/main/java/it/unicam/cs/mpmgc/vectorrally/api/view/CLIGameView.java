package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.List;

public class CLIGameView implements GameView {
    private final IOController ioController;
    private final GameMessageProvider messageProvider = new GameMessageProvider();

    public CLIGameView(IOController ioController) {
        this.ioController = ioController;
    }

    @Override
    public void displayPossibleMoves(List<Player> players, RaceTrack raceTrack, List<Position> possibleDestinations) {
        ioController.printRaceTrack(raceTrack, players, possibleDestinations);
        ioController.displayMoves(possibleDestinations);
    }

    @Override
    public void displayWinner(Player winner) {
        ioController.displayMessage(messageProvider.getWinMessage(winner));
        ioController.displayMessage(messageProvider.getCongratulationsMessage());
    }

    @Override
    public void displayGameOver() {
        ioController.displayMessage(messageProvider.getGameOverMessage());
    }

    @Override
    public void displayTurn(Player player, int counter) {
        ioController.displayMessage(messageProvider.getTurnMessage(counter, player));
    }

    @Override
    public void displayElimination(Player player) {
        ioController.displayMessage(messageProvider.getEliminationMessage(player));
    }

    @Override
    public Move getMoveChoice(List<Move> possibleMoves) {
        return ioController.chooseMove(possibleMoves);
    }

    @Override
    public void goToNextTurn() {
        ioController.goToNextTurn();
    }

}
