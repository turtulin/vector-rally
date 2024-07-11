package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

/**
 * This interface defines the contract for checking winning conditions in the vector rally game.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface WinCondition extends Rule {
    /**
     * Checks if a player has won the game.
     *
     * @param player the player to be checked.
     * @return true if the player has won, false otherwise.
     */
    boolean checkWin(Player player);
}
