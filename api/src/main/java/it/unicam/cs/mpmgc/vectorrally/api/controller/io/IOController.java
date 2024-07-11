package it.unicam.cs.mpmgc.vectorrally.api.controller.io;

/**
 * This interface defines methods for input and output operations in the game.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface IOController {

    /**
     * Asks the user if they want to start a new game.
     *
     * @return 1 if the user wants to start a new game, 0 otherwise.
     */
    int askForNewGame();

    /**
     * Displays a message to the user.
     *
     * @param message the message to display.
     */
    void displayMessage(String message);

}
