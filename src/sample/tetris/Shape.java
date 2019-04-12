package sample.tetris;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Shape {
    private int x = 90, y = 0;
    private ArrayList<Rectangle> figure = new ArrayList<>();
    private final int BLOCK_SIZE = 30;

    void stepDown() {
        for (Rectangle rectangle : figure) {
            rectangle.setY(rectangle.getY() + BLOCK_SIZE / 2.0);
        }
    }

    void stepLeft(int[][] glass, KeyCode keyCode) {
        if (!isTouchWall(glass, keyCode)) {
            for (Rectangle rectangle : figure) {
                rectangle.setX(rectangle.getX() - BLOCK_SIZE);
            }
        }
    }

    void stepRight(int[][] glass, KeyCode keyCode) {
        if (!isTouchWall(glass, keyCode)) {
            for (Rectangle rectangle : figure) {
                rectangle.setX(rectangle.getX() + BLOCK_SIZE);
            }
        }
    }

    void rotate(int[][] currentTetromino) {
        this.x = (int) figure.get(0).getX();
        this.y = (int) figure.get(0).getY();
        figure.clear();
        initializeShape(currentTetromino);
    }

    void initializeShape(int[][] currentTetromino) {
        for (int x = 0; x < currentTetromino.length; x++) {
            for (int y = 0; y < currentTetromino[0].length; y++) {
                if (currentTetromino[x][y] == 1) {
                    figure.add(new Rectangle(this.x + 30 * y, this.y + 30 * x, 28, 28));
                }
            }
        }
    }

    private boolean isTouchWall(int[][] glass, KeyCode keyCode) {
        for (Rectangle rectangle : figure) {
            if (keyCode.equals(KeyCode.LEFT) && (rectangle.getX() == 0
                    || glass[(int) (rectangle.getY() / BLOCK_SIZE)][(int) (rectangle.getX() / BLOCK_SIZE) - 1] == 1)) {
                return true;
            }
            if (keyCode.equals(KeyCode.RIGHT) && (rectangle.getX() == 240
                    || glass[(int) (rectangle.getY() / BLOCK_SIZE)][(int) (rectangle.getX() / BLOCK_SIZE) + 1] == 1)) {
                return true;
            }

        }
        return false;
    }

    boolean isTouchFloor(int[][] glass) {
        for (Rectangle rectangle : figure) {
            if (glass[(int) rectangle.getY() / BLOCK_SIZE + 1][(int) rectangle.getX() / BLOCK_SIZE] > 0) {
                return true;
            }
        }
        return false;
    }

    void leaveOnTheFloor(int[][] glass) {
        for (Rectangle rectangle : figure) {
            glass[(int) rectangle.getY() / BLOCK_SIZE][(int) rectangle.getX() / BLOCK_SIZE] = 1;
        }
    }

    void paint(Pane pane) {
        for (Rectangle rectangle : figure) {
            Image image = new Image("file:images/greenBlock.png");
            rectangle.setFill(new ImagePattern(image));
            pane.getChildren().add(rectangle);
        }
    }
}
