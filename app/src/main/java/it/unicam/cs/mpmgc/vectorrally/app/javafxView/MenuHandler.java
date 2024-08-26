package it.unicam.cs.mpmgc.vectorrally.app.javafxView;

import it.unicam.cs.mpmgc.vectorrally.api.view.FinishGameView;
import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * This class is responsible for handling the menu screen.
 *
 * @version 1.0
 * @since 2024-07-18
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
*/
public class MenuHandler implements FinishGameView {

    @FXML
    private void handleStartButtonAction() {
        SceneManager.getInstance().switchToScene("/settings.fxml");
    }

    @FXML
    private void handleQuitButtonAction() {
        Platform.exit();
    }

    @Override
    public boolean playAnotherMatch() {
        return false;
    }
}
