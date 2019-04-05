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
    private int[][] glass = new int[AMOUNT_OF_BLOCKS + 1][10];
    private ArrayList<int[][]> tetrominos = new Tetrominos().setTetrominos();

    GamePanel() {
        Arrays.fill(glass[AMOUNT_OF_BLOCKS], 1);
        setFocusTraversable(true);
    }

    public void run() {
        tetromino.initializeShape(tetrominos);
        tetromino.paint(this);
        runGame = new Timeline(new KeyFrame(Duration.millis(300), event -> {
            tetromino.stepDown();
            if (tetromino.isOnTheFloor(glass)) {
                tetromino.leaveOnTheFloor(glass);
                tetromino = new Tetrominos();
                tetromino.initializeShape(tetrominos);
                tetromino.paint(this);
            }
        }));
        runGame.setCycleCount(Timeline.INDEFINITE);
        runGame.play();
    }

    void listener(Pane pane) {
        pane.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    System.out.println(true);
                    break;
                case DOWN:
                    System.out.println();
                    break;
                case LEFT:
                    tetromino.stepLeft();
                    break;
                case RIGHT:
                    System.out.println(true);
                    tetromino.stepRight();
                    break;
                case SHIFT:
                    System.out.println(true);
                    break;
            }
        });
    }

    void pause() {
        runGame.stop();
    }
}
