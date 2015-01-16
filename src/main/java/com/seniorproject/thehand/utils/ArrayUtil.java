/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seniorproject.thehand.utils;

/**
 *
 * @author RainWhileLoop
 */
public class ArrayUtil {

    public static int[] change2DTo1D(int[][] matrix) {
        int i = 0;
         int row = matrix[1].length;
         int col = matrix.length;
        int[] matrixOut = new int[row * col];
        
        for (int y = 0; y < row; y++) { // rotate clockwise
            for (int x = 0; x < col; x++) {
                matrixOut[i] = matrix[x][y];
                i++;
            }
        }
        return matrixOut;
    }

    public static int[][] change1DTo2D(int[] matrix, int col, int row) {
        int[][] matrixOut = new int[col][row];
        int i = 0;
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++) {
                matrixOut[x][y] = matrix[i];
                i++;
            }
        }
        return matrixOut;
    }
}
