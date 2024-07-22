package it.unicam.cs.mpmgc.vectorrally.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * This class is responsible for starting the graphical application.
 *
 * @version 1.0
 * @since 2024-07-17
 * @author Marta Musso
 * <a href="mailto:marta.musso@studenti.unicam.it">marta.musso@studenti.unicam.it</a>
 */
public class GraphicalApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/menu.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Vector Rally");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
