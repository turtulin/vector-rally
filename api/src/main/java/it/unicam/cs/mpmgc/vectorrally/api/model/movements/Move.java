package it.unicam.cs.mpmgc.vectorrally.api.model.movements;

/**
 * Represents the shift from one position to another with a certain acceleration.
 * This record is immutable, ensuring that the state of an instance cannot be modified after it is created.
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public record Move(Acceleration acceleration, Position position) implements Shift {

    /**
     * Calculates the destination position based on the starting position and the acceleration.
     *
     * @return the destination position.
     */
    public Position getDestination() {
        int destX = this.position.getX() + this.acceleration.getDx();
        int destY = this.position.getY() + this.acceleration.getDy();
        return new Position(destX, destY);
    }
}