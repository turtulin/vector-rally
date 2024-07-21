package model.players;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.RaceCar;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.DefaultPlayer;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.Car;
import it.unicam.cs.mpmgc.vectorrally.api.model.cars.CarColour;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DefaultPlayerTest {

    private DefaultPlayer player;
    private Car car;

    @BeforeEach
    void setUp() {
        car = new RaceCar(CarColour.RED);
        player = new DefaultPlayer(car) {};
    }

    @Test
    void constructorShouldThrowExceptionWhenCarIsNull() {
        assertThrows(NullPointerException.class, () -> new DefaultPlayer(null) {});
    }

    @Test
    void getPlayerAccelerationShouldReturnCarAcceleration() {
        Acceleration acceleration = new Acceleration(1, 1);
        car.setAcceleration(acceleration);
        assertEquals(acceleration, player.getPlayerAcceleration());
    }

    @Test
    void setPlayerAccelerationShouldThrowExceptionWhenAccelerationIsNull() {
        assertThrows(NullPointerException.class, () -> player.setPlayerAcceleration(null));
    }

    @Test
    void setPlayerAccelerationShouldUpdateCarAcceleration() {
        Acceleration acceleration = new Acceleration(1, 1);
        player.setPlayerAcceleration(acceleration);
        assertEquals(acceleration, car.getAcceleration());
    }

    @Test
    void getPlayerCarColourShouldReturnCarColour() {
        assertEquals(CarColour.RED, player.getPlayerCarColour());
    }

    @Test
    void getPositionShouldReturnInitialPosition() {
        assertEquals(new Position(0, 0), player.getPosition());
    }

    @Test
    void setPositionShouldThrowExceptionWhenPositionIsNull() {
        assertThrows(NullPointerException.class, () -> player.setPosition(null));
    }

    @Test
    void setPositionShouldUpdatePosition() {
        Position newPosition = new Position(2, 3);
        player.setPosition(newPosition);
        assertEquals(newPosition, player.getPosition());
    }

    @Test
    void isRacingShouldReturnInitialValue() {
        assertFalse(player.isRacing());
    }

    @Test
    void setRacingShouldUpdateIsRacing() {
        player.setRacing(true);
        assertTrue(player.isRacing());
    }
}

