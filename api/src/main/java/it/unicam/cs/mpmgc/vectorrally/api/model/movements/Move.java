package it.unicam.cs.mpmgc.vectorrally.api.model.movements;

/**
 * Represents the shift from one position to another with a certain acceleration.
 * This record is immutable, ensuring that the state of an instance cannot be modified after it is created.
 *
 * @param acceleration the {@link Vector} representing the acceleration.
 * @param position the {@link Coordinates} representing the starting position.
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public record Move(Vector acceleration, Coordinates position) {

    /**
     * Calculates the destination position based on the starting position and the acceleration.
     *
     * @return the destination {@link Coordinates}.
     */
    public Coordinates getDestination() {
        int destX = this.position.getX() + this.acceleration.getDx();
        int destY = this.position.getY() + this.acceleration.getDy();
        return new Position(destX, destY);
    }
}