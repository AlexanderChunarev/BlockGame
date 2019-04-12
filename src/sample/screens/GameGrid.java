package sample.screens;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import sample.tetris.GamePanel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


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
        setStyle("-fx-background-color: #383838;");
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
        //gamePanel.setPadding(new Insets(20));
        gamePanel.setPrefSize(270, 420);
        gamePanel.setStyle("-fx-border-color: black");
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

    private void controll() {
        try {
            Scanner scanner = new Scanner(new File("UserSettings.txt"));
            Map<String, String> map = new HashMap<>();
            map.put("left", scanner.next());
            map.put("right", scanner.next());
            map.put("down", scanner.next());
            this.setOnKeyReleased(event -> {
                if (event.getCode().equals(KeyCode.valueOf(map.get("left")))) {
                    System.out.println("Pressed left key is: " + event.getCode());
                } else if (event.getCode().equals(KeyCode.valueOf(map.get("right")))) {
                    System.out.println("Pressed right key is: " + event.getCode());
                } else if (event.getCode().equals(KeyCode.valueOf(map.get("down")))) {
                    System.out.println("Pressed down key is: " + event.getCode());
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
