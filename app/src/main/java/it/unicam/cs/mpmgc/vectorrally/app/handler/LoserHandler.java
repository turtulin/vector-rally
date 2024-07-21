package it.unicam.cs.mpmgc.vectorrally.app.handler;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoserHandler {

    @FXML
    private ImageView loseImage;

    @FXML
    public void initialize() {
        loseImage.setImage(new Image(getClass().getResource("/resources/backgroundGameOver.png").toExternalForm()));
    }
}