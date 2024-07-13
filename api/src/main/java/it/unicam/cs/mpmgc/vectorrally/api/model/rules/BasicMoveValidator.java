package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.ComponentPassChecker;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Direction;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;

import java.util.List;

public class BasicMoveValidator implements MoveValidator {
    private final ComponentPassChecker componentPassChecker;

    public BasicMoveValidator(ComponentPassChecker componentPassChecker) {
        this.componentPassChecker = componentPassChecker;
    }

    @Override
    public boolean isValid(Move move, RaceTrack track, List<Player> allPlayers) {
        Position start = move.position();
        int newX = start.getX() + move.acceleration().getDx();
        int newY = start.getY() + move.acceleration().getDy();
        Position end = new Position(newX, newY);
        if (!track.isInBounds(end.getX(), end.getY())) {
            return false;
        }

        if (componentPassChecker.passesThroughComponent(start, end, TrackComponent.WALL)) return false;
        if (componentPassChecker.passesThroughComponent(start, end, TrackComponent.END_LINE)) {
            if (!isValidDirection(move.acceleration().getDirection(), track)) return false;
        }

        for (Player player : allPlayers) {
            if (player.getPosition().equals(end)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidDirection(Direction direction, RaceTrack track) {
        Position startLinePosition = track.getPositionsOfComponent(TrackComponent.START_LINE).get(0);
        Position endLinePosition = track.getPositionsOfComponent(TrackComponent.END_LINE).get(0);
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

    private boolean isPositionOccupied(Position end, List<Player> allPlayers) {
        for (Player player : allPlayers) {
            if (player.getPosition().equals(end)) return true;
        }
        return false;
    }
}
