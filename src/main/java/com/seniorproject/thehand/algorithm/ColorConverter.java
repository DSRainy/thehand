package com.seniorproject.thehand.algorithm;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;

/**
 *
 * @author RainWhileLoop
 */
public class ColorConverter {

    private int[][] rgbPixels;
    private int width, height;

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

    public int[][] getRGBPixel() {
        return rgbPixels;
    }

    public BufferedImage getImage() {
        int i = 0;
        int[] pixels = new int[width * height];

        for (int y = 0; y < height; y++) { // rotate clockwise
            for (int x = 0; x < width; x++) {
                pixels[i] = rgbPixels[x][y];
                i++;
            }
        }
        BufferedImage bimage = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        Graphics2D bGr;
        bGr = bimage.createGraphics();
        bGr.drawImage(new Frame().createImage(new MemoryImageSource(width, height, pixels, 0, width)), 0, 0, null);
        bGr.dispose();
        return bimage;
    }

}
