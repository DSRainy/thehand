/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seniorproject.thehand.algorithm;

import com.seniorproject.thehand.utils.ImageUtil;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FastConvexHull {

    private final ArrayList<Point> points = new ArrayList<>();
    private final ArrayList<Point> result = new ArrayList<>();

    public void execute() {
//        ArrayList<Point> points = (ArrayList<Point>) points.clone();
        Collections.sort(points, new XCompare());

        int n = points.size();

        Point[] lUpper = new Point[n];

        lUpper[0] = points.get(0);
        lUpper[1] = points.get(1);

        int lUpperSize = 2;

        for (int i = 2; i < n; i++) {
            lUpper[lUpperSize] = points.get(i);
            lUpperSize++;

            while (lUpperSize > 2 && !rightTurn(lUpper[lUpperSize - 3], lUpper[lUpperSize - 2], lUpper[lUpperSize - 1])) {
                // Remove the middle point of the three last
                lUpper[lUpperSize - 2] = lUpper[lUpperSize - 1];
                lUpperSize--;
            }
        }

        Point[] lLower = new Point[n];

        lLower[0] = points.get(n - 1);
        lLower[1] = points.get(n - 2);

        int lLowerSize = 2;

        for (int i = n - 3; i >= 0; i--) {
            lLower[lLowerSize] = points.get(i);
            lLowerSize++;

            while (lLowerSize > 2 && !rightTurn(lLower[lLowerSize - 3], lLower[lLowerSize - 2], lLower[lLowerSize - 1])) {
                // Remove the middle point of the three last
                lLower[lLowerSize - 2] = lLower[lLowerSize - 1];
                lLowerSize--;
            }
        }

        for (int i = 0; i < lUpperSize; i++) {
            result.add(lUpper[i]);
        }

        for (int i = 1; i < lLowerSize - 1; i++) {
            result.add(lLower[i]);
        }

    }

    private boolean rightTurn(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x) > 0;
    }

    private class XCompare implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            return (new Integer(o1.x)).compareTo(new Integer(o2.x));
        }
    }

    public void setInput(int[][] input) {
        for (int row = 0; row < input.length; row++) {
            for (int col = 0; col < input[1].length; col++) {
                if (input[row][col] != -1) {
                    Point point = new Point(row, col);
                    points.add(point);
                }
            }
        }
    }

    public BufferedImage getImage() {
        int[][] output = new int[240][320];
        for (Point point : result) {
            output[point.y - 1][point.x - 1] = -1;
        }
        return ImageUtil.getImage(output);
    }
}
