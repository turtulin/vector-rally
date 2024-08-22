/*
package it.unicam.cs.mpmgc.vectorrally.app.javafxView;

import it.unicam.cs.mpmgc.vectorrally.api.view.MatchGameView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

*/
/**
 * This class is responsible for handling the winner screen.
 *
 * @version 1.0
 * @since 2024-07-17
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 *//*

public class WinnerHandler {

    public ImageView winImage;
    public Label winnerNameLabel;
    public Button playAgainButton;
    public Button quitGameButton;
    private MatchGameView guiGameView;

    @FXML
    public void initialize() {
        winImage.setImage(new Image(Objects.requireNonNull(getClass().getResource("/resources/backgroundCongratulations.png")).toExternalForm()));
        guiGameView = new GUIMatchGameView();
    }

    */
/**
     * Sets the winner's name to be displayed on the screen.
     *
     * @param winnerName the name of the player who won the game
     *//*

    public void setWinner(String winnerName) {
        winnerNameLabel.setText("Congratulations " + winnerName + "!");
    }

    @FXML
    private void handlePlayAgainButtonAction() {
        guiGameView.handlePlayAgain();
    }

    @FXML
    private void handleQuitGameButtonAction() {
        guiGameView.handleQuitGame();
    }
}
*/
