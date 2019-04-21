package sample.tetris;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import sample.matrix.MatrixOperations;

import java.io.*;
import java.util.*;

public class GamePanel extends Pane {
    private final int AMOUNT_OF_BLOCKS = 14;
    private Tetrominos tetromino = new Tetrominos();
    private Timeline runGame;
    private int[][] currentTetromino;
    private Image currentBlockImage;
    private ArrayList<int[][]> tetrominos = new Tetrominos().setTetrominos();
    private ArrayList<Image> blockImages = new Tetrominos().setBlockImages();
    private Rectangle[][] glass = new Rectangle[AMOUNT_OF_BLOCKS][10];

    public GamePanel() {
        setFocusTraversable(true);
    }

    public void run() {
        prepareTetromino();
        runGame = new Timeline(new KeyFrame(Duration.millis(400), event -> {
            if (tetromino.isTouchFloor(glass)) {
                tetromino.leaveOnTheFloor(glass);
                tetromino = new Tetrominos();
                prepareTetromino();
            }
            if (isFilled()) {
                removeFilledRow();
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
                if (glass[x][y] != null) {
                    this.getChildren().add(glass[x][y]);
                }
            }
        }
        tetromino.paint(this, currentBlockImage);
    }

    private void getRandomTetromino() {
        Random random = new Random();
        int randTetromino = random.nextInt(tetrominos.size());
        currentTetromino = tetrominos.get(randTetromino);
        currentBlockImage = blockImages.get(randTetromino);
    }

    private void prepareTetromino() {
        getRandomTetromino();
        tetromino.initializeShape(currentTetromino);
        tetromino.paint(this, currentBlockImage);
    }

    private boolean isFilled() {
        //int count;
        for (int i = 0; i < glass.length; i++) {
            int count = MatrixOperations.getCount(glass, i);
            if (count == glass[0].length) {
                return true;
            }
        }
        return false;
    }

    private void removeFilledRow() {
        for (int i = 0; i < glass.length; i++) {
            int count = MatrixOperations.getCount(glass, i);
            if (count == glass[0].length) {
                moveDown(i);
            }
        }
    }

    private void moveDown(int pos) {
        for (int i = pos; i > 0; i--) {
            for (int j = 0; j < glass[0].length; j++) {
                glass[i][j] = glass[i - 1][j];
                if (glass[i][j] != null) {
                    glass[i][j].setY(glass[i][j].getY() + 30);
                }

            }
        }
    }

    private void repaint() {
        this.getChildren().clear();
    }

    public void listener(Pane pane) {
        Properties prop = new Properties();
        try {
            InputStream input = new FileInputStream("UserSettings");
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pane.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.valueOf(prop.getProperty("rotateKey")))) {
                currentTetromino = MatrixOperations.rotate(currentTetromino);
                tetromino.getRotatedTetromino(currentTetromino);
                repaint();
                paintComponent();

            }
            if (event.getCode().equals(KeyCode.valueOf(prop.getProperty("leftKey")))) {
                if (!tetromino.isTouchWall(glass, "LEFT")) {
                    tetromino.stepLeft();
                }

            }
            if (event.getCode().equals(KeyCode.valueOf(prop.getProperty("rightKey")))) {
                if (!tetromino.isTouchWall(glass, "RIGHT")) {
                    tetromino.stepRight();
                }
            }
            if (event.getCode().equals(KeyCode.valueOf(prop.getProperty("dropKey")))) {
                while (!tetromino.isTouchFloor(glass)) {
                    tetromino.drop();
                }
            }
        });
    }

    public void pause() {
        //runGame.stop();
    }
}
