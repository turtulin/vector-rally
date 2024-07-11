package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Direction;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;

/**
 * This class checks if a player has crossed the finish line in the vector rally game.
 *
 * @version 1.0
 * @since 2024-07-11
 */
public class FinishLineWinCondition implements WinCondition {
    private final RaceTrack raceTrack;
    private final ComponentPassChecker componentPassChecker;

    /**
     * Constructs a FinishLineWinCondition with the specified race track.
     *
     * @param raceTrack the racetrack to be used for checking the win condition.
     * @throws NullPointerException if raceTrack is null.
     */
    public FinishLineWinCondition(RaceTrack raceTrack) {
        if (raceTrack == null) throw new NullPointerException("RaceTrack cannot be null");
        this.raceTrack = raceTrack;
        this.componentPassChecker = new BasicComponentPassChecker(raceTrack);
    }
    @Override
    public boolean isRespected(Player player) {
        Position currentPosition = player.getPosition();
        Acceleration acceleration = player.getPlayerAcceleration();
        Position newPosition = new Position(currentPosition.getX() + acceleration.getDx(), currentPosition.getY() + acceleration.getDy());
        if (componentPassChecker.passesThroughComponent(currentPosition, newPosition, TrackComponent.START_LINE)) {
            Direction direction = acceleration.getDirection();
            if (!isValidDirection(direction)) return false;
        }
        return true;
    }

    @Override
    public boolean isValidDirection(Direction direction) {
        Position startLinePosition = raceTrack.getPositionsOfComponent(TrackComponent.START_LINE).get(0);
        Position endLinePosition = raceTrack.getPositionsOfComponent(TrackComponent.END_LINE).get(0);

        return switch (getIllegalDirection(startLinePosition, endLinePosition)) {
            case RIGHT -> direction != Direction.RIGHT && direction != Direction.UP_RIGHT && direction != Direction.DOWN_RIGHT;
            case LEFT -> direction != Direction.LEFT && direction != Direction.UP_LEFT && direction != Direction.DOWN_LEFT;
            case UP -> direction != Direction.UP && direction != Direction.UP_LEFT && direction != Direction.UP_RIGHT;
            case DOWN -> direction != Direction.DOWN && direction != Direction.DOWN_LEFT && direction != Direction.DOWN_RIGHT;
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
