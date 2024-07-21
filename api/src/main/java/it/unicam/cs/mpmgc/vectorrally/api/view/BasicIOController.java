package it.unicam.cs.mpmgc.vectorrally.api.view;

import java.util.List;

public interface BasicIOController {
    /**
     * Finds the track files in the directory.
     *
     * @return a list of track file names
     * @throws Exception if an error occurs while finding the track files
     */
    List<String> findTrack() throws Exception;

    /**
     * Asks the player to proceed to the next turn.
     */
    void waitForNextTurn();
}
