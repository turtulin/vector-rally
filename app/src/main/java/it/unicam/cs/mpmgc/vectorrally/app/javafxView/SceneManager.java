package it.unicam.cs.mpmgc.vectorrally.app.javafxView;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * This class is responsible for handling the switching of scenes.
 *
 * @version 1.0
 * @since 2024-07-17
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class SceneManager {
    private static SceneManager instance;
    private Stage primaryStage;

    /**
     * Private constructor to prevent instantiation.
     */
    private SceneManager() {
    }

    /**
     * Returns the instance of the SceneManager.
     * @return the instance of the {@link SceneManager}
     */
    public static SceneManager getInstance() {
        if (instance == null) instance = new SceneManager();
        return instance;
    }

    /**
     * Sets the primary stage.
     * @param stage the primary {@link Stage}
     */
    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    /**
     * Switches to the scene specified by the fxmlPath.
     * @param fxmlPath the path to the fxml file
     */
    public void switchToScene(String fxmlPath) {
        switchToScene(fxmlPath, null, null);
    }

    /**
     * Switches to the scene specified by the fxmlPath.
     * @param fxmlPath the path to the fxml file
     * @param controllerConsumer the controller consumer
     */
    public void switchToScene(String fxmlPath, Consumer<Object> controllerConsumer) {
        switchToScene(fxmlPath, null, controllerConsumer);
    }

    /**
     * Switches to the scene specified by the fxmlPath.
     * @param fxmlPath the path to the fxml file
     * @param controllerFactory the controller factory
     * @param controllerConsumer the controller consumer
     */
    public void switchToScene(String fxmlPath, Function<Class<?>, Object> controllerFactory, Consumer<Object> controllerConsumer) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            if (controllerFactory != null) loader.setControllerFactory(controllerFactory::apply);
            Parent root = loader.load();
            if (controllerConsumer != null) controllerConsumer.accept(loader.getController());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

