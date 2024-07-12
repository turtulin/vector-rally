import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.HumanPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.RaceCar;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;

public class PlayerInitializationTest {
    @Test
    public void testPlayerInitialization() {
        Car car = new RaceCar(CarColour.RED);
        Player player = new HumanPlayer(car);
        Position position = new Position(5, 5);
        player.setPosition(position);

        assertEquals(5, player.getPosition().getX());
        assertEquals(5, player.getPosition().getY());
    }
}
