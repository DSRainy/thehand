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

    ArrayList<Point> convexHull = new ArrayList<>();
    int[][] in;
    public void quickHull() {
//        convexHull = new ArrayList<>();
//        if (convexHull.size() < 3) {
//            return (ArrayList) convexHull.clone();
//        }
        // find extremals
        int minPoint = -1, maxPoint = -1;
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        for (int i = 0; i < convexHull.size(); i++) {
            if (convexHull.get(i).x < minX) {
                minX = convexHull.get(i).x;
                minPoint = i;
            }
            if (convexHull.get(i).x > maxX) {
                maxX = convexHull.get(i).x;
                maxPoint = i;
            }
        }
        Point A = convexHull.get(minPoint);
        Point B = convexHull.get(maxPoint);
        convexHull.add(A);
        convexHull.add(B);
//        convexHull.remove(A);
//        convexHull.remove(B);

        ArrayList<Point> leftSet = new ArrayList<>();
        ArrayList<Point> rightSet = new ArrayList<>();

        for (int i = 0; i < convexHull.size(); i++) {
            Point p = convexHull.get(i);
            if (pointLocation(A, B, p) == -1) {
                leftSet.add(p);
            } else {
                rightSet.add(p);
            }
        }
        hullSet(A, B, rightSet, convexHull);
        hullSet(B, A, leftSet, convexHull);

        
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

    private void hullSet(Point A, Point B, ArrayList<Point> set, ArrayList<Point> hull) {
        int insertPosition = hull.indexOf(B);
        if (set.size() == 0) {
            return;
        }
        if (set.size() == 1) {
            Point p = set.get(0);
            set.remove(p);
            hull.add(insertPosition, p);
            return;
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
        hullSet(A, P, leftSetAP, hull);
        hullSet(P, B, leftSetPB, hull);

    }

    private int pointLocation(Point A, Point B, Point P) {
        int cp1 = (B.x - A.x) * (P.y - A.y) - (B.y - A.y) * (P.x - A.x);
        return (cp1 > 0) ? 1 : -1;
    }
    
    public void setInput(int[][] input) {
        in = input;
        convexHull.clear();
        for (int row = 0; row < in.length; row++) {
            for (int col = 0; col < in[1].length; col++) {
                if (in[row][col] != -1) {
                    Point point = new Point(row, col);
//                    System.out.println("(" + point.x + "," + point.y + ")");
                    convexHull.add(point);
                }
            }
        }
    }

    
    public BufferedImage getImage(){
        BufferedImage image = new BufferedImage(in.length,in[1].length,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Polygon polygon = new Polygon();
        
        for( Point point : convexHull){
//            System.out.println("(" + point.x + "," + point.y + ")");
            polygon.addPoint(point.x, point.y);
        }
        g.drawPolygon(polygon);
        g.dispose();
        return image;
    }

    
    public void setImage(BufferedImage image){
        in = ImageUtil.changeImageToArray(image);
        convexHull.clear();
        for (int row = 0; row < in.length; row++) {
            for (int col = 0; col < in[1].length; col++) {
                if (in[row][col] != -1) {
                    Point point = new Point(row, col);
//                    System.out.println("(" + point.x + "," + point.y + ")");
                    convexHull.add(point);
                }
            }
        }
    }
}
