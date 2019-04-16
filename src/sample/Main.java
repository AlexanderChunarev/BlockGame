package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.screens.MenuWindow;
import sample.screens.SceneLibrary;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Block game");
        primaryStage.setScene(new Scene(new MenuWindow(), 390, 450));
        primaryStage.setResizable(false);
        SceneLibrary.setPrimaryScene(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
