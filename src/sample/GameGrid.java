package sample;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class GameGrid extends GridPane implements InitializeScene {
    private static final Border green = new Border(new BorderStroke(Color.GREEN,
            BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(1)));
    private Button back = new Button("Back");
    private Button play = new Button("Play");
    private Button pause = new Button("Pause");
    private Pane pane = new Pane();
    private VBox paneMenu = new VBox();

    public GameGrid() {
        fillPanel();
        listener();
    }

    @Override
    public void fillPanel() {
        setStyle("-fx-background-color: #383838;");
        setPadding(new Insets(16));
        setVgap(15);
        setHgap(20);
        createGamePanel();
        createMenuPanel();
    }


    private void createMenuPanel() {
        paneMenu.setPadding(new Insets(0));
        paneMenu.setAlignment(Pos.TOP_CENTER);
        paneMenu.setPrefSize(100, 300);
        paneMenu.getChildren().add(back);
        paneMenu.getChildren().add(play);
        paneMenu.getChildren().add(pause);
        add(paneMenu, 1, 0);
    }

    private void createGamePanel() {
        pane.setPadding(new Insets(20));
        pane.setPrefSize(250, 400);
        pane.setBorder(green);
        add(pane, 0, 0);
    }

    private void controll() {
        Shape shape = new Shape();
        try {
            Scanner scanner = new Scanner(new File("UserSettings.txt"));
            Map<String, String> map = new HashMap<>();
            map.put("left", scanner.next());
            map.put("right", scanner.next());
            map.put("down", scanner.next());
            this.setOnKeyReleased(event -> {
                if (event.getCode().equals(KeyCode.valueOf(map.get("left")))) {
                    Polygon newShape = shape.createTShape(getTranslateX(), getTranslateY() + 10);
                    pane.getChildren().add(newShape);
                    System.out.println(newShape.getTranslateY());
                    System.out.println(newShape.getTranslateX());
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

    @Override
    public void listener() {
        back.setOnMouseClicked(event -> SceneLibrary.switchMenu());
        play.setOnAction(event -> {

        });
    }

    @Override
    public void setProperties() {

    }
}
