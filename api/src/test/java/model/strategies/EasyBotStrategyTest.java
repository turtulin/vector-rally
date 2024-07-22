package model.strategies;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.DefaultPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.RaceCar;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.EasyBotStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EasyBotStrategyTest {

    private EasyBotStrategy strategy;
    private Player player;

    @BeforeEach
    void setUp() {
        strategy = new EasyBotStrategy();
        player = new DefaultPlayer(new RaceCar(CarColour.RED)) {};
    }

    @Test
    void decideMoveShouldReturnAMoveFromPossibleMoves() {
        List<Move> possibleMoves = Arrays.asList(
                new Move(new Acceleration(1, 0), new Position(1, 0)),
                new Move(new Acceleration(0, 1), new Position(0, 1)),
                new Move(new Acceleration(-1, 0), new Position(-1, 0))
        );

        Move chosenMove = strategy.decideMove(player, possibleMoves);

        assertTrue(possibleMoves.contains(chosenMove));
    }

    @Test
    void decideMoveShouldReturnRandomMove() {
        List<Move> possibleMoves = Arrays.asList(
                new Move(new Acceleration(1, 0), new Position(1, 0)),
                new Move(new Acceleration(0, 1), new Position(0, 1)),
                new Move(new Acceleration(-1, 0), new Position(-1, 0))
        );

        Move firstChosenMove = strategy.decideMove(player, possibleMoves);
        boolean differentMoveChosen = false;

        for (int i = 0; i < 10; i++) {
            Move chosenMove = strategy.decideMove(player, possibleMoves);
            if (!chosenMove.equals(firstChosenMove)) {
                differentMoveChosen = true;
                break;
            }
        }

        assertTrue(differentMoveChosen, "The strategy did not choose a different move in 10 attempts");
    }
}
