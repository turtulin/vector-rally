package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.BotStrategyFactory;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

import java.util.List;
import java.util.stream.Collectors;

public abstract class DefaultMatchController implements MatchController {

    /**
     * Initializes the bot strategy factory with the specified neighbors' generator.
     *
     * @param neighborsGenerator the neighbors generator
     * @return the bot strategy factory
     */
    protected BotStrategyFactory initializeBotStrategyFactory(NeighborsGenerator neighborsGenerator) {
        return new BotStrategyFactory(neighborsGenerator);
    }

    /**
     * Makes the specified move for the given player.
     *
     * @param player the player making the move
     * @param move the move to be made
     */
    protected void makeMove(Player player, Move move) {
        player.setPosition(move.getDestination());
        player.setPlayerAcceleration(move.acceleration());
    }

    /**
     * Gets the possible destinations for the given moves.
     *
     * @param moves the list of moves
     * @return the list of possible destination positions
     */
    protected List<Position> getPossibleDestinations(List<Move> moves) {
        return moves.stream().map(Move::getDestination).collect(Collectors.toList());
    }
}
