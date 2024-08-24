package it.unicam.cs.mpmgc.vectorrally.app.javafxView;

import it.unicam.cs.mpmgc.vectorrally.api.view.FinishGameView;
import it.unicam.cs.mpmgc.vectorrally.api.view.MatchGameView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * This class is responsible for handling the loser screen.
 *
 * @version 1.0
 * @since 2024-07-17
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 
*/

public class LoserHandler {

    @FXML
    private ImageView loseImage;

    @FXML
    private Button goToHomeButton;

    @FXML
    public void initialize() {
    }

    @FXML
    private void handleGoToHome() {
        SceneManager.getInstance().switchToScene("/menu.fxml");
    }
}
