package it.unicam.cs.mpmgc.vectorrally.api.controller.io;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.List;

/**
 * This interface defines utility methods for displaying messages in the game.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface Utils {

    void printWelcomeMessage();
    void printRules();
    void printRaceTrack(RaceTrack raceTrack, List<Player> players);
    void printSetupResult(RaceTrack raceTrack, List<Player> players);
    void printTurn(int turn, Player player);
    void printMoves(RaceTrack raceTrack, List<Player> players, List<Move> moves);
    void printWinMessage(Player player);
    void printEliminationMessage(Player player);
    void printDisqualificationWarning(Player player);
    void printDisqualificationMessage(Player player);
}
