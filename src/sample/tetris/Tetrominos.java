package sample.tetris;

import javafx.scene.image.Image;
import java.util.*;

public class Tetrominos extends Shape {
    private int[][] TShape = {{0, 1, 0},
            {1, 1, 1}};
    private int[][] JShape = {{1, 0, 0},
            {1, 1, 1}};
    private int[][] LShape = {{1, 1, 1},
            {1, 0, 0}};
    private int[][] SShape = {{0, 1, 1},
            {1, 1, 0}};
    private int[][] ZShape = {{1, 1, 0},
            {0, 1, 1}};
    private int[][] SquareShape = {{1, 1},
            {1, 1}};
    private int[][] LineShape = {{1, 1, 1, 1}};

    ArrayList<int[][]> setTetrominos() {
        ArrayList<int[][]> arrayList = new ArrayList<>();
        arrayList.add(TShape);
        arrayList.add(JShape);
        arrayList.add(LShape);
        arrayList.add(SShape);
        arrayList.add(ZShape);
        arrayList.add(SquareShape);
        arrayList.add(LineShape);
        return arrayList;
    }

    ArrayList<Image> setBlockImages() {
        ArrayList<Image> arrayList = new ArrayList<>();
        arrayList.add(new Image("file:images/greenBlock.png"));
        arrayList.add(new Image("file:images/greenBlock.png"));
        arrayList.add(new Image("file:images/blueBlock.png"));
        arrayList.add(new Image("file:images/fioletBlock.png"));
        arrayList.add(new Image("file:images/orangeBlock.png"));
        arrayList.add(new Image("file:images/redBlock.png"));
        arrayList.add(new Image("file:images/redBlock.png"));
        return arrayList;
    }
}
