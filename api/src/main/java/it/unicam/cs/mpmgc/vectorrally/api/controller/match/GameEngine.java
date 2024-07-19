package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

/**
 * This interface defines methods for controlling the game engine.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @autor Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface GameEngine {

    /**
     * Starts the game application.
     */
    void startGame() throws Exception;

    void displayWelcomeAndRules();
}
