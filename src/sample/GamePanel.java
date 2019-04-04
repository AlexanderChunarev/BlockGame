package sample;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;

public class GamePanel extends Pane {

    private Tetrominos shape = new Tetrominos();
    private Canvas canvas = new Canvas(270, 420);
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private Timeline runGame;
    private final int AMOUNT_OF_BLOCKS = (int) (canvas.getHeight() / 30);
    private int[][] glass = new int[AMOUNT_OF_BLOCKS + 1][10];
    private ArrayList<int[][]> tetrominos = new Tetrominos().setTetrominos();

    GamePanel() {
        Arrays.fill(glass[AMOUNT_OF_BLOCKS], 1);
        getChildren().add(canvas);
    }

    void runGame() {
        shape.initializeShape(tetrominos);
        runGame = new Timeline(new KeyFrame(Duration.millis(500), event -> {
            repaint(gc);
            paintComponent();
            if (shape.isOnTheFloor(glass)) {
                shape.leaveOnTheFloor(glass);
                shape = new Tetrominos();
                shape.initializeShape(tetrominos);
            }
            shape.paintFigure(gc);
            shape.paintFigure(gc);
            shape.stepDown();
        }));
        runGame.setCycleCount(Timeline.INDEFINITE);
        runGame.play();
    }

    private void paintComponent() {
        for (int x = 0; x < glass.length - 1; x++) {
            for (int y = 0; y < glass[0].length; y++) {
                if (glass[x][y] == 1) {
                    gc.fillRect(y * 30, x * 30, 28, 28);
                }
            }
        }
        shape.paintFigure(gc);
    }

    private void repaint(GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    void pause() {
        runGame.stop();
    }
}
