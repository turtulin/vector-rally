package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Coordinates;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.Track;

import java.util.List;

/**
 * This class implements the {@link MatchGameView} interface to provide a command-line interface (CLI)
 * for managing the display and interaction of a game match. It interacts with the user through text-based prompts
 * and outputs, handling the display of possible moves, game status updates, and the retrieval of player input.
 *
 * @version 1.0
 * @since 2024-08-21
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class CLIMatchGameView implements MatchGameView {
    private final IOController ioController;

    public CLIMatchGameView(IOController ioController) {
        this.ioController = ioController;
    }

    @Override
    public void displayPossibleMoves(List<Player> players, Track raceTrack, List<Coordinates> possibleDestinations) {
        ioController.printRaceTrack(raceTrack, players, possibleDestinations);
        ioController.displayMoves(possibleDestinations.size());
    }

    @Override
    public void displayWinner(Player winner) {
        ioController.displayWinningMessage(winner);
    }

    @Override
    public void displayGameOver() {
        ioController.displayGameOver();
    }

    @Override
    public void displayTurn(Player player, int counter) {
        ioController.displayTurn(player, counter);
    }

    @Override
    public void displayElimination(Player player) {
        ioController.displayElimination(player);
    }

    @Override
    public Move getMoveChoice(List<Move> possibleMoves) {
        return ioController.getChosenMove(possibleMoves);
    }

    @Override
    public void goToNextTurn() {
        ioController.displayGoToNextTurn();
        ioController.getGoToNextTurn();
    }

}
