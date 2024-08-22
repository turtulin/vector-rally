package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;

public interface SetupGameView {
    String getChosenTrack();

    int getNumHumanPlayers(int maxPlayers);

    BotStrategy chooseStrategyDifficulty();

    NeighborsGenerator getShiftAlgorithm();
}
