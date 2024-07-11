package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

/**
 * This interface defines the contract for enforcing turn-based gameplay in the vector rally game.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface TurnBasedRule extends GameRule {

    /**
     * Ensures that players take turns in the correct order.
     *
     * @param currentPlayer the player whose turn it currently is.
     * @return the player who should take the next turn.
     */
    Player getNextPlayer(Player currentPlayer);
}
