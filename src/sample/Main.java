package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new MenuWindow();
        primaryStage.setTitle("Block game");
        primaryStage.setScene(new Scene(root, 400, 450));
        primaryStage.setResizable(false);
        SceneLibrary.setPrimaryScene(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
