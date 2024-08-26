package it.unicam.cs.mpmgc.vectorrally.app.javafxView;

import javafx.fxml.FXML;

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
    private void handleGoToHome() {
        SceneManager.getInstance().switchToScene("/menu.fxml");
    }
}
