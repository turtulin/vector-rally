package it.unicam.cs.mpmgc.vectorrally.api.model.movements;

/**
 * Represents the possible directions in the Vector Rally game. Each constant in this enum
 * corresponds to a specific direction that a vector, such as acceleration or movement, can take.
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    UP_RIGHT,
    UP_LEFT,
    DOWN_RIGHT,
    DOWN_LEFT,
    NONE;
}
