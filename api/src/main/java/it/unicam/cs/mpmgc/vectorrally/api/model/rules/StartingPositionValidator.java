package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

public class StartingPositionValidator implements StartingPositionRule {

    @Override
    public boolean isRespected(Player player) {
        return false;
    }

    @Override
    public boolean validateStartingPosition(Player player) {
        return false;
    }
}
