package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

/**
 * This interface defines methods for controlling the game engine.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface GameEngine {

    /**
     * Starts the game application.
     *
     * @throws Exception if an error occurs while starting the game.
     */
    void startGame() throws Exception;
}
