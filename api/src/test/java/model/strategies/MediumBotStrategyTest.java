package model.strategies;

import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.MediumBotStrategy;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.DefaultPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.RaceCar;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MediumBotStrategyTest {

    private MediumBotStrategy strategy;
    private Player player;

    @BeforeEach
    void setUp() {
        strategy = new MediumBotStrategy();
        player = new DefaultPlayer(new RaceCar(CarColour.RED)) {};
    }

    @Test
    void decideMoveShouldReturnTheLongestMove() {
        List<Move> possibleMoves = Arrays.asList(
                new Move(new Acceleration(1, 1), new Position(0, 0)),
                new Move(new Acceleration(2, 2), new Position(0, 0)),
                new Move(new Acceleration(3, 3), new Position(0, 0))
        );

        Move expectedMove = possibleMoves.get(2);
        Move actualMove = strategy.decideMove(player, possibleMoves);

        assertEquals(expectedMove, actualMove);
    }

    @Test
    void decideMoveShouldReturnTheOnlyMoveWhenOneMoveIsAvailable() {
        List<Move> possibleMoves = List.of(
                new Move(new Acceleration(1, 1), new Position(0, 0))
        );

        Move expectedMove = possibleMoves.getFirst();
        Move actualMove = strategy.decideMove(player, possibleMoves);

        assertEquals(expectedMove, actualMove);
    }

    @Test
    void decideMoveShouldHandleMovesWithSameDistance() {
        List<Move> possibleMoves = Arrays.asList(
                new Move(new Acceleration(1, 1), new Position(0, 0)),
                new Move(new Acceleration(1, -1), new Position(0, 0))
        );

        Move expectedMove = possibleMoves.getFirst();
        Move actualMove = strategy.decideMove(player, possibleMoves);

        assertEquals(expectedMove, actualMove);
    }
}
