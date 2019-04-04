package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Block {
    private int x, y;

    Block(int x, int y) {
        setX(x);
        setY(y);
    }

    int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }

    void paint(GraphicsContext graphicsContext) {
        //Image image = new Image(loadBlockImage());
        //graphicsContext.drawImage(image, x * 30, y * 30, 28, 28);
        graphicsContext.fillRect( x * 30, y * 30, 28, 28);
    }

    private InputStream loadBlockImage() {
        InputStream image = null;
        try {
            image = new BufferedInputStream(new FileInputStream("greenBlock.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return image;
    }
}
