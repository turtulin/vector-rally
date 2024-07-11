package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

// TODO : Implement the SpeedLimitHandler class
public class SpeedLimitHandler implements GameRule{

    @Override
    public boolean isRespected(Player player) {
        return true;
    }
}
