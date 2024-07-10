package it.unicam.cs.mpmgc.vectorrally.api.model.movements;

/**
 * This interface represents a position in the graph paper axis.
 * It provides methods to get and set the x and y coordinates.
 *
 * @version 1.0
 * @since 2024-07-10
 *
 * @autor Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface Coordinates {
    /**
     * Gets the x coordinate.
     *
     * @return the x coordinate.
     */
    int getX();

    /**
     * Gets the y coordinate.
     *
     * @return the y coordinate.
     */
    int getY();

    /**
     * Sets the x coordinate.
     *
     * @param x the new x coordinate.
     */
    void setX(int x);

    /**
     * Sets the y coordinate.
     *
     * @param y the new y coordinate.
     */
    void setY(int y);

    /**
     * Compares this object to the specified object.
     *
     * @param o the object to compare.
     * @return true if the objects are equal, false otherwise.
     */
    boolean equals(Object o);

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for the object.
     */
    int hashCode();

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    String toString();
}