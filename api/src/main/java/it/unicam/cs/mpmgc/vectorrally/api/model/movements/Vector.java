package it.unicam.cs.mpmgc.vectorrally.api.model.movements;

/**
 * Represents a vector quantity in the game, which includes both magnitude and direction.
 * Magnitude refers to the size or length of the vector, while direction refers to the orientation
 * of the vector in space. This interface provides methods to get and set the dx and dy values,
 * and to calculate the magnitude and direction of the vector.
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface Vector {

    /**
     * Retrieves the dx value of the vector, representing the change in the x direction.
     *
     * @return the dx value.
     */
    int getDx();

    /**
     * Retrieves the dy value of the vector, representing the change in the y direction.
     *
     * @return the dy value.
     */
    int getDy();

    /**
     * Updates the dx value of the vector, representing the change in the x direction.
     *
     * @param dx the new dx value.
     */
    void setDx(int dx);

    /**
     * Updates the dy value of the vector, representing the change in the y direction.
     *
     * @param dy the new dy value.
     */
    void setDy(int dy);

    /**
     * Retrieves the magnitude of the vector, calculated as the Euclidean distance.
     *
     * @return the magnitude of the vector.
     */
    double getMagnitude();

    /**
     * Retrieves the direction of the vector.
     *
     * @return the direction of the vector.
     * @throws IllegalStateException if the direction is not valid.
     */
    Direction getDirection();
}
