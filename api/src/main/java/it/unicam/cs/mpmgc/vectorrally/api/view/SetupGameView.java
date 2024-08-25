package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;

/**
 * This interface defines methods for setting up the game configuration in the Vector Rally application.
 * It provides methods for choosing the racetrack, number of players, bot strategy, and shift algorithm.
 *
 * @version 1.0
 * @since 2024-08-21
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface SetupGameView {

    /**
     * Retrieves the chosen racetrack for the game.
     *
     * @return the name of the selected track as a {@link String}.
     */
    String getChosenTrack();

    /**
     * Gets the number of human players selected for the game.
     *
     * @param maxPlayers the maximum number of players allowed, typically based on the selected track.
     * @return the number of human players chosen by the user.
     */
    int getNumHumanPlayers(int maxPlayers);

    /**
     * Chooses the difficulty level of the bot players.
     *
     * @return the selected {@link BotStrategy} for the bot players.
     */
    BotStrategy chooseStrategyDifficulty();

    /**
     * Retrieves the shift algorithm to be used during the game.
     *
     * @return the selected {@link NeighborsGenerator} for generating possible moves.
     */
    NeighborsGenerator getShiftAlgorithm();
}
