package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

/**
 * This interface represents a general game rule in the vector rally game.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface GameRule {

    /**
     * Checks if the rule is respected for the given player.
     *
     * @param player the player to check.
     * @return true if the rule is respected, false otherwise.
     */
    boolean isRespected(Player player);
}
