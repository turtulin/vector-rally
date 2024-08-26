package it.unicam.cs.mpmgc.vectorrally.app.javafxView;

import it.unicam.cs.mpmgc.vectorrally.api.model.players.Player;
import it.unicam.cs.mpmgc.vectorrally.api.view.GameMessageProvider;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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

    private final GameMessageProvider messageProvider = new GameMessageProvider();

/**
     * Sets the winner's name to be displayed on the screen.
     * @param winner the name of the player who won the game
     */
    public void setWinner(Player winner) {
        playerNameLabel.setText(messageProvider.getWinMessage(winner));
    }

    @FXML
    private void handleGoToHome() {
        SceneManager.getInstance().switchToScene("/menu.fxml");
    }
}
