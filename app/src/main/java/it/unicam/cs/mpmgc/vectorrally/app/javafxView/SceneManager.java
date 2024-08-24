package it.unicam.cs.mpmgc.vectorrally.app.javafxView;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class SceneManager {
    private static SceneManager instance;
    private Stage primaryStage;

    private SceneManager() {
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public void switchToScene(String fxmlPath) {
        switchToScene(fxmlPath, null, null);
    }

    public void switchToScene(String fxmlPath, Consumer<Object> controllerConsumer) {
        switchToScene(fxmlPath, null, controllerConsumer);
    }

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

