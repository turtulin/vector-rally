package it.unicam.cs.mpmgc.vectorrally.api.controller.match;

import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.GameSetup;

public class GUIGameEngine implements GameEngine{


    private final GameSetup setup;

    public GUIGameEngine(GameSetup setup) {
        this.setup = setup;
    }

    @Override
    public void startGame() throws Exception {

        this.setup.initializeTrack();
    }
}
