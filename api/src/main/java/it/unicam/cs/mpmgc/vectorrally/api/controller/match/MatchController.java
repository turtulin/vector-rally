package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.List;

/**
 * This interface defines methods for controlling the game match.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface MatchController {

    void initializeMatch(List<Player> players, RaceTrack raceTrack);

    void startMatch() throws Exception;

    void handleTurn(Player player);

    void handleMove(Player player, Move move);

    void handleElimination(Player player);

    boolean isGameOver();

    void endMatch() throws Exception;
}
