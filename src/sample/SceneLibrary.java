package sample;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneLibrary {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 450;
    private static Stage currentStage;
    private static Scene menu = new Scene(new MenuWindow(), WIDTH, HEIGHT);
    private static Scene settings = new Scene(new Settings(), WIDTH, HEIGHT);
    private static Scene play = new Scene(new GameGrid(), WIDTH, HEIGHT);

    public static void setPrimaryScene(Stage primaryScene) {
        currentStage = primaryScene;
    }

    public static void switchMenu() {
        currentStage.setScene(menu);
    }

    public static void switchSettings() {
        currentStage.setScene(settings);
    }

    public static void switchPlay() {
        currentStage.setScene(play);
    }
}
