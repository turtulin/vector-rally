package it.unicam.cs.mpmgc.vectorrally.api.controller.handlers;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Direction;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.FinishLineWinCondition;

/**
 * This class handles the validation for illegal direction moves.
 *
 * @version 1.0
 * @since 2024-07-11
 */
public class IllegalDirectionHandler implements MoveHandler {
    private final FinishLineWinCondition winCondition;
    private MoveHandler nextHandler;

    public IllegalDirectionHandler(FinishLineWinCondition winCondition) {
        this.winCondition = winCondition;
    }

    public void setNextHandler(MoveHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean handleMove(Player player, Position newPosition) {
        Direction direction = player.getPlayerAcceleration().getDirection();
        if (!winCondition.isValidDirection(direction)) {
            return false;
        }
        if (nextHandler != null) {
            return nextHandler.handleMove(player, newPosition);
        }
        return true;
    }
}
