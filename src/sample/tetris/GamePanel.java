package sample.tetris;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import sample.matrix.MatrixOperations;

import java.util.ArrayList;
import java.util.Arrays;

public class GamePanel extends Pane {

    private Tetrominos tetromino = new Tetrominos();
    private Timeline runGame;
    private final int AMOUNT_OF_BLOCKS = 14;
    private int[][] glass = new int[AMOUNT_OF_BLOCKS + 1][9];
    private ArrayList<int[][]> tetrominos = new Tetrominos().setTetrominos();
    private int[][] currentTetromino = tetromino.getRandomElement(tetrominos);

    public GamePanel() {
        setFocusTraversable(true);
        Arrays.fill(glass[AMOUNT_OF_BLOCKS], 1);
    }

    public void run() {
        tetromino.initializeShape(currentTetromino);
        tetromino.paint(this);
        runGame = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (tetromino.isTouchFloor(glass)) {
                tetromino.leaveOnTheFloor(glass);
                tetromino = new Tetrominos();
                currentTetromino = tetromino.getRandomElement(tetrominos);
                tetromino.initializeShape(currentTetromino);
                tetromino.paint(this);
            }
            if (MatrixOperations.isRowFilled(glass, 9)) {
                MatrixOperations.isFilledRow(glass, 9);
                repaint();
                paintComponent();
            }

            tetromino.stepDown();
        }));
        runGame.setCycleCount(Timeline.INDEFINITE);
        runGame.play();
    }

    private void paintComponent() {
        for (int x = 0; x < glass.length - 1; x++) {
            for (int y = 0; y < glass[0].length; y++) {
                if (glass[x][y] == 1) {
                    Rectangle rectangle = new Rectangle(30 * y, 30 * x, 28, 28);
                    setBlockData(rectangle);
                    this.getChildren().add(rectangle);
                }
            }
        }
        tetromino.paint(this);
    }

    private void setBlockData(Rectangle rectangle) {
        Image image = new Image("file:images/greenBlock.png");
        rectangle.setFill(new ImagePattern(image));
    }

    private void repaint() {
        this.getChildren().clear();
    }

    public void listener(Pane pane) {
        pane.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    currentTetromino = MatrixOperations.rotate(currentTetromino);
                    tetromino.rotate(currentTetromino);
                    repaint();
                    paintComponent();
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

    public void pause() {
        runGame.stop();
    }
}
