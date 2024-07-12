package it.unicam.cs.mpmgc.vectorrally.api.controller.handlers;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Direction;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.FinishLineWinCondition;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.GameRule;

/**
 * This class handles the validation for illegal direction moves.
 *
 * @version 1.0
 * @since 2024-07-11
 */
public class IllegalDirectionHandler implements MoveHandler {
    private final GameRule rule;
    private MoveHandler nextHandler;

    public IllegalDirectionHandler(GameRule rule) {
        this.rule = rule;
    }

    public void setNextHandler(MoveHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean handleMove(Player player, Position newPosition) {
        if (!rule.isRespected(player)) {
            return false;
        }
        return nextHandler == null || nextHandler.handleMove(player, newPosition);
    }
}