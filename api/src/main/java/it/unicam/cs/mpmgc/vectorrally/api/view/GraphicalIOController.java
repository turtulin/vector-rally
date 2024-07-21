package it.unicam.cs.mpmgc.vectorrally.api.view;


import it.unicam.cs.mpmgc.vectorrally.api.model.movements.Position;
import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * This class is responsible for handling the input and output operations of the game.
 *
 * @version 1.0
 * @since 2024-07-17
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */

public class GraphicalIOController implements IOController {

    @Override
    public List<String> findTrack() {
        String directoryPath = IOController.checkRootPath();
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));
        List<String> trackFiles = new ArrayList<>();
        assert files != null;
        for (File file : files) {
            trackFiles.add(file.getName());
        }
        return trackFiles;
    }

    @Override
    public void waitForNextTurn() {
        CountDownLatch latch = new CountDownLatch(1);
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void printRaceTrack(RaceTrack raceTrack, List<Player> players, List<Position> destinations) {

    }

    @Override
    public void displayMessage(String message) {

    }
}
