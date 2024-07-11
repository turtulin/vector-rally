package it.unicam.cs.mpmgc.vectorrally.api.controller.handlers;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.ComponentPassChecker;

public class WallHandler implements MoveHandler {
    private final ComponentPassChecker componentPassChecker;
    private MoveHandler nextHandler;

    public WallHandler(ComponentPassChecker componentPassChecker) {
        this.componentPassChecker = componentPassChecker;
    }

    public void setNextHandler(MoveHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean handleMove(Player player, Position newPosition) {
        Position currentPosition = player.getPosition();
        if (componentPassChecker.passesThroughComponent(currentPosition, newPosition, TrackComponent.WALL)) {
            player.setRacing(false);
            return false;
        }
        if (nextHandler != null) {
            return nextHandler.handleMove(player, newPosition);
        }
        return true;
    }
}
