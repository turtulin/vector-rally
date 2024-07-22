package model.algorithms;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.FourNeighborsGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import java.util.List;

class FourNeighborsGeneratorTest {

    private FourNeighborsGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new FourNeighborsGenerator();
    }

    @Test
    void generateShiftsShouldReturnCorrectAccelerations() {
        Acceleration initialSpeed = new Acceleration(2, 3);
        List<Acceleration> expectedShifts = List.of(
                new Acceleration(1, 3),  // Left
                new Acceleration(3, 3),  // Right
                new Acceleration(2, 2),  // Down
                new Acceleration(2, 4),  // Up
                new Acceleration(2, 3)   // No change
        );
        List<Acceleration> generatedShifts = generator.generateShifts(initialSpeed);
        assertEquals(expectedShifts.size(), generatedShifts.size());
        for (int i = 0; i < expectedShifts.size(); i++) {
            assertTrue(compareAccelerations(expectedShifts.get(i), generatedShifts.get(i)));
        }
    }

    private boolean compareAccelerations(Acceleration a1, Acceleration a2) {
        return a1.getDx() == a2.getDx() && a1.getDy() == a2.getDy();
    }
}

