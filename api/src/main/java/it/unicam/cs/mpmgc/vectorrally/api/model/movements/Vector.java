package it.unicam.cs.mpmgc.vectorrally.api.model.movements;

/**
 * This interface represents the acceleration in the game, which is a vector quantity
 * having both magnitude and direction. Magnitude refers to the size or length of the vector,
 * while direction refers to the orientation of the vector in space.
 * It provides methods to get and set the dx and dy values and to calculate magnitude and direction.
 *
 * @version 1.0
 * @since 2024-07-10
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface Vector {

    /**
     * Gets the dx value of the acceleration, representing the change in the x direction.
     *
     * @return the dx value.
     */
    int getDx();

    /**
     * Gets the dy value of the acceleration, representing the change in the y direction.
     *
     * @return the dy value.
     */
    int getDy();

    /**
     * Sets the dx value of the acceleration, representing the change in the x direction.
     *
     * @param dx the new dx value.
     */
    void setDx(int dx);

    /**
     * Sets the dy value of the acceleration, representing the change in the y direction.
     *
     * @param dy the new dy value.
     */
    void setDy(int dy);

    /**
     * Gets the magnitude of the vector, calculated as the Euclidean distance.
     *
     * @return the magnitude of the vector.
     */
    double getMagnitude();

    /**
     * Gets the direction of the vector.
     *
     * @return the direction of the vector.
     */
    Direction getDirection();
}
