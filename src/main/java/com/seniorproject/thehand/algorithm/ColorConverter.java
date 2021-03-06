package com.seniorproject.thehand.algorithm;

import com.seniorproject.thehand.utils.ArrayUtil;
import com.seniorproject.thehand.utils.ImageUtil;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author RainWhileLoop
 */
public class ColorConverter {

    protected int[][] rgbPixels, hsvPixels;
    public int width, height;
    protected float lowThreshold[] = new float[3];
    protected float highThreshold[] = new float[3];

    public int[] r, g, b;
    public float[] h= new float[width*height], s= new float[width*height], v= new float[width*height];
    private static int i = 0;

    public void process() {
        RGBtoHSV();
    }

    public void separateRGB() {
        int[] pixel = ArrayUtil.change2DTo1D(rgbPixels);
        for (int i = 0; i < width * height; i++) {
            int[] rgb = ImageUtil.separate(pixel[i]);
            r[i] = rgb[0];
            g[i] = rgb[1];
            b[i] = rgb[2];
        }
    }

    public void separateHSV(float hsv[]) {
        
        h[i] = hsv[0];
        s[i] = hsv[1];
        v[i] = hsv[2];
        i++;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
        rgbPixels = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rgbPixels[x][y] = bufferedImage.getRGB(x, y); // Used (width - x - 1) instead x for flip horizontal
            }
        }

    }

    public int[][] getRGBPixels2Dim() {
        return rgbPixels;
    }

    public int[] getPixelOutput() {
        return ArrayUtil.change2DTo1D(this.hsvPixels);
    }

    public BufferedImage getImage() {
        // you can set the output image by change the this.hsvPixels variable
        return ImageUtil.getImage(this.hsvPixels);
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
//                separateHSV(hsv);
//                hsvPixels[x][y] = Color.getHSBColor(hsv[0], 1, 1).getRGB();
                hsvPixels[x][y] = threshold(hsv);
            }
        }
    }

    // my hand is
    // low 0 0 22
    // high 43 52 59
    /**
     * this method is threshold using hsv ranged
     *
     * @param hsv a hsv value for checking
     * @return black pixel if <b>hsv</b> is in range <br/>
     *
     */
    private int threshold(float[] hsv) {
        hsv[0] *= 360f;
        if (lowThreshold[0] <= hsv[0] && hsv[0] <= highThreshold[0]
                && lowThreshold[1] <= hsv[1] && hsv[1] <= highThreshold[1]
                && lowThreshold[2] <= hsv[2] && hsv[2] <= highThreshold[2]) {
            return 0;
        } else {
            return -1;
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
        this.lowThreshold[1] = 0f;
        this.lowThreshold[2] = 0f;
    }

    @Deprecated
    public void setHighThreshold(int high) {
        this.highThreshold[0] = high;
        this.highThreshold[1] = 1f;
        this.highThreshold[2] = 1f;
    }

}
