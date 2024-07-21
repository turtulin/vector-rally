package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.util.List;

/**
 * Defines the contract for validating moves in the Vector Rally game. Implementations of this
 * interface provide the logic to determine if a move is valid based on the current state of the
 * racetrack and the positions of all players.
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */

public interface MoveValidator {

    /**
     * Determines if the specified move is valid on the given racetrack, considering the positions
     * of all players.
     *
     * @param move the move to be validated.
     * @param track the racetrack on which the move is to be validated.
     * @param allPlayers the list of all players in the game.
     * @return true if the move is valid, false otherwise.
     */
    boolean isValid(Move move, RaceTrack track, List<Player> allPlayers);
}
