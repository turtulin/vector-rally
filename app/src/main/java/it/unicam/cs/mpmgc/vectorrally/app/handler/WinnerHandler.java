package it.unicam.cs.mpmgc.vectorrally.app.handler;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WinnerHandler {

    @FXML
    private ImageView winImage;

    @FXML
    public void initialize() {
        winImage.setImage(new Image(getClass().getResource("/resources/backgroundCongratulations.png").toExternalForm()));
    }
}
