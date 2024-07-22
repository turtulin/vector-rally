package it.unicam.cs.mpmgc.vectorrally.api.view;

import java.util.concurrent.CountDownLatch;

/**
 * This class is responsible for handling the input and output operations of the game.
 *
 * @version 1.0
 * @since 2024-07-17
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */

public class GraphicalIOController extends TrackPathController implements BasicIOController {
    public void waitForNextTurn() {
        CountDownLatch latch = new CountDownLatch(1);
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
