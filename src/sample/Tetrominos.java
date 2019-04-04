package sample;

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

    public ArrayList<int[][]> setTetrominos() {
        ArrayList<int[][]> arrayList = new ArrayList<>();
        arrayList.add(TShape);
        arrayList.add(JShape);
        arrayList.add(LShape);
        arrayList.add(SShape);
        arrayList.add(ZShape);
        arrayList.add(SquareShape);
        return arrayList;
    }
}
