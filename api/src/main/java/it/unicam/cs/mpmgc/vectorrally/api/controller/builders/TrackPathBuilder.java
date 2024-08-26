package it.unicam.cs.mpmgc.vectorrally.api.controller.builders;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for finding the track files in the directory.
 *
 * @version 1.0
 * @since 2024-07-17
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
*/

public class TrackPathBuilder {

    /**
     * Finds all track files in the specified directory.
     *
     * @return a {@link List} of track file names with a ".txt" extension, or {@code null} if the directory or files are not found.
     */
    public List<String> findTrack() {
        String directoryPath = checkRootPath();
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));
        if (!doesDirectoryExist(directory) || !doFilesExist(files)) return null;
        List<String> trackFiles = new ArrayList<>();
        assert files != null;
        for (File file : files) trackFiles.add(file.getName());
        return trackFiles;
    }

    /**
     * Checks and returns the root path where track files are stored.
     * Adjusts the path based on the current working directory.
     *
     * @return the root path for the track files as a {@link String}.
     */
    public static String checkRootPath() {
        String currentWorkingDir = System.getProperty("user.dir");
        String directoryPath;
        if (currentWorkingDir.endsWith("app")) directoryPath = "../api/src/main/resources/racetracks";
        else directoryPath = "api/src/main/resources/racetracks";
        return directoryPath;
    }

    /**
     * Checks if the given file directory exists and is a directory.
     *
     * @param directory the {@link File} directory to check.
     * @return {@code true} if the directory exists and is valid, {@code false} otherwise.
     */
    public boolean doesDirectoryExist(File directory) {
        return directory.exists() && directory.isDirectory();
    }

    /**
     * Checks if the specified array of files is not null and contains at least one file.
     *
     * @param files the array of {@link File} to check.
     * @return {@code true} if the files array is valid, {@code false} otherwise.
     */
    public boolean doFilesExist(File[] files) {
        return files != null && files.length > 0;
    }

}
