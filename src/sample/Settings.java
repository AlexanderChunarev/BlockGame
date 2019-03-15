package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.*;
import java.util.Scanner;

public class Settings extends GridPane implements InitializeScene {

    private Button backToMEnu = new Button("Back");
    private Label[] labels = new Label[]{
            new Label("Left:"),
            new Label("Right:"),
            new Label("Down:")};
    private TextField[] textFields = new TextField[]{
            new TextField(),
            new TextField(),
            new TextField()};


    public Settings() {
        fillPanel();
        listener();
        setUserSettings();
    }

    @Override
    public void fillPanel() {
        setAlignment(Pos.TOP_CENTER);
        setStyle("-fx-background-color: #383838;");
        setVgap(20);
        setHgap(20);
        setProperties();
        for (int i = 0; i < 3; i++) {
            add(labels[i], 0, i + 1);
            add(textFields[i], 1, i + 1);
        }
        add(backToMEnu, 1, 4);
    }

    @Override
    public void setProperties() {
        for (Label label : labels) {
            label.setFont(Font.font("Verdana", 14));
            label.setTextFill(Color.WHITE);
        }
        for (TextField textField : textFields) {
            textField.setAlignment(Pos.CENTER);
            textField.setStyle("-fx-display-caret: false;");
        }
    }

    @Override
    public void listener() {
        backToMEnu.setOnAction(event -> SceneLibrary.switchMenu());
        for (TextField textField : textFields) {
            textField.setOnKeyReleased(event -> {
                String keyName = String.valueOf(event.getCode());
                textField.setText(keyName);
                writeUserSettings(keyName);
            });
        }
    }

    private void setUserSettings() {
        try {
            Scanner scanner = new Scanner(new File("UserSettings.txt"));
            for (TextField textField : textFields) {
                textField.setText(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void writeUserSettings(String keyName) {
        Writer output;
        try {
            output = new BufferedWriter(new FileWriter("UserSettings.txt", true));
            output.append(keyName + "\n");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}