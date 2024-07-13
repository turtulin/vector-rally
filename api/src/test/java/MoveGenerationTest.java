import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMovesGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.MoveValidator;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.FourNeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.RaceCar;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.HumanPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMoveValidator;


import java.util.List;
import java.util.ArrayList;

public class MoveGenerationTest {
    @Test
    public void testMoveGeneration() {
        TrackComponent[][] components = new TrackComponent[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                components[i][j] = TrackComponent.ROAD;
            }
        }
        RaceTrack raceTrack = new RaceTrack(components);
        MoveValidator validator = new BasicMoveValidator(new BasicComponentPassChecker(raceTrack));
        BasicMovesGenerator generator = new BasicMovesGenerator(new FourNeighborsGenerator(), validator);

        Car car = new RaceCar(CarColour.RED);
        Player player = new HumanPlayer(car);
        player.setPosition(new Position(5, 5));

        List<Player> players = new ArrayList<>();
        players.add(player);

        List<Move> moves = generator.generatePossibleMoves(player, raceTrack, players);

        for (Move move : moves) {
            Position end = new Position(move.position().getX() + move.acceleration().getDx(), move.position().getY() + move.acceleration().getDy());
            assertTrue(end.getX() >= 0);
            assertTrue(end.getY() >= 0);
        }
    }
}
