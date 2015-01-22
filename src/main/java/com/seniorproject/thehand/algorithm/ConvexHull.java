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
import java.util.List;

/**
 *
 * @author RainWhileLoop
 */
public class ConvexHull {

    protected int[][] input;
    protected int[][] data;
    protected int[][] dataOutput;

    private List<Point> pointList;
    private ArrayList<Point> result;

    public void execute() {
//        ArrayList<Point> pointList = (ArrayList<Point>) points.clone();
        Collections.sort(pointList, new XCompare());

        int n = pointList.size();

        Point[] lUpper = new Point[n];

        lUpper[0] = pointList.get(0);
        lUpper[1] = pointList.get(1);

        int lUpperSize = 2;

        for (int i = 2; i < n; i++) {
            lUpper[lUpperSize] = pointList.get(i);
            lUpperSize++;

            while (lUpperSize > 2 && !rightTurn(lUpper[lUpperSize - 3], lUpper[lUpperSize - 2], lUpper[lUpperSize - 1])) {
                // Remove the middle point of the three last
                lUpper[lUpperSize - 2] = lUpper[lUpperSize - 1];
                lUpperSize--;
            }
        }

        Point[] lLower = new Point[n];

        lLower[0] = pointList.get(n - 1);
        lLower[1] = pointList.get(n - 2);

        int lLowerSize = 2;

        for (int i = n - 3; i >= 0; i--) {
            lLower[lLowerSize] = pointList.get(i);
            lLowerSize++;

            while (lLowerSize > 2 && !rightTurn(lLower[lLowerSize - 3], lLower[lLowerSize - 2], lLower[lLowerSize - 1])) {
                // Remove the middle point of the three last
                lLower[lLowerSize - 2] = lLower[lLowerSize - 1];
                lLowerSize--;
            }
        }

        result = new ArrayList<>();

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
            return (new Integer(o1.x)).compareTo(o2.x);
        }
    }

    public void setInput(int[][] input) {
        this.input = input;
        this.dataOutput = input;
        pointList = new ArrayList<>();
        setData();
    }

    private void setData() {
        try {
            for (int y = 0; y < this.input[1].length; y++) {
                for (int x = 0; x < this.input.length; x++) {
                    if (this.input[x][y] != 0) {
                        pointList.add(new Point(x, y));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[][] getDataOutput() {
        int i = 0;
        for (int y = 0; y < dataOutput[1].length; y++) {
            for (int x = 0; x < dataOutput.length; x++) {
                if (i < result.size()) {
                    if (result.get(i).x == x && result.get(i).y == y) {
                        dataOutput[x][y] = 0;
                        i++;
                    } else {
                        dataOutput[x][y] = 0xFFFFFFFF;
                    }
                }
            }
        }
        return dataOutput;
    }

    public BufferedImage getImage() {
        return ImageUtil.getImage(getDataOutput());
    }

//    private Comparator compare() {
//        return new Comparator() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                Point p1 = (Point) o1;
//                Point p2 = (Point) o2;
//                return (new Integer(p1.x)).compareTo(p2.x);
//            }
//        };
//    }
}
