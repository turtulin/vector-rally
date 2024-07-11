package it.unicam.cs.mpmgc.vectorrally.api.model.algorithms;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

import java.util.List;

public interface MoveGenerator {

    /**
     * Generates all possible moves for the given player from the given position.
     *
     * @param player the player for whom to generate the moves.
     * @param position the position from which to generate the moves.
     * @return a list of possible new positions the player can move to.
     */
    List<Position> generateMoves(Player player, Position position);
}
