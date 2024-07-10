package it.unicam.cs.mpmgc.vectorrally.api.model.movements;

/**
 * This record represents the shift from one position to another with a certain acceleration.
 * It is immutable.
 *
 * @version 1.0
 * @since 2024-07-10
 */
public record Move(Acceleration acceleration, Coordinates position) implements Shift {
}