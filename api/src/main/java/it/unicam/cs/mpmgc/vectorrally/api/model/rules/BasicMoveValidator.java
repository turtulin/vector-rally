package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Direction;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;

import java.util.ArrayList;
import java.util.List;

public class BasicMoveValidator implements MoveValidator {
    @Override
    public boolean isValid(Move move, RaceTrack track, List<Player> allPlayers) {
        if (!track.isInBounds(move.getDestination().getX(), move.getDestination().getY())) return false;
        if (passesThroughComponent(track, move, TrackComponent.WALL)) return false;
        if (passesThroughComponent(track, move, TrackComponent.END_LINE) && !isValidDirection(move.acceleration().getDirection(), track)) return false;
        //if (passesThroughPlayers(move, allPlayers)) return false;
        return !isPositionOccupied(move.getDestination(), allPlayers);
    }

    private boolean isValidDirection(Direction direction, RaceTrack track) {
        Position startLinePosition = track.getPositionsOfComponent(TrackComponent.START_LINE).getFirst();
        Position endLinePosition = track.getPositionsOfComponent(TrackComponent.END_LINE).getFirst();
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

    public boolean passesThroughComponent(RaceTrack track, Move move, TrackComponent component) {
        List<Position> positions = getPositionsBetween(move);
        for (Position position : positions) {
            if (track.getComponentAt(position.getX(), position.getY()) == component) return true;
        }
        return false;
    }

    public boolean passesThroughPlayers(Move move, List<Player> allPlayers) {
        List<Position> positions = getPositionsBetween(move);
        for (Position position : positions) {
            for (Player player : allPlayers) {
                if (player.getPosition().equals(position)) return true;
            }
        }
        return false;
    }

    private List<Position> getPositionsBetween(Move move) {
        int dx = move.acceleration().getDx();
        int dy = move.acceleration().getDy();
        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        int xIncrement = Integer.signum(dx);
        int yIncrement = Integer.signum(dy);
        int x = move.position().getX();
        int y = move.position().getY();
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i <= steps; i++) {
            positions.add(new Position(x, y));
            x += xIncrement;
            y += yIncrement;
        }
        return positions;
    }
}
