package com.seniorproject.thehand.algorithm;

import com.seniorproject.thehand.util.Array;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;

/**
 *
 * @author RainWhileLoop
 */
public class ColorConverter {

    protected int[][] rgbPixels, hsvPixels;
    protected int width, height;

    public void setBufferedImage(BufferedImage bufferedImage) {
        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
        rgbPixels = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rgbPixels[width - x - 1][y] = bufferedImage.getRGB(x, y);
            }
        }
    }

    public int[][] getRGBPixels2Dim() {
        return rgbPixels;
    }

    public BufferedImage getImage() {
//        getRGBPixels1Dim();
        int[] pixels = Array.change2DimTo1Dim(this.rgbPixels, height, width);
        BufferedImage bimage = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        Graphics2D bGr;
        bGr = bimage.createGraphics();
        bGr.drawImage(new Frame().createImage(new MemoryImageSource(width, height, pixels, 0, width)), 0, 0, null);
        bGr.dispose();
        return bimage;
    }

//    private void getRGBPixels1Dim() {
//        int i = 0;
//        rgbPixels = new int[width * height];
//
//        for (int y = 0; y < height; y++) { // rotate clockwise
//            for (int x = 0; x < width; x++) {
//                rgbPixels[i] = rgbPixels[x][y];
//                i++;
//            }
//        }
//    }

    private void RGBtoHSV() {
//        getRGBPixels1Dim();
        float[] hsv = new float[3];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                float r = rgbPixels[x][y] >> 16 & 0xFF;
                float g = rgbPixels[x][y] >> 8 & 0xFF;
                float b = rgbPixels[x][y] & 0xFF;
                r /= 255.0f;
                g /= 255.0f;
                b /= 255.0f;
                float max = Math.max(Math.max(r, g), b);
                float min = Math.min(Math.min(r, g), b);

                hsv[2] = max;
                hsv[1] = (hsv[2] == 0) ? 0.0f : (max - min) / hsv[2];

                if (r == max) {
                    hsv[0] = 60.0f * (g - b) / (max - min) + (g < b ? 6 : 0);
                } else if (g == max) {
                    hsv[0] = 60.0f * (b - r) / (max - min) + 2;
                } else {
                    hsv[0] = 60.0f * (r - g) / (max - min) + 4;
                }
                
                if (hsv[0] < 0.0f) {
                    hsv[0] += 360.0f;
                }
                
                hsvPixels[x][y] = Color.getHSBColor(hsv[0], hsv[1], hsv[2]).getRGB();
            }
        }
    }

//    public BufferedImage getHSVImage() {
//
//        return image;
//    }
}
