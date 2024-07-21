package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;

/**
 * Provides utility methods for handling CLI-specific tasks.
 *
 * @version 1.0
 * @since 2024-07-17
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface CLIUtils {

    /**
     * Returns the ANSI color code corresponding to the given car color.
     *
     * @param colour the color of the car
     * @return the ANSI color code as a string
     */
    default String getCarColorCode(CarColour colour) {
        return switch (colour) {
            case RED -> "\033[31m";
            case ORANGE -> "\033[38;5;208m";
            case YELLOW -> "\033[33m";
            case GREEN -> "\033[32m";
            case BLUE -> "\033[34m";
            case PURPLE -> "\033[35m";
            case PINK -> "\033[38;5;205m";
            case CYAN -> "\033[36m";
            case BROWN -> "\033[38;5;94m";
        };
    }
}
