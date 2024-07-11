package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

/**
 * This interface defines the contract for enforcing speed limits in the vector rally game.
 *
 * @version 1.0
 * @since 2024-07-10
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface SpeedLimitRule extends GameRule {

    /**
     * Checks if a player's speed is within the allowed limit.
     *
     * @param player the player whose speed is to be checked.
     * @return true if the player's speed is within the limit, false otherwise.
     */
    boolean checkSpeed(Player player);
}
