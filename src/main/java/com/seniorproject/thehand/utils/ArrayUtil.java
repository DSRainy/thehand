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

    public static int[] change2DimTo1Dim(int[][] matrix, int row, int col) {
        int i = 0;
        int[] matrixOut = new int[row * col];

        for (int y = 0; y < row; y++) { // rotate clockwise
            for (int x = 0; x < col; x++) {
                matrixOut[i] = matrix[x][y];
                i++;
            }
        }
        return matrixOut;
    }

    public static int[][] change1DimTo2Dim(int[] matrix, int col, int row) {
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
