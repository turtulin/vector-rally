package it.unicam.cs.mpmgc.vectorrally.app.handler;

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
}
