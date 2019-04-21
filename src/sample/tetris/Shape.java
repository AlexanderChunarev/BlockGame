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
            rectangle.setY(rectangle.getY() + BLOCK_SIZE);
        }
    }

    void stepLeft() {
        for (Rectangle rectangle : figure) {
            rectangle.setX(rectangle.getX() - BLOCK_SIZE);
        }

    }

    void stepRight() {
        for (Rectangle rectangle : figure) {
            rectangle.setX(rectangle.getX() + BLOCK_SIZE);
        }
    }

    void drop() {
        stepDown();
    }

    void getRotatedTetromino(int[][] currentTetromino) {
        if (currentTetromino.length == 3) {
            this.x = (int) figure.get(0).getX() - BLOCK_SIZE;
        } else {
            this.x = (int) figure.get(0).getX();
        }
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

    boolean isTouchWall(Rectangle[][] glass, String keyName) {
        for (Rectangle rectangle : figure) {
            if (keyName.equals("LEFT") && (rectangle.getX() == 0
                    || glass[(int) (rectangle.getY() / BLOCK_SIZE)][(int) (rectangle.getX() / BLOCK_SIZE) - 1] != null)) {
                return true;
            }
            if (keyName.equals("RIGHT") && (rectangle.getX() == 270
                    || glass[(int) (rectangle.getY() / BLOCK_SIZE)][(int) (rectangle.getX() / BLOCK_SIZE) + 1] != null)) {
                return true;
            }

        }
        return false;
    }

    boolean isTouchFloor(Rectangle[][] glass) {
        for (Rectangle rectangle : figure) {
            if (rectangle.getY() / BLOCK_SIZE == glass.length - 1
                    || glass[(int) rectangle.getY() / BLOCK_SIZE][(int) rectangle.getX() / BLOCK_SIZE] != null) {
                return true;
            }
        }
        return false;
    }

    void leaveOnTheFloor(Rectangle[][] glass) {
        for (Rectangle rectangle : figure) {
            glass[(int) rectangle.getY() / BLOCK_SIZE - 1][(int) rectangle.getX() / BLOCK_SIZE] = rectangle;
        }
    }

    void paint(Pane pane, Image blockImage) {
        for (Rectangle rectangle : figure) {
            rectangle.setFill(new ImagePattern(blockImage));
            rectangle.setArcHeight(5);
            rectangle.setArcWidth(5);
            pane.getChildren().add(rectangle);
        }
    }
}
