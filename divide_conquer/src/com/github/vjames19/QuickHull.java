package com.github.vjames19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vjames19 on 3/25/15.
 */
public class QuickHull {

    public static List<Point> convexHull(List<Point> points) {
        if (points.size() <= 3) {
            return new ArrayList<Point>(points);
        }

        points = new ArrayList(points); // Make a shallow copy
        Point a = points.get(0);
        Point b = points.get(0);
        for (Point point: points) {
            if (point.getX() < a.getX()) {
                a = point;
            }

            if (point.getX() > b.getX()) {
                b = point;
            }
        }

        List<Point> convexHull = new ArrayList<Point>();
        convexHull.add(a);
        convexHull.add(b);

        points.remove(a);
        points.remove(b);

        ArrayList<Point> leftSet = new ArrayList<Point>();
        ArrayList<Point> rightSet = new ArrayList<Point>();

        for (Point point : points) {
            if (point.distance(a, b) < 0) {
                leftSet.add(point);
            } else {
                rightSet.add(point);
            }
        }

        findHull(leftSet, a, b, convexHull);
        findHull(rightSet, b, a, convexHull);

        return convexHull;
    }

    private static void findHull(List<Point> points, Point a, Point b, List<Point> hull) {
        if (points.isEmpty()) {
            return;
        }

        // find pmax
        Point p = points.get(0);
        for (Point point : points) {
            if (Math.abs(point.distance(a, b)) > Math.abs(p.distance(a, b))) {
                p = point;
            }
        }

        hull.add(p);
        points.remove(p);


        // determine who is on the left of a,pmax
        List<Point> leftAP = new ArrayList<Point>();
        for (Point point : points) {
            if (point.distance(a, p) < 0) {
                leftAP.add(point);
            }
        }

        List<Point> leftPB = new ArrayList<Point>();
        for (Point point : points) {
            if (point.distance(p, b) < 0) {
                leftPB.add(point);
            }
        }

        findHull(leftAP, a, p, hull);
        findHull(leftPB, p, b, hull);
    }

    public static void main(String[] args) {
        List<Point> points = Arrays.asList(
                new Point(-10, 10),
                new Point(10, 10),
                new Point(11, 5),
                new Point(-11, 5),
                new Point(-10, 12),
                new Point(10, -10),
                new Point(-10, -10),
                new Point(0, 0),
                new Point(1, 1),
                new Point(1, 3),
                new Point(4, 3),
                new Point(4, 8),
                new Point(5, 8),
                new Point(3, 8),
                new Point(6, 8),
                new Point(9, 8)
        );

        System.out.printf("The points are the following: %s\n", points);
        System.out.println("The convex hull:" + convexHull(points));
    }
}
