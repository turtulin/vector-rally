package it.unicam.cs.mpmgc.vectorrally.app.handler;

import it.unicam.cs.mpmgc.vectorrally.api.controller.match.GameEngine;
import it.unicam.cs.mpmgc.vectorrally.api.controller.match.VectorRallyEngine;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.Track;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RaceHandler {

    @FXML
    private Label trackLabel;

    @FXML
    private Label difficultyLabel;

    @FXML
    private Label shiftRuleLabel;

    public void setTrack(String track) {
        if (trackLabel != null) {
            trackLabel.setText(track);
        }
    }

    public void setDifficulty(String difficulty) {
        if (difficultyLabel != null) {
            difficultyLabel.setText(difficulty);
        }
    }

    public void setShiftRule(String shiftRule) {
        if (shiftRuleLabel != null) {
            shiftRuleLabel.setText(shiftRule);
        }
    }

    private void handleSetup() throws Exception {
        String difficulty = this.difficultyLabel.getText();
        String track = this.trackLabel.getText();
        String shiftRule = this.shiftRuleLabel.getText();
        GameEngine gameController = new VectorRallyEngine();
        gameController.startGame();
    }
}
