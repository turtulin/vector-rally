package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;

/**
 * This class implements the {@link SetupGameView} interface for a command-line interface (CLI) environment.
 * It handles the setup process of the game by interacting with the user through the console.
 * This class facilitates track selection, number of human players, bot strategy difficulty, and shift algorithm selection.
 *
 * @version 1.0
 * @since 2024-08-21
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
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
