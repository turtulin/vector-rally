package it.unicam.cs.mpmgc.vectorrally.api.view;

/**
 * This interface defines the method for managing the end-of-game options in the Vector Rally application.
 * It allows the user to decide whether to play another match after the current one ends.
 *
 * @version 1.0
 * @since 2024-08-20
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface FinishGameView {

    /**
     * Prompts the user to decide whether they want to play another match.
     *
     * @return {@code true} if the user wants to play another match; {@code false} otherwise.
     */
    boolean playAnotherMatch();
}
