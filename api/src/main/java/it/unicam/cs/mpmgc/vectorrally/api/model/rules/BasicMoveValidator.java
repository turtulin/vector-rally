package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Coordinates;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Direction;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.Track;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;

import java.util.ArrayList;
import java.util.List;

/**
 * Validates moves in the Vector Rally game according to basic game rules. This class implements
 * the {@link MoveValidator} interface and provides methods to check if a move is valid based on
 * track boundaries, component interactions, and player positions.
 *
 * @version 1.0
 * @since 2024-07-10
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class BasicMoveValidator implements MoveValidator {
    @Override
    public boolean isValid(Move move, Track track, List<Player> allPlayers) {
        if (!track.isInBounds(move.getDestination().getX(), move.getDestination().getY())) return false;
        if (passesThroughComponent(track, move, TrackComponent.WALL)) return false;
        if (passesThroughComponent(track, move, TrackComponent.END_LINE) && !isValidDirection(move.acceleration().getDirection(), track)) return false;
        if (passesThroughPlayers(move, allPlayers)) return false;
        if (endsInComponent(move, track, TrackComponent.WALL)) return false;
        return !isPositionOccupied(move.getDestination(), allPlayers);
    }

    @Override
    public boolean endsInComponent(Move move, Track track, TrackComponent component) {
        return track.getComponentAt(move.getDestination().getX(), move.getDestination().getY()) == component;
    }

    @Override
    public boolean passesThroughComponent(Track track, Move move, TrackComponent component) {
        List<Coordinates> positions = getPositionsBetween(move);
        for (Coordinates position : positions) {
            if (track.getComponentAt(position.getX(), position.getY()) == component) return true;
        }
        return false;
    }

    @Override
    public boolean passesThroughPlayers(Move move, List<Player> allPlayers) {
        List<Coordinates> positions = getPositionsBetween(move);
        for (Coordinates position : positions) {
            for (Player player : allPlayers) {
                if (player.getPosition().equals(position) && !(move.position().equals(player.getPosition()))) return true;
            }
        }
        return false;
    }

    @Override
    public List<Coordinates> getPositionsBetween(Move move) {
        int dx = move.acceleration().getDx();
        int dy = move.acceleration().getDy();
        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        List<Coordinates> positions = new ArrayList<>();
        int x = move.position().getX();
        int y = move.position().getY();
        for (int i = 0; i <= steps; i++) {
            positions.add(new Position(x, y));
            if (steps != 0) {
                x += dx / steps;
                y += dy / steps;
            }
        }
        return positions;
    }

    private boolean isValidDirection(Direction direction, Track track) {
        Coordinates startLinePosition = track.getPositionsOfComponent(TrackComponent.START_LINE).getFirst();
        Coordinates endLinePosition = track.getPositionsOfComponent(TrackComponent.END_LINE).getFirst();
        return switch (getIllegalDirection(startLinePosition, endLinePosition)) {
            case RIGHT -> direction != Direction.LEFT && direction != Direction.UP_LEFT && direction != Direction.DOWN_LEFT;
            case LEFT -> direction != Direction.RIGHT && direction != Direction.UP_RIGHT && direction != Direction.DOWN_RIGHT;
            case UP -> direction != Direction.DOWN && direction != Direction.DOWN_LEFT && direction != Direction.DOWN_RIGHT;
            case DOWN -> direction != Direction.UP && direction != Direction.UP_LEFT && direction != Direction.UP_RIGHT;
            default -> false;
        };
    }

    private Direction getIllegalDirection(Coordinates start, Coordinates end) {
        if (end.getX() > start.getX()) return Direction.RIGHT;
        if (end.getX() < start.getX()) return Direction.LEFT;
        if (end.getY() > start.getY()) return Direction.DOWN;
        return Direction.UP;
    }

    private boolean isPositionOccupied(Coordinates end, List<Player> allPlayers) {
        for (Player player : allPlayers) {
            if (player.getPosition().equals(end)) return true;
        }
        return false;
    }
}
