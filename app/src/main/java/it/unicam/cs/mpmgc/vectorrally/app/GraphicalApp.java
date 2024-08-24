package it.unicam.cs.mpmgc.vectorrally.app;

import it.unicam.cs.mpmgc.vectorrally.app.javafxView.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

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

 /*   @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/menu.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Vector Rally");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
*/
    @Override
    public void start(Stage primaryStage) throws IOException {
        SceneManager.getInstance().setPrimaryStage(primaryStage);
        SceneManager.getInstance().switchToScene("/menu.fxml");
        primaryStage.setTitle("Vector Rally");
    }
}
