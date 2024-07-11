package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.io.IOController;
import it.unicam.cs.mpmgc.vectorrally.api.controller.io.TerminalUtils;

/**
 * This class implements the GameEngine interface, managing the game loop and overall game logic.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class VectorRallyEngine implements GameEngine {
    @Override
    public void startGame() {
        TerminalUtils utilities = new TerminalUtils();
        GameSetup gameSetup = new VectorRallySetup(utilities);
        MatchController matchController = new VectorRallyMatchController(gameSetup, utilities);
        matchController.startGame();
    }
}
