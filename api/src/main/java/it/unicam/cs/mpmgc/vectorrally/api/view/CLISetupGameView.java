package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;

public class CLISetupGameView implements SetupGameView {
    IOController ioController;

    public CLISetupGameView(IOController ioController) {
        this.ioController = ioController;
    }

    @Override
    public String getChosenTrack() {
        TrackPathController trackPathController = new TrackPathController();
        return ioController.pickTrack(trackPathController.findTrack());
    }

    @Override
    public int getNumHumanPlayers(int maxPlayers) {
        return ioController.askNumberOfHumanPlayers(maxPlayers);
    }

    @Override
    public BotStrategy chooseStrategyDifficulty() {
        return ioController.chooseAllBotsStrategyDifficulty();
    }

    @Override
    public NeighborsGenerator getShiftAlgorithm() {
        return ioController.initializeShiftAlgorithm();
    }
}
