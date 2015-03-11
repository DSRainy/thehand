package com.seniorproject.thehand.algorithm;

import com.seniorproject.thehand.utils.ImageUtil;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author casanovatoy
 */
public class QuickHull {

    ArrayList<Point> data = new ArrayList<>();
    ArrayList<Point> convexHull = new ArrayList<>();
    int[][] in;

    public void quickHull() {
        convexHull.clear();
//        data = new ArrayList<>();
//        if (data.size() < 3) {
//            return (ArrayList) data.clone();
//        }
        int minPoint = -1, maxPoint = -1;
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;

        // Select point which leftmost and rightmost
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).x < minX) {
                minX = data.get(i).x;
                minPoint = i;
            }
            if (data.get(i).x > maxX) {
                maxX = data.get(i).x;
                maxPoint = i;
            }
        }

        Point pMinX = data.get(minPoint);
        Point pMaxX = data.get(maxPoint);
//        data.add(pMinX);
        data.add(pMaxX);
//        data.remove(pMinX);
        data.remove(pMaxX);
        ArrayList<Point> leftSet = new ArrayList<>();
        ArrayList<Point> rightSet = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            Point p = data.get(i);
            if (pointLocation(pMinX, pMaxX, p) == -1) {
                leftSet.add(p);
            } else {
                rightSet.add(p);
            }
        }
        hullSet(pMinX, pMaxX, rightSet, data);
        hullSet(pMaxX, pMinX, leftSet, data);

    }

    /*
     * Computes the square of the distance of point C to the segment defined by points AB
     */
    private int distance(Point A, Point B, Point C) {
        int ABx = B.x - A.x;
        int ABy = B.y - A.y;
        int num = ABx * (A.y - C.y) - ABy * (A.x - C.x);
        if (num < 0) {
            num = -num;
        }
        return num;
    }

    private ArrayList<Point> hullSet(Point A, Point B, ArrayList<Point> set, ArrayList<Point> hull) {
        int insertPosition = hull.indexOf(B);
        if (set.isEmpty()) {
            return null;
        }
        if (set.size() == 1) {
            Point p = set.get(0);
            set.remove(p);
            hull.add(insertPosition, p);
            return null;
        }
        int dist = Integer.MIN_VALUE;
        int furthestPoint = -1;
        for (int i = 0; i < set.size(); i++) {
            Point p = set.get(i);
            int distance = distance(A, B, p);
            if (distance > dist) {
                dist = distance;
                furthestPoint = i;
            }
        }
        Point P = set.get(furthestPoint);
        set.remove(furthestPoint);
        hull.add(insertPosition, P);

        // Determine who's to the left of AP
        ArrayList<Point> leftSetAP = new ArrayList<>();
        for (int i = 0; i < set.size(); i++) {
            Point M = set.get(i);
            if (pointLocation(A, P, M) == 1) {
                leftSetAP.add(M);
            }
        }

        // Determine who's to the left of PB
        ArrayList<Point> leftSetPB = new ArrayList<>();
        for (int i = 0; i < set.size(); i++) {
            Point M = set.get(i);
            if (pointLocation(P, B, M) == 1) {
                leftSetPB.add(M);
            }
        }

        
//        printPoint(hull);
        hullSet(A, P, leftSetAP, hull);
        hullSet(P, B, leftSetPB, hull);
        convexHull.addAll(leftSetAP);
        convexHull.addAll(leftSetPB);
        return hull;
    }

    private int pointLocation(Point A, Point B, Point P) {
        int cp1 = (B.x - A.x) * (P.y - A.y) - (B.y - A.y) * (P.x - A.x);
        return (cp1 > 0) ? 1 : -1;
    }

    public void setInput(int[][] input) {
        in = input;
        data.clear();
        for (int row = 0; row < in.length; row++) {
            for (int col = 0; col < in[1].length; col++) {
                if (in[row][col] != -1) {
                    Point point = new Point(row, col);
//                    System.out.println("(" + point.x + "," + point.y + ")");
                    data.add(point);
                }
            }
        }
    }

    public BufferedImage getImage() {
        BufferedImage image = new BufferedImage(in.length, in[1].length, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.GREEN);
        Polygon polygon = new Polygon();
        for (Point point : data) {
            polygon.addPoint(point.x, point.y);
        }
//        for (Point point : convexHull) {
//            polygon.addPoint(point.x, point.y);
//        }
//        for (int i=0;i<100;i++) {
//            polygon.addPoint(convexHull.get(i).x, convexHull.get(i).y);
//        }
        g.drawPolygon(polygon);
        g.dispose();
        return image;
    }

    public void setImage(BufferedImage image) {
        in = ImageUtil.changeImageToArray(image);
        data.clear();
//        convexHull.clear();
//        data = new ArrayList<>();
        for (int row = 0; row < in.length; row++) {
            for (int col = 0; col < in[1].length; col++) {
                if (in[row][col] != -1) {
                    Point point = new Point(row, col);
                    data.add(point);
//                    data.add(point);
                }
            }
        }
    }

    private void printPoint(ArrayList<Point> list) {
        for (Point p : list) {
            System.out.println("(" + p.x + "," + p.y + ")");
        }
        System.out.println("");
    }
}
