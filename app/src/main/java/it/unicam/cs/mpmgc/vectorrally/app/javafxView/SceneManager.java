package it.unicam.cs.mpmgc.vectorrally.app.javafxView;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class SceneManager {
    private static SceneManager instance;
    private Stage primaryStage;
    private final Map<String, Scene> sceneCache = new HashMap<>();

    private SceneManager() {
        // Private constructor to prevent instantiation
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

    public void switchToScene(String fxmlPath, Consumer<Object> controllerConsumer) {
        try {
            Scene scene = sceneCache.get(fxmlPath);
            if (scene == null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                Parent root = loader.load();
                if (controllerConsumer != null) {
                    controllerConsumer.accept(loader.getController());
                }
                scene = new Scene(root);
                sceneCache.put(fxmlPath, scene);
            }
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToScene(String fxmlPath) {
        switchToScene(fxmlPath, null);
    }

    public void clearCache() {
        sceneCache.clear();
    }

    public void preloadScene(String fxmlPath) {
        if (!sceneCache.containsKey(fxmlPath)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                sceneCache.put(fxmlPath, scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

