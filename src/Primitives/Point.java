package Primitives;

public class Point {

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x;
    public double y;

    @Override
    public String toString() {
        return "Primitives.Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
