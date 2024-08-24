package it.unicam.cs.mpmgc.vectorrally.app.javafxView;

import it.unicam.cs.mpmgc.vectorrally.api.controller.match.BasicGameEngine;
import it.unicam.cs.mpmgc.vectorrally.api.controller.match.GameEngine;
import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.BasicGameSetup;
import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.GameSetup;
import it.unicam.cs.mpmgc.vectorrally.api.controller.setup.SetupResult;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.FourNeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.EightNeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.algorithms.NeighborsGenerator;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.RaceTrack;
import it.unicam.cs.mpmgc.vectorrally.api.model.racetrack.TrackComponent;
import it.unicam.cs.mpmgc.vectorrally.api.model.strategies.BotStrategy;

import it.unicam.cs.mpmgc.vectorrally.api.view.SetupGameView;
import it.unicam.cs.mpmgc.vectorrally.api.view.TrackPathController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

/**
 * This class is responsible for handling the settings screen.
 *
 * @version 1.0
 * @since 2024-07-18
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
*/


public class SettingsHandler implements SetupGameView {

    private final TrackPathController IOController;
    private final GameEngine gameEngine;
    private final GameSetup setup;
    private RaceTrack raceTrack;

    @FXML
    private Spinner<Integer> numHumanPlayers;

    @FXML
    private SplitMenuButton trackMenuButton;

    @FXML
    private SplitMenuButton difficultyMenuButton;

    @FXML
    private SplitMenuButton shiftRuleMenuButton;

    @FXML
    private Button setupButton;

    public SettingsHandler() {
        this.IOController = new TrackPathController();
        RaceHandler raceHandler = new RaceHandler();
        this.gameEngine = new BasicGameEngine(raceHandler, null, this);
        this.setup = new BasicGameSetup(this);
    }

    @FXML
    public void initialize() {
        setupButton.setDisable(true);
        numHumanPlayers.setDisable(true);
        loadTrackNames();
        loadDifficultyMenu();
        loadShiftRuleMenu();
    }


    public void loadTrackNames() {
        List<String> trackNames = IOController.findTrack();
        for (String track : trackNames) {
            MenuItem menuItem = new MenuItem(track);
            menuItem.setOnAction(event -> {
                trackMenuButton.setText(track);
                numHumanPlayers.setDisable(false);
                try {
                    setupPlayerSpinner();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                checkReadyStatus();
            });
            trackMenuButton.getItems().add(menuItem);
        }
    }

    private void setupPlayerSpinner() throws Exception {
        raceTrack = setup.initializeTrack();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, raceTrack.getPositionsOfComponent(TrackComponent.START_POSITION).size() , 0);
        numHumanPlayers.setValueFactory(valueFactory);
    }

    private void loadDifficultyMenu() {
        for (BotStrategy strategy : BotStrategy.values()) {
            MenuItem menuItem = new MenuItem(strategy.name());
            menuItem.setOnAction(event -> {
                difficultyMenuButton.setText(strategy.name());
                checkReadyStatus();
            });
            difficultyMenuButton.getItems().add(menuItem);
        }
    }

    private void loadShiftRuleMenu() {
        List<Class<?>> shiftRules = List.of(FourNeighborsGenerator.class, EightNeighborsGenerator.class);
        for (Class<?> shiftRuleClass : shiftRules) {
            String className = shiftRuleClass.getSimpleName();
            String menuText = className.replace("NeighborsGenerator", " neighbors");
            MenuItem menuItem = new MenuItem(menuText);
            menuItem.setOnAction(event -> {
                shiftRuleMenuButton.setText(menuText);

                checkReadyStatus();
            });
            shiftRuleMenuButton.getItems().add(menuItem);
        }
    }

    private void checkReadyStatus() {
        boolean isReady = !trackMenuButton.getText().equals("Racetracks") &&
                !difficultyMenuButton.getText().equals("Bots difficulty") &&
                !shiftRuleMenuButton.getText().equals("Shift rule") &&
                numHumanPlayers.getValue() != null &&
                numHumanPlayers.getValue() >= 0;
        setupButton.setDisable(!isReady);
    }

/*
    @FXML
    private void handleSetupButtonAction(ActionEvent event) throws IOException {
        // not so useful given that we have the methods: getChosenTrack, getNumHumanPlayers, chooseStrategyDifficulty, getShiftAlgorithm
        String selectedTrack = trackMenuButton.getText();
        String selectedDifficulty = difficultyMenuButton.getText();
        String selectedShiftRule = shiftRuleMenuButton.getText();

        // here we have to use SceneManager instead of the following code:
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/race.fxml"));
        Parent raceRoot = loader.load();

        RaceHandler raceHandler = loader.getController();

        // I do not know if this is necessary, we have to remember that we have the SetupGameView methods to initialize things
        raceHandler.setTrack(selectedTrack);
        raceHandler.setDifficulty(selectedDifficulty);
        raceHandler.setShiftRule(selectedShiftRule);

        // always SceneManger instead of this
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(raceRoot));
        stage.show();
    }
*/

    @FXML
    private void handleSetupButtonAction() {
        try {
            SetupResult setupResult = gameEngine.setupMatch();
            SceneManager.getInstance().switchToScene("/race.fxml", controller -> {
                if (controller instanceof RaceHandler raceHandler) {
                    try {
                        raceHandler.initializeRace(setupResult);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getChosenTrack() {
        return TrackPathController.checkRootPath() + "/" + trackMenuButton.getText();
    }

    @Override
    public int getNumHumanPlayers(int maxPlayers) {
        return numHumanPlayers.getValue();
    }

    @Override
    public BotStrategy chooseStrategyDifficulty() {
        String selectedDifficulty = difficultyMenuButton.getText();
        return BotStrategy.valueOf(selectedDifficulty);
    }

    @Override
    public NeighborsGenerator getShiftAlgorithm() {
        String selectedShiftRule = shiftRuleMenuButton.getText();
        if (selectedShiftRule.contains("Four")) return new FourNeighborsGenerator();
        else return new EightNeighborsGenerator();
    }
}
