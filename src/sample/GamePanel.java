package sample;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;

public class GamePanel extends Pane {

    private Tetrominos tetromino = new Tetrominos();
    private Timeline runGame;
    private final int AMOUNT_OF_BLOCKS = 14;
    private int[][] glass = new int[AMOUNT_OF_BLOCKS + 1][9];
    private ArrayList<int[][]> tetrominos = new Tetrominos().setTetrominos();

    GamePanel() {
        setFocusTraversable(true);
        Arrays.fill(glass[AMOUNT_OF_BLOCKS], 1);
    }

    public void run() {
        tetromino.initializeShape(tetrominos);
        tetromino.paint(this);
        runGame = new Timeline(new KeyFrame(Duration.millis(200), event -> {
            if (tetromino.isTouchFloor(glass)) {
                tetromino.leaveOnTheFloor(glass);
                tetromino = new Tetrominos();
                tetromino.initializeShape(tetrominos);
                tetromino.paint(this);
            }
            tetromino.stepDown();
        }));
        runGame.setCycleCount(Timeline.INDEFINITE);
        runGame.play();
    }

    void listener(Pane pane) {
        pane.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    break;
                case DOWN:
                    break;
                case LEFT:
                    tetromino.stepLeft(glass, event.getCode());
                    break;
                case RIGHT:
                    tetromino.stepRight(glass, event.getCode());
                    break;
            }
        });
    }

    void pause() {
        runGame.stop();
    }
}
