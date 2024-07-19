package it.unicam.cs.mpmgc.vectorrally.api.view;

import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Move;
import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;

import java.io.File;
import java.util.List;

/**
 * This interface defines methods for input and output operations in the game.
 *
 * @version 1.0
 * @since 2024-07-11
 *
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public interface IOControllerNew {

    /**
     * Finds the track files in the directory.
     * @return the chosen track file name.
     */
    List<String> findTrack() throws Exception;

    /**
     * Asks if the player wants to play another match.
     * @return true if the player wants to play another match, false otherwise.
     */
    boolean askToPlayAnotherMatch();


    /**
     * Asks the player to choose a move from the available moves.
     * @param possibleMoves the list of possible moves.
     * @return the index of the chosen move.
     */
    int chooseMove(List<Move> possibleMoves);

    /**
     * Asks the player to choose a starting position from the available positions.
     * @param player the player that has to choose the starting position.
     * @param availablePositions the list of available positions.
     * @return the chosen starting position.
     */
    Position chooseStartingPosition(Player player, List<Position> availablePositions);

    /**
     * Finds the root path of the directory.
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
     * @param directory the directory to check.
     * @return true if the directory exist, false otherwise.
     */
    default boolean doesDirectoryExist(File directory) {
        return directory.exists() && directory.isDirectory();
    }

    /**
     * Checks if the files exist in the directory.
     * @param files the files to check.
     * @return true if the files exist, false otherwise.
     */
    default boolean doFilesExist(File[] files) {
        return files != null && files.length > 0;
    }

}
