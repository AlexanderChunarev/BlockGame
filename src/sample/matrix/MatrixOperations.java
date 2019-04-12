package sample.matrix;

import java.util.Arrays;

public class MatrixOperations {
    private static int[][] transposeMatrix(int[][] m) {
        int[][] temp = new int[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }

    private static int[][] swap(int[][] arr) {
        int[][] temp = new int[arr.length][arr[0].length];
        int index;
        for (int i = 0; i < arr.length; i++) {
            index = 0;
            for (int j = arr[0].length - 1; j >= 0; j--) {
                temp[i][index] = arr[i][j];
                index++;
            }
        }
        return temp;
    }

    public static int[][] rotate(int[][] matrix) {
        return swap(transposeMatrix(matrix));
    }

    public static boolean isRowFilled(int[][] matrix, int maxBlocksContains) {
        int counter;
        for (int i = 0; i < matrix.length - 1; i++) {
            counter = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    counter++;
                }
            }
            if (counter == maxBlocksContains) {
                return true;
            }
        }
        return false;
    }

    public static void isFilledRow(int[][] matrix, int maxBlocksContains) {
        int counter;
        for (int i = 0; i < matrix.length - 1; i++) {
            counter = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    counter++;
                }
            }
            if (counter == maxBlocksContains) {
                System.out.println(true);
                Arrays.fill(matrix[i], 0);
                moveMatrixDown(matrix, i);
            }
        }
    }

    private static void moveMatrixDown(int[][] matrix ,int position) {
        for (int i = position; i > 0 ; i--) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = matrix[i - 1][j];
            }
        }
    }

}
