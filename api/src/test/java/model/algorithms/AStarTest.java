package model.algorithms;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.AStar;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.EightNeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Acceleration;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Coordinates;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Vector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

class AStarTest {

    private AStar aStar;

    @BeforeEach
    void setUp() {
        BiFunction<Coordinates, Coordinates, Float> heuristic = AStar::calculateHeuristic;
        NeighborsGenerator neighborsGenerator = new EightNeighborsGenerator();
        aStar = new AStar(heuristic, neighborsGenerator);
    }

    @Test
    void findPathShouldReturnCorrectPath() {
        Coordinates start = new Position(0, 0);
        Coordinates goal = new Position(3, 3);
        Vector initialAcceleration = new Acceleration(0, 0);

        List<Position> expectedPath = List.of(
                new Position(0, 0),
                new Position(1, 1),
                new Position(2, 2),
                new Position(3, 3)
        );

        List<Coordinates> actualPath = aStar.findPath(start, goal, initialAcceleration);

        assertEquals(expectedPath.size(), actualPath.size());
        for (int i = 0; i < expectedPath.size(); i++) {
            assertEquals(expectedPath.get(i), actualPath.get(i));
        }
    }

    @Test
    void calculateCostShouldReturnCorrectCost() {
        Coordinates start = new Position(0, 0);
        Coordinates goal = new Position(3, 3);
        Vector initialAcceleration = new Acceleration(0, 0);

        double expectedCost = 3.0;
        double actualCost = aStar.calculateCost(start, goal, initialAcceleration);

        assertEquals(expectedCost, actualCost);
    }
}
