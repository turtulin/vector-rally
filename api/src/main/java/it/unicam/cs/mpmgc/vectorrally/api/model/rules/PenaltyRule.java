package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

/**
 * This interface defines the contract for applying penalties in the vector rally game.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface PenaltyRule extends Rule {
    /**
     * Applies penalties to the player based on their position.
     *
     * @param player the player to apply penalties to.
     */
    void applyPenalty(Player player);
}
