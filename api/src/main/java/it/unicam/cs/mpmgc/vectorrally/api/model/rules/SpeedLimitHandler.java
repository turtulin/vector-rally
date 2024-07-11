package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

// TODO : Implement the SpeedLimitRule interface and its methods
public class SpeedLimitHandler implements SpeedLimitRule {

    @Override
    public boolean isRespected(Player player) {
        return false;
    }

    @Override
    public boolean checkSpeed(Player player) {
        return false;
    }
}
