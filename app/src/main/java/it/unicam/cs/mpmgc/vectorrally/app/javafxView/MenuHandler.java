/*
package it.unicam.cs.mpmgc.vectorrally.app.javafxView;

import it.unicam.cs.mpmgc.vectorrally.api.view.MatchGameView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

*/
/**
 * This class is responsible for handling the menu screen.
 *
 * @version 1.0
 * @since 2024-07-18
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 *//*

public class MenuHandler {

    public Button playButton;
    public Button quitGameButton;
    private MatchGameView guiGameView;

   */
/* @FXML
    private void handleStartButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/settings.fxml"));
        TrackPathController IOController = new GraphicalIOController();
        loader.setControllerFactory(param -> new SettingsHandler(IOController));
        Parent settingsRoot = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(settingsRoot));
        stage.show();
    }

    @FXML
    private void handleQuitButtonAction(ActionEvent event) {
        Platform.exit();
    }*//*


    @FXML
    public void initialize() {
        this.guiGameView = new GUIMatchGameView();
    }

    @FXML
    private void handleStartButtonAction() {
        guiGameView.handlePlayAgain();
    }

    @FXML
    private void handleQuitButtonAction() {
        guiGameView.handleQuitGame();
    }
}
*/
