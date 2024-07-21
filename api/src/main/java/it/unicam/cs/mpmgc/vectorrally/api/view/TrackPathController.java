package it.unicam.cs.mpmgc.vectorrally.api.view;

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

public abstract class TrackPathController {
    public List<String> findTrack() {
        String directoryPath = checkRootPath();
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));
        if (!doesDirectoryExist(directory) || !doFilesExist(files)) {
            return null;
        }
        List<String> trackFiles = new ArrayList<>();
        assert files != null;
        for (File file : files) {
            trackFiles.add(file.getName());
        }
        return trackFiles;
    }

    public static String checkRootPath() {
        String currentWorkingDir = System.getProperty("user.dir");
        String directoryPath;
        if (currentWorkingDir.endsWith("app")) {
            directoryPath = "../api/src/main/resources/racetracks";
        } else {
            directoryPath = "api/src/main/resources/racetracks";
        }
        return directoryPath;
    }

    public boolean doesDirectoryExist(File directory) {
        return directory.exists() && directory.isDirectory();
    }

    public boolean doFilesExist(File[] files) {
        return files != null && files.length > 0;
    }

}
