package sample;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Random;

public class Shape {
    private int x = 3, y = 0;
    private ArrayList<Block> figure = new ArrayList<>();


    private int[][] getRandomElement(ArrayList<int[][]> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    void stepDown() {
        for (Block block : figure) {
            block.setY(block.getY() + 1);
            y++;
        }
    }

    void initializeShape(ArrayList<int[][]> tetrominos) {
        int[][] currentTetromino = getRandomElement(tetrominos);
        for (int x = 0; x < currentTetromino.length; x++) {
            for (int y = 0; y < currentTetromino[0].length; y++) {
                if (currentTetromino[x][y] == 1) {
                    figure.add(new Block(y + this.x, x + this.y));
                }
            }
        }
    }

    boolean isOnTheFloor(int[][] glass) {
        for (Block block : figure) {
            if (glass[block.getY() + 1][block.getX()] > 0) return true;
        }
        return false;
    }

    void leaveOnTheFloor(int[][] glass) {
        for (Block block : figure) {
            System.out.println(block.getY() + "\t" + block.getX());
            glass[block.getY()][block.getX()] = 1;
        }
    }

    void paintFigure(GraphicsContext graphicsContext) {
        for (Block block : figure) {
            block.paint(graphicsContext);
        }
    }

}
