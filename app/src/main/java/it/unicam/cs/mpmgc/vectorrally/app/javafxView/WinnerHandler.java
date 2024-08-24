package it.unicam.cs.mpmgc.vectorrally.app.javafxView;

import it.unicam.cs.mpmgc.vectorrally.api.view.FinishGameView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

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
    private Label playerNameLabel;

    @FXML
    private ImageView winImage;

    @FXML
    private Button goToHomeButton;

    @FXML
    public void initialize() {
    }

/**
     * Sets the winner's name to be displayed on the screen.
     *
     * @param winnerName the name of the player who won the game
     */

    public void setWinner(String winnerName) {
        playerNameLabel.setText("Congratulations " + winnerName + "!");
    }

    @FXML
    private void handleGoToHome() {
        SceneManager.getInstance().switchToScene("/menu.fxml");
    }
}
