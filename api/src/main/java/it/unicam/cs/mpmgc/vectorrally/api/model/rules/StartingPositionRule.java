package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

/**
 * This interface defines the contract for validating the starting position of players in the vector rally game.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface StartingPositionRule extends GameRule {

    /**
     * Validates the starting position of a player.
     *
     * @param player the player whose starting position is to be validated.
     * @return true if the starting position is valid, false otherwise.
     */
    boolean validateStartingPosition(Player player);
}
