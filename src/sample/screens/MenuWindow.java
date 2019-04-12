package sample.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuWindow extends VBox implements InitializeScene {

    private Button[] buttons = new Button[]{
            new Button("Play"),
            new Button("Settings"),
            new Button("Help"),
            new Button("Exit")};

    public MenuWindow() {
        fillPanel();
        listener();
    }

    @Override
    public void fillPanel() {
        setProperties();
        getChildren().addAll(buttons);
    }

    @Override
    public void setProperties() {
        setAlignment(Pos.BASELINE_CENTER);
        setStyle("-fx-background-color: #383838;");
        setPadding(new Insets(30, 0, 30, 0));
        setSpacing(10);
        for (Button button : buttons) {
            button.setFont(loadFont());
            button.setTextFill(Color.LIGHTGRAY);
            button.setStyle("-fx-background-color: transparent;");
        }

    }

    @Override
    public void listener() {
        buttons[0].setOnAction(event -> SceneLibrary.switchPlay());
        buttons[1].setOnAction(event -> SceneLibrary.switchSettings());
        buttons[3].setOnAction(event -> {
            Stage stage = (Stage) getScene().getWindow();
            stage.close();
        });
    }

    private Font loadFont() {
        Font font = null;
        try {
            font = Font.loadFont(new FileInputStream(new File("prstartk.ttf")), 30);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return font;
    }
}