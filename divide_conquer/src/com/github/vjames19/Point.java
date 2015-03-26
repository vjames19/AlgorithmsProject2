package com.github.vjames19;

/**
 * Created by vjames19 on 3/25/15.
 */
public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Distance of this point to the line depicted by the two points.
     * @return The distance. If distance < 0 its on the left side, else if distance > 0 it lies on the right side.
     */
    public int distance(Point p1, Point p2) {
        return p1.getX()*p2.getY() + x*p1.getY() + p2.getX()*y -  x*p2.getY() - p2.getX()*p1.getY() - p1.getX()*y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
