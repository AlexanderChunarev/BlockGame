package sample.tetris;

import javafx.scene.shape.Rectangle;

public class Glass {
    private final int AMOUNT_OF_BLOCKS = 14;
    private Rectangle[][] glass = new Rectangle[AMOUNT_OF_BLOCKS][10];

    boolean isTouchGround() {
        for (int i = 0; i < glass.length; i++) {
            for (int j = 0; j < glass[i].length; j++) {
                if (i == glass.length - 1) {
                    return true;
                }
                if (glass[i][j] != null) {
                    return true;
                }
            }
        }
        return false;
    }

    void leaveOnTheFloor(int[][] currentFigure) {
        for (int i = 0; i < currentFigure.length; i++) {
            for (int j = 0; j < currentFigure[i].length; j++) {

            }
        }
    }
}
