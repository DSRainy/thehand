package com.seniorproject.thehand.algorithm;

import com.seniorproject.thehand.utils.ArrayUtil;
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
    protected float lowThreshold[] = new float[3];
    protected float highThreshold[] = new float[3];

    public void setBufferedImage(BufferedImage bufferedImage) {
        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
        rgbPixels = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rgbPixels[x][y] = bufferedImage.getRGB(x, y); // Used (width - x - 1) instead x for flip horizontal
            }
        }
        RGBtoHSV();
    }

    public int[][] getRGBPixels2Dim() {
        return rgbPixels;
    }

    public BufferedImage getImage() {
        int[] pixels = ArrayUtil.change2DimTo1Dim(this.hsvPixels, height, width);
        BufferedImage bimage = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        Graphics2D bGr;
        bGr = bimage.createGraphics();
        bGr.drawImage(new Frame().createImage(new MemoryImageSource(width, height, pixels, 0, width)), 0, 0, null);
        bGr.dispose();
        return bimage;
    }

    private void RGBtoHSV() {
        hsvPixels = new int[width][height];
        float[] hsv = new float[3];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
//                float r = rgbPixels[x][y] >> 16 & 0xFF;
//                float g = rgbPixels[x][y] >> 8 & 0xFF;
//                float b = rgbPixels[x][y] & 0xFF;
//                r /= 255.0f;
//                g /= 255.0f;
//                b /= 255.0f;
//                float max = Math.max(Math.max(r, g), b);
//                float min = Math.min(Math.min(r, g), b);
//
//                hsv[2] = max;
//                hsv[1] = (hsv[2] == 0) ? 0.0f : (max - min) / hsv[2];
//
//                if (r == max) {
//                    hsv[0] = 60.0f * (g - b) / (max - min) + (g < b ? 6 : 0);
//                } else if (g == max) {
//                    hsv[0] = 60.0f * (b - r) / (max - min) + 2;
//                } else {
//                    hsv[0] = 60.0f * (r - g) / (max - min) + 4;
//                }
//
//                if (hsv[0] < 0.0f) {
//                    hsv[0] += 360.0f;
//                }
                int r = rgbPixels[x][y] >> 16 & 0xFF;
                int g = rgbPixels[x][y] >> 8 & 0xFF;
                int b = rgbPixels[x][y] & 0xFF;
                hsv = Color.RGBtoHSB(r, g, b, hsv);
//                hsvPixels[x][y] = Color.getHSBColor(hsv[0], hsv[1], hsv[2]).getRGB();
                hsvPixels[x][y] = threshold(hsv);
            }
        }
    }
    // my hand is
    // low 0 0 22
    // high 43 52 59
    private int threshold(float[] hsv) {
        hsv[0] *= 360f;
        if (lowThreshold[0] <= hsv[0] && hsv[0] <= highThreshold[0]
                && lowThreshold[1] <= hsv[1] && hsv[1] <= highThreshold[1]
                && lowThreshold[2] <=hsv[2] && hsv[2] <= highThreshold[2]) {
            return 0;
        } else {
            return 0xffffffff;
        }
    }

    /**
     * set the low threshold using HSV ranged
     *
     * @param lowThreshold an array of hsv<br/> 
     * <ul>
     * <li>lowThreshold[0] is range 0 - 360</li>
     * <li>lowThreshold[1] is range 0 - 100</li>
     * <li>lowThreshold[2] is range 0 - 100</li>
     * </ul>
     */
    public void setLowThreshold(int lowThreshold[]) {
        this.lowThreshold[0] = lowThreshold[0];
        this.lowThreshold[1] = lowThreshold[1] / 100f;
        this.lowThreshold[2] = lowThreshold[2] / 100f;
    }

    /**
     * set the low threshold using HSV ranged
     *
     * @param highThreshold an array of hsv<br/> 
     * <ul>
     * <li>highThreshold[0] is range 0 - 360</li>
     * <li>highThreshold[1] is range 0 - 100</li>
     * <li>highThreshold[2] is range 0 - 100</li>
     * </ul>
     */
    public void setHighThreshold(int highThreshold[]) {
        this.highThreshold[0] = highThreshold[0];
        this.highThreshold[1] = highThreshold[1] / 100f;
        this.highThreshold[2] = highThreshold[2] / 100f;
    }

    @Deprecated
    public void setLowThreshold(int lowH) {
        this.lowThreshold[0] = lowH;
    }

    @Deprecated
    public void setHighThreshold(int high) {
        this.highThreshold[0] = high;
    }

}
