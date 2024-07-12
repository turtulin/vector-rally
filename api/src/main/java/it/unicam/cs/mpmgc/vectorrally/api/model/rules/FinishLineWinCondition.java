package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.*;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;

/**
 * This class checks if a player has crossed the finish line in the vector rally game.
 *
 * @version 1.0
 * @since 2024-07-11
 */
public class FinishLineWinCondition implements GameRule {
    private final RaceTrack raceTrack;

    public FinishLineWinCondition(RaceTrack raceTrack) {
        this.raceTrack = raceTrack;
    }

    @Override
    public boolean isRespected(Player player) {
        Position position = player.getPosition();
        return raceTrack.getComponentAt(position.getX(), position.getY()) == TrackComponent.END_LINE;
    }


    public boolean isValidDirection(Direction direction) {
        Position startLinePosition = raceTrack.getPositionsOfComponent(TrackComponent.START_LINE).get(0);
        Position endLinePosition = raceTrack.getPositionsOfComponent(TrackComponent.END_LINE).get(0);
        return switch (getIllegalDirection(startLinePosition, endLinePosition)) {
            case RIGHT -> direction != Direction.LEFT && direction != Direction.UP_LEFT && direction != Direction.DOWN_LEFT;
            case LEFT -> direction != Direction.RIGHT && direction != Direction.UP_RIGHT && direction != Direction.DOWN_RIGHT;
            case UP -> direction != Direction.DOWN && direction != Direction.DOWN_LEFT && direction != Direction.DOWN_RIGHT;
            case DOWN -> direction != Direction.UP && direction != Direction.UP_LEFT && direction != Direction.UP_RIGHT;
            default -> false;
        };
    }

    private Direction getIllegalDirection(Position start, Position end) {
        if (end.getX() > start.getX()) return Direction.RIGHT;
        if (end.getX() < start.getX()) return Direction.LEFT;
        if (end.getY() > start.getY()) return Direction.DOWN;
        return Direction.UP;
    }
}
