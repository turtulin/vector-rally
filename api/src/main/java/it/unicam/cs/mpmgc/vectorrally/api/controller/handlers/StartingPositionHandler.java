package it.unicam.cs.mpmgc.vectorrally.api.controller.handlers;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.GameRule;

public class StartingPositionHandler implements MoveHandler {
    private final GameRule rule;
    private MoveHandler nextHandler;

    public StartingPositionHandler(GameRule rule) {
        this.rule = rule;
    }

    public void setNextHandler(MoveHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean handleMove(Player player, Position newPosition) {
        player.setPosition(newPosition);
        if (!rule.isRespected(player)) {
            return false;
        }
        return nextHandler == null || nextHandler.handleMove(player, newPosition);
    }
}
