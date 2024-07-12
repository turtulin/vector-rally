import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.FourNeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import java.util.List;

public class NeighborsGenerationTest {
    @Test
    public void testNeighborsGeneration() {
        FourNeighborsGenerator generator = new FourNeighborsGenerator();
        Position position = new Position(5, 5);
        List<Position> neighbors = generator.generateShifts(position);

        assertTrue(neighbors.contains(new Position(4, 5)));
        assertTrue(neighbors.contains(new Position(6, 5)));
        assertTrue(neighbors.contains(new Position(5, 4)));
        assertTrue(neighbors.contains(new Position(5, 6)));

        // Ensure no negative positions are generated
        for (Position neighbor : neighbors) {
            assertTrue(neighbor.getX() >= 0);
            assertTrue(neighbor.getY() >= 0);
        }
    }
}
