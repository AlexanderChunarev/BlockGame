package sample;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Shape {
    private int x = 90, y = 0;
    private ArrayList<Rectangle> figure = new ArrayList<>();
    private final int BLOCK_SIZE = 30;

    private int[][] getRandomElement(ArrayList<int[][]> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    void stepDown() {
        for (Rectangle rectangle : figure) {
            rectangle.setY(rectangle.getY() + BLOCK_SIZE);
        }
    }

    void stepLeft() {
        for (Rectangle rectangle : figure) {
            rectangle.setX(rectangle.getX() - BLOCK_SIZE);
        }
        System.out.println();
    }

    void stepRight() {
        for (Rectangle rectangle : figure) {
            rectangle.setX(rectangle.getX() + BLOCK_SIZE);
        }
    }

    void initializeShape(ArrayList<int[][]> tetrominos) {
        int[][] currentTetromino = getRandomElement(tetrominos);
        for (int x = 0; x < currentTetromino.length; x++) {
            for (int y = 0; y < currentTetromino[0].length; y++) {
                if (currentTetromino[x][y] == 1) {
                    figure.add(new Rectangle(this.x + 30 * y, this.y + 30 * x, 28, 28));
                }
            }
        }
    }

    boolean isOnTheFloor(int[][] glass) {
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
            pane.getChildren().add(rectangle);
        }
    }
}
