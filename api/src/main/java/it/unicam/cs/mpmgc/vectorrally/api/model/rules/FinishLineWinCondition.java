package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;

/**
 * This class checks if a player has crossed the finish line in the vector rally game.
 *
 * @version 1.0
 * @since 2024-07-10
 */
public class FinishLineWinCondition implements WinCondition {
    private final RaceTrack raceTrack;

    public FinishLineWinCondition(RaceTrack raceTrack) {
        this.raceTrack = raceTrack;
    }

    @Override
    public boolean checkWin(Player player) {
        return raceTrack.getComponentAt(player.getPosition().getX(), player.getPosition().getY()) == TrackComponent.END_LINE;
    }

    @Override
    public boolean isRespected(Player player) {
        return checkWin(player);
    }
}
