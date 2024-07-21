package it.unicam.cs.mpmgc.vectorrally.api.model.racetrack;

/**
 * Represents a component in the Vector Rally game. Components are elements that make up the racetrack,
 * such as walls, roads, start lines, etc. Each component is associated with a character symbol
 * that represents it.
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface Component {
    /**
     * Retrieves the character symbol representing the component.
     *
     * @return the character symbol of the component.
     */
    char getSymbol();
}
