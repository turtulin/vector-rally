package model.rules;

import it.unicam.cs.mpmgc.vectorrally.api.model.cars.RaceCar;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.rules.BasicMoveValidator;
import org.junit.jupiter.api.BeforeEach;

class BasicMoveValidatorTest {

    private BasicMoveValidator basicMoveValidator;
    private RaceTrack raceTrack;
    private Player player;
    private RaceCar raceCar;

    @BeforeEach
    void setUp() {
        basicMoveValidator = new BasicMoveValidator();
    }


}
