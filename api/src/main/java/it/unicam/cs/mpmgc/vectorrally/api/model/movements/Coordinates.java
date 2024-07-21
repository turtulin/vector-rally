package it.unicam.cs.mpmgc.vectorrally.api.model.movements;

/**
 * Represents a position in the graph paper axis system. This interface provides methods
 * to get and set the x and y coordinates, allowing for manipulation of position data.
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface Coordinates {
    /**
     * Retrieves the x coordinate.
     *
     * @return the x coordinate.
     */
    int getX();

    /**
     * Retrieves the y coordinate.
     *
     * @return the y coordinate.
     */
    int getY();

    /**
     * Updates the x coordinate.
     *
     * @param x the new x coordinate.
     */
    void setX(int x);

    /**
     * Updates the y coordinate.
     *
     * @param y the new y coordinate.
     */
    void setY(int y);
}
