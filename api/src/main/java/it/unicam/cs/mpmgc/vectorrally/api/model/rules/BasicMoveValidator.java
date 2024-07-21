package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Direction;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
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
    public boolean isValid(Move move, RaceTrack track, List<Player> allPlayers) {
        if (!track.isInBounds(move.getDestination().getX(), move.getDestination().getY())) return false;
        if (passesThroughComponent(track, move, TrackComponent.WALL)) return false;
        if (passesThroughComponent(track, move, TrackComponent.END_LINE) && !isValidDirection(move.acceleration().getDirection(), track)) return false;
        if (passesThroughPlayers(move, allPlayers)) return false;
        return !isPositionOccupied(move.getDestination(), allPlayers);
    }

    /**
     * Checks if the move passes through a specified component on the track.
     *
     * @param track the racetrack being validated.
     * @param move the move to be checked.
     * @param component the track component to check for.
     * @return true if the move passes through the component, false otherwise.
     */
    public boolean passesThroughComponent(RaceTrack track, Move move, TrackComponent component) {
        List<Position> positions = getPositionsBetween(move);
        for (Position position : positions) {
            if (track.getComponentAt(position.getX(), position.getY()) == component) return true;
        }
        return false;
    }

    /**
     * Checks if the move passes through any player on the track.
     *
     * @param move the move to be checked.
     * @param allPlayers the list of all players in the game.
     * @return true if the move passes through any player, false otherwise.
     */
    public boolean passesThroughPlayers(Move move, List<Player> allPlayers) {
        List<Position> positions = getPositionsBetween(move);
        for (Position position : positions) {
            for (Player player : allPlayers) {
                if (player.getPosition().equals(position) && !(move.position().equals(player.getPosition()))) return true;
            }
        }
        return false;
    }

    /**
     * Gets the positions between the start and end of the move.
     *
     * @param move the move to get positions for.
     * @return a list of positions between the start and end of the move.
     */
    public List<Position> getPositionsBetween(Move move) {
        int dx = move.acceleration().getDx();
        int dy = move.acceleration().getDy();
        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        List<Position> positions = new ArrayList<>();
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

    /**
     * Checks if the direction of the move is valid based on the start and end line positions.
     *
     * @param direction the direction of the move.
     * @param track the racetrack being validated.
     * @return true if the direction is valid, false otherwise.
     */
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

    /**
     * Gets the illegal direction of a move based on the start and end positions.
     *
     * @param start the start position of the move.
     * @param end the end position of the move.
     * @return the illegal direction of the move.
     */
    private Direction getIllegalDirection(Position start, Position end) {
        if (end.getX() > start.getX()) return Direction.RIGHT;
        if (end.getX() < start.getX()) return Direction.LEFT;
        if (end.getY() > start.getY()) return Direction.DOWN;
        return Direction.UP;
    }

    /**
     * Checks if a position is occupied by a player.
     *
     * @param end the position to check.
     * @param allPlayers the list of all players in the game.
     * @return true if the position is occupied, false otherwise.
     */
    private boolean isPositionOccupied(Position end, List<Player> allPlayers) {
        for (Player player : allPlayers) {
            if (player.getPosition().equals(end)) return true;
        }
        return false;
    }
}
