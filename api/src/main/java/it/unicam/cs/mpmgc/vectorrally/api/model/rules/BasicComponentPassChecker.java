package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.Track;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;

/**
 * This class implements the ComponentPassChecker interface to check if a player passes through a specified component.
 *
 * @version 1.0
 * @since 2024-07-11
 */
public class BasicComponentPassChecker implements ComponentPassChecker {

    private final Track raceTrack;

    public BasicComponentPassChecker(Track raceTrack) {
        this.raceTrack = raceTrack;
    }

    @Override
    public boolean passesThroughComponent(Position startPosition, Position endPosition, TrackComponent component) {
        if (startPosition.equals(endPosition)) return false;
        int dx = endPosition.getX() - startPosition.getX();
        int dy = endPosition.getY() - startPosition.getY();
        return checkPass(startPosition.getX(), startPosition.getY(), dx, dy, component);
    }

    private boolean checkPass(int startX, int startY, int dx, int dy, TrackComponent component) {
        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        int xIncrement = Integer.signum(dx);
        int yIncrement = Integer.signum(dy);
        int x = startX;
        int y = startY;
        for (int i = 0; i <= steps; i++) {
            if (raceTrack.getComponentAt(x, y) == component) return true;
            x += xIncrement;
            y += yIncrement;
        }
        return false;
    }

}
