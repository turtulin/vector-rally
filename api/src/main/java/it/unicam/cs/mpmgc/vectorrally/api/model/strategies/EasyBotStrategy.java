package it.unicam.cs.mpmgc.vectorrally.api.model.strategies;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

import java.util.List;
import java.util.Random;

/**
 * Implements the {@link DecisionStrategy} interface for the easy difficulty level.
 * This strategy chooses a random move from the list of possible moves.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class EasyBotStrategy implements DecisionStrategy {
    private final Random random;

    /**
     * Constructs an EasyBotStrategy with a new random number generator.
     */
    public EasyBotStrategy() {
        this.random = new Random();
    }

    @Override
    public Move decideMove(Player player, List<Move> possibleMoves) {
        return possibleMoves.get(random.nextInt(possibleMoves.size()));
    }
}
