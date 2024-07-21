package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.io.File;
import java.util.List;

/**
 * Defines methods for input and output operations in the game.
 *
 * @version 1.0
 * @since 2024-07-11
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface IOController {

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

    /**
     * Displays the racetrack.
     *
     * @param raceTrack the racetrack to display.
     * @param players the list of players.
     * @param destinations the list of destinations.
     */
    void printRaceTrack(RaceTrack raceTrack, List<Player> players, List<Position> destinations);

    /**
     * Displays a message.
     *
     * @param message the message to display
     */
    void displayMessage(String message);

    /**
     * Finds the root path of the directory.
     *
     * @return the string of the root path.
     */
    static String checkRootPath() {
        String currentWorkingDir = System.getProperty("user.dir");
        String directoryPath;
        if (currentWorkingDir.endsWith("app")) {
            directoryPath = "../api/src/main/resources/racetracks";
        } else {
            directoryPath = "api/src/main/resources/racetracks";
        }
        return directoryPath;
    }

    /**
     * Checks if the directory exist in the file system.
     *
     * @param directory the directory to check.
     * @return true if the directory exist, false otherwise.
     */
    default boolean doesDirectoryExist(File directory) {
        return directory.exists() && directory.isDirectory();
    }

    /**
     * Checks if the files exist in the directory.
     *
     * @param files the files to check.
     * @return true if the files exist, false otherwise.
     */
    default boolean doFilesExist(File[] files) {
        return files != null && files.length > 0;
    }

}
