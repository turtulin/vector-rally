package it.unicam.cs.mpmgc.vectorrally.api.model.racetrack;

/**
 * Represents the different elements of a racetrack. Each element is associated with
 * a specific character symbol that can be used to construct and represent the racetrack.
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public enum TrackComponent implements Component {
    WALL('w'),
    ROAD(' '),
    START_LINE('s'),
    END_LINE('e'),
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
     * Retrieves the {@link TrackComponent} corresponding to the given character.
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
