package model.algorithms;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.Node;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    private Node node;
    private Position position;

    @BeforeEach
    void setUp() {
        position = new Position(5, 10);
        node = new Node(position);
    }

    @Test
    void constructorShouldInitializePositionAndCosts() {
        assertEquals(position, node.getPosition());
        assertEquals(Double.MAX_VALUE, node.getGCost());
        assertEquals(0, node.getHCost());
        assertNull(node.getParent());
    }

    @Test
    void getPositionShouldReturnCorrectPosition() {
        assertEquals(position, node.getPosition());
    }

    @Test
    void getAndSetGCostShouldWorkCorrectly() {
        double newGCost = 10.5;
        node.setGCost(newGCost);
        assertEquals(newGCost, node.getGCost());
    }

    @Test
    void getAndSetHCostShouldWorkCorrectly() {
        double newHCost = 5.5;
        node.setHCost(newHCost);
        assertEquals(newHCost, node.getHCost());
    }

    @Test
    void getFCostShouldReturnCorrectValue() {
        double gCost = 10.5;
        double hCost = 5.5;
        node.setGCost(gCost);
        node.setHCost(hCost);
        assertEquals(gCost + hCost, node.getFCost());
    }

    @Test
    void getAndSetParentShouldWorkCorrectly() {
        Node parentNode = new Node(new Position(3, 4));
        node.setParent(parentNode);
        assertEquals(parentNode, node.getParent());
    }

    @Test
    void compareToShouldReturnNegativeWhenThisFCostIsLessThanOtherFCost() {
        Node otherNode = new Node(new Position(1, 1));
        otherNode.setGCost(10);
        otherNode.setHCost(5);

        node.setGCost(5);
        node.setHCost(5);

        assertTrue(node.compareTo(otherNode) < 0);
    }

    @Test
    void compareToShouldReturnPositiveWhenThisFCostIsGreaterThanOtherFCost() {
        Node otherNode = new Node(new Position(1, 1));
        otherNode.setGCost(5);
        otherNode.setHCost(5);

        node.setGCost(10);
        node.setHCost(5);

        assertTrue(node.compareTo(otherNode) > 0);
    }

    @Test
    void compareToShouldReturnZeroWhenFCostsAreEqual() {
        Node otherNode = new Node(new Position(1, 1));
        otherNode.setGCost(10);
        otherNode.setHCost(5);

        node.setGCost(10);
        node.setHCost(5);

        assertEquals(0, node.compareTo(otherNode));
    }
}

