package it.unicam.cs.mpmgc.vectorrally.api.model.racetrack;

/**
 * This enum represents the different elements of a racetrack.
 * Each element is associated with a specific character symbol.
 *
 * @version 1.0
 * @since 2024-07-10
 */
public enum TrackComponent implements Component {
    WALL('w'),
    ROAD(' '),
    START_LINE('s'),
    END_LINE('e'),
    OIL_SPOT('o'),
    START_POSITION('p');

    private final char symbol;

    TrackComponent(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    /**
     * Gets the RaceTrackComponent corresponding to the given character.
     *
     * @param c the character representing a track component.
     * @return the corresponding RaceTrackComponent.
     * @throws IllegalArgumentException if the character does not match any component.
     */
    public static TrackComponent fromChar(char c) {
        for (TrackComponent component : values()) {
            if (component.symbol == c) {
                return component;
            }
        }
        throw new IllegalArgumentException("Unknown track component: " + c);
    }
}
