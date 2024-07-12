package it.unicam.cs.mpmgc.vectorrally.api.controller.io;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.List;

/**
 * This interface defines methods for input and output operations in the game.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface IOController {
    void printRaceTrack(RaceTrack raceTrack, List<Player> players);
    int chooseMoveToPerform(List<Move> moves);
    void printWinMessage(Player player);
    void printInvalidMoveMessage(Player player);
    void printGameOver();
    void printMessage(String message);
    String chooseTrack();
    int getNumberOfHumanPlayers();
    int getNumberOfBotPlayers();
    List<Player> setupPlayers(RaceTrack raceTrack, int numHumanPlayers, int numBotPlayers);

}
