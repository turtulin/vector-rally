package model.players;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.RaceCar;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.DefaultPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultPlayerTest {

    private Car testCar;
    private DefaultPlayer player;

    @BeforeEach
    void setUp() {
        testCar = new RaceCar(CarColour.RED);
        player = new DefaultPlayer(testCar) {
        };
    }

    @Test
    void constructorShouldThrowExceptionWhenCarIsNull() {
        assertThrows(NullPointerException.class, () -> new DefaultPlayer(null) {
        });
    }

    @Test
    void getPlayerAccelerationShouldReturnCorrectAcceleration() {
        Acceleration acceleration = new Acceleration(1, 1);
        testCar.setAcceleration(acceleration);

        Acceleration playerAcceleration = player.getPlayerAcceleration();
        assertTrue(compareAccelerations(acceleration, playerAcceleration));
    }

    @Test
    void setPlayerAccelerationShouldUpdateCarAcceleration() {
        Acceleration acceleration = new Acceleration(1, 1);
        player.setPlayerAcceleration(acceleration);

        Acceleration carAcceleration = testCar.getAcceleration();
        assertTrue(compareAccelerations(acceleration, carAcceleration));
    }

    @Test
    void setPlayerAccelerationShouldThrowExceptionWhenAccelerationIsNull() {
        assertThrows(NullPointerException.class, () -> player.setPlayerAcceleration(null));
    }

    @Test
    void getPlayerCarColourShouldReturnCorrectColour() {
        assertEquals(CarColour.RED, player.getPlayerCarColour());
    }

    @Test
    void getPositionShouldReturnInitialPosition() {
        Position initialPosition = new Position(0, 0);
        assertTrue(comparePositions(initialPosition, player.getPosition()));
    }

    @Test
    void setPositionShouldUpdatePlayerPosition() {
        Position newPosition = new Position(5, 5);
        player.setPosition(newPosition);

        assertTrue(comparePositions(newPosition, player.getPosition()));
    }

    @Test
    void setPositionShouldThrowExceptionWhenPositionIsNull() {
        assertThrows(NullPointerException.class, () -> player.setPosition(null));
    }

    @Test
    void isRacingShouldReturnCorrectStatus() {
        assertFalse(player.isRacing());

        player.setRacing(true);
        assertTrue(player.isRacing());
    }

    @Test
    void setRacingShouldUpdateRacingStatus() {
        player.setRacing(true);
        assertTrue(player.isRacing());

        player.setRacing(false);
        assertFalse(player.isRacing());
    }

    private boolean compareAccelerations(Acceleration a1, Acceleration a2) {
        return a1.getDx() == a2.getDx() && a1.getDy() == a2.getDy();
    }

    private boolean comparePositions(Position p1, Position p2) {
        return p1.getX() == p2.getX() && p1.getY() == p2.getY();
    }
}
