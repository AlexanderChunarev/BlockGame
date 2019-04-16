package sample.screens;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import sample.tetris.GamePanel;


import java.io.File;


public class GameGrid extends BorderPane implements InitializeScene {
    private Button back = new Button("Back");
    private Button play = new Button("Play");
    private Button pause = new Button("Pause");
    private GamePanel gamePanel = new GamePanel();
    private VBox paneMenu = new VBox();

    public GameGrid() {
        fillPanel();
        listener();
    }

    @Override
    public void fillPanel() {
        File file = new File("dark-grey-background-texture.jpg");
        Image img = new Image(file.getAbsoluteFile().toURI().toString());
        BackgroundImage bgImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        setBackground(new Background(bgImg));
        setPadding(new Insets(16));
        createGamePanel();
        createMenuPanel();
    }


    private void createMenuPanel() {
        paneMenu.setPadding(new Insets(0));
        paneMenu.getChildren().addAll(back, play, pause);
        setRight(paneMenu);
    }

    private void createGamePanel() {
        gamePanel.setPrefSize(298, 420);
        gamePanel.setStyle("-fx-background-color: rgba(0, 100, 100, 0.3); -fx-background-radius: 5;");
        gamePanel.listener(this);
        setLeft(gamePanel);
    }

    @Override
    public void listener() {
        back.setOnMouseClicked(event -> SceneLibrary.switchMenu());
        play.setOnMouseClicked(event -> gamePanel.run());
        pause.setOnMouseClicked(event -> gamePanel.pause());
    }

    @Override
    public void setProperties() {

    }
}
