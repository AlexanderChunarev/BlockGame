package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.*;
import java.util.Properties;

public class Settings extends GridPane implements InitializeScene {

    private Button backToMEnu = new Button("Back");
    private Label[] labels = new Label[]{
            new Label("Left:"),
            new Label("Right:"),
            new Label("Down:")};
    private TextField left = new TextField();
    private TextField right = new TextField();
    private TextField down = new TextField();
    private TextField[] textFields = new TextField[]{
            left,
            right,
            down};

    public Settings() {
        fillPanel();
        loadSettings();
        listener();
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
        for (int i = 0; i < textFields.length; i++) {
            int index = i;
            textFields[i].setOnKeyReleased(event -> {
                textFields[index].setText(String.valueOf(event.getCode()));
                setUserSettings();
            });
        }
    }

    private void setUserSettings() {
        try {
            Properties props = new Properties();
            File f = new File("output");
            OutputStream out = new FileOutputStream(f);
            for (int i = 0; i < textFields.length; i++) {
                if (i == 0) {
                    props.setProperty("leftKey", textFields[i].getText());
                } else if (i == 1) {
                    props.setProperty("rightKey", textFields[i].getText());
                } else if (i == 2) {
                    props.setProperty("downKey", textFields[i].getText());
                }
            }
            props.store(out, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSettings() {
        Properties prop = new Properties();
        InputStream input;
        try {
            input = new FileInputStream("output");
            prop.load(input);
            textFields[0].setText(prop.getProperty("leftKey"));
            textFields[1].setText(prop.getProperty("rightKey"));
            textFields[2].setText(prop.getProperty("downKey"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}