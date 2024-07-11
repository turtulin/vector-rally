package it.unicam.cs.mpmgc.vectorrally.api.model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

public class TurnHandler implements TurnBasedRule {
    @Override
    public boolean isRespected(Player player) {
        return false;
    }

    @Override
    public Player getNextPlayer(Player currentPlayer) {
        return null;
    }
}
