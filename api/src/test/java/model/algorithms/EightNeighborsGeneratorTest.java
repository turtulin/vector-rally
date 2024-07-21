package model.algorithms;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.EightNeighborsGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import java.util.List;

public class EightNeighborsGeneratorTest {

    private EightNeighborsGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new EightNeighborsGenerator();
    }

    @Test
    void generateShiftsShouldReturnNineShifts() {
        Acceleration speed = new Acceleration(2, 3);
        List<Acceleration> shifts = generator.generateShifts(speed);
        assertEquals(9, shifts.size());
    }

    @Test
    void generateShiftsShouldReturnCorrectShifts() {
        Acceleration speed = new Acceleration(2, 3);
        List<Acceleration> shifts = generator.generateShifts(speed);

        assertTrue(shifts.contains(new Acceleration(1, 2)));
        assertTrue(shifts.contains(new Acceleration(1, 3)));
        assertTrue(shifts.contains(new Acceleration(1, 4)));
        assertTrue(shifts.contains(new Acceleration(2, 2)));
        assertTrue(shifts.contains(new Acceleration(2, 4)));
        assertTrue(shifts.contains(new Acceleration(3, 2)));
        assertTrue(shifts.contains(new Acceleration(3, 3)));
        assertTrue(shifts.contains(new Acceleration(3, 4)));
        assertTrue(shifts.contains(new Acceleration(2, 3)));
    }

    @Test
    void generateShiftsShouldHandleNegativeSpeed() {
        Acceleration speed = new Acceleration(-1, -2);
        List<Acceleration> shifts = generator.generateShifts(speed);

        assertTrue(shifts.contains(new Acceleration(-2, -3)));
        assertTrue(shifts.contains(new Acceleration(-2, -2)));
        assertTrue(shifts.contains(new Acceleration(-2, -1)));
        assertTrue(shifts.contains(new Acceleration(-1, -3)));
        assertTrue(shifts.contains(new Acceleration(-1, -1)));
        assertTrue(shifts.contains(new Acceleration(0, -3)));
        assertTrue(shifts.contains(new Acceleration(0, -2)));
        assertTrue(shifts.contains(new Acceleration(0, -1)));
        assertTrue(shifts.contains(new Acceleration(-1, -2)));
    }

    @Test
    void generateShiftsShouldHandleZeroSpeed() {
        Acceleration speed = new Acceleration(0, 0);
        List<Acceleration> shifts = generator.generateShifts(speed);

        assertTrue(shifts.contains(new Acceleration(-1, -1)));
        assertTrue(shifts.contains(new Acceleration(-1, 0)));
        assertTrue(shifts.contains(new Acceleration(-1, 1)));
        assertTrue(shifts.contains(new Acceleration(0, -1)));
        assertTrue(shifts.contains(new Acceleration(0, 1)));
        assertTrue(shifts.contains(new Acceleration(1, -1)));
        assertTrue(shifts.contains(new Acceleration(1, 0)));
        assertTrue(shifts.contains(new Acceleration(1, 1)));
        assertTrue(shifts.contains(new Acceleration(0, 0)));
    }
}
