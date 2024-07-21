package it.unicam.cs.mpmgc.vectorrally.app.handler;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class is responsible for handling the winner screen.
 *
 * @version 1.0
 * @since 2024-07-17
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class WinnerHandler {

    @FXML
    private ImageView winImage;

    @FXML
    public void initialize() {
        winImage.setImage(new Image(getClass().getResource("/resources/backgroundCongratulations.png").toExternalForm()));
    }
}
