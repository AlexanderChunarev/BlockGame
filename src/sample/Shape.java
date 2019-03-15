package sample;

import javafx.scene.shape.Polygon;

public class Shape {
    public Polygon createTShape(double x, double y) {
        Polygon hexagon = new Polygon();
        hexagon.getPoints().addAll(x, y,
                x,y+10,
                x+10, y+10,
                x+10, y
                );
        return hexagon;
    }
}
