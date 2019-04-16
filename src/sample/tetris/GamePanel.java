package sample.tetris;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
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
    private int[][] glass = new int[AMOUNT_OF_BLOCKS + 1][10];

    public GamePanel() {
        setFocusTraversable(true);
        Arrays.fill(glass[AMOUNT_OF_BLOCKS], 1);
        for (int i = 0; i < glass.length; i++) {
            for (int j = 0; j < glass[0].length; j++) {
                System.out.print(glass[i][j]);
            }
            System.out.println();
        }
    }

    public void run() {
        getRandomTetromino();
        tetromino.initializeShape(currentTetromino);
        tetromino.paint(this, currentBlockImage);
        runGame = new Timeline(new KeyFrame(Duration.millis(200), event -> {
            if (tetromino.isTouchFloor(glass)) {
                tetromino.leaveOnTheFloor(glass);
                tetromino = new Tetrominos();
                getRandomTetromino();
                tetromino.initializeShape(currentTetromino);
                tetromino.paint(this, currentBlockImage);
            }
            if (MatrixOperations.isRowFilled(glass, 10)) {
                MatrixOperations.isFilledRow(glass, 10);
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
        tetromino.paint(this, currentBlockImage);
    }

    private void getRandomTetromino() {
        Random random = new Random();
        int randTetromino = random.nextInt(tetrominos.size());
        currentTetromino = tetrominos.get(randTetromino);
        currentBlockImage = blockImages.get(randTetromino);
    }

    private void setBlockData(Rectangle rectangle) {
        Image image = new Image("file:images/greenBlock.png");
        rectangle.setArcHeight(5);
        rectangle.setArcWidth(5);
        rectangle.setFill(new ImagePattern(image));
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
            if (event.getCode().equals(KeyCode.valueOf(prop.getProperty("upKey")))) {
                currentTetromino = MatrixOperations.rotate(currentTetromino);
                tetromino.rotate(currentTetromino);
                repaint();
                paintComponent();

            } else if (event.getCode().equals(KeyCode.valueOf(prop.getProperty("leftKey")))) {
                tetromino.stepLeft(glass, event.getCode());

            } else if (event.getCode().equals(KeyCode.valueOf(prop.getProperty("rightKey")))) {
                tetromino.stepRight(glass, event.getCode());
            }
        });
}

    public void pause() {
        runGame.stop();
    }
}
