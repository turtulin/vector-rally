package model.players;

import it.unicam.cs.mpmgc.vectorrally.api.model.players.HumanPlayer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class HumanPlayerTest {

    @Test
    void constructorShouldThrowExceptionWhenCarIsNull() {
        assertThrows(NullPointerException.class, () -> new HumanPlayer(null));
    }
}

