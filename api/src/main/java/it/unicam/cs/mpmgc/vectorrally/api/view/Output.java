package it.unicam.cs.mpmgc.vectorrally.api.view;

/**
 * Defines an interface for output operations.
 * Provides static methods to print messages to the console.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface Output {

    /**
     * Prints a message followed by a newline to the console.
     *
     * @param message the message to print
     */
    static void printlnMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints a message to the console.
     *
     * @param message the message to print
     */
    static void printMessage(String message) {
        System.out.print(message);
    }
}
