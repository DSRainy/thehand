/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seniorproject.thehand.utils;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;

/**
 *
 * @author RainWhileLoop
 */
public class ImageUtil {

    public static BufferedImage getImage(int[] pixels) {
        BufferedImage bimage = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        Graphics2D bGr;
        bGr = bimage.createGraphics();
        bGr.drawImage(new Frame().createImage(new MemoryImageSource(320, 240, pixels, 0, 320)), 0, 0, null);
        bGr.dispose();
        return bimage;
    }

    public static BufferedImage getImage(int[][] pixels2D) {

        int[] pixels = ArrayUtil.change2DTo1D(pixels2D);
        BufferedImage bimage = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
        Graphics2D bGr;
        bGr = bimage.createGraphics();
        bGr.drawImage(new Frame().createImage(new MemoryImageSource(320, 240, pixels, 0, 320)), 0, 0, null);
        bGr.dispose();
        return bimage;
    }
/**
 * Convert the type of bufferedImage
 * 
 * @param image the image which convert type
 * @param type type of output image 
 * @return imageOutput 
 * @see BufferedImage.TYPE_BYTE_GRAY , BufferedImage.TYPE_INT_RGB
 */
    public static BufferedImage convertType(BufferedImage image, int type) {
        BufferedImage convertedImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        convertedImg.getGraphics().drawImage(image, 0, 0, null);
        return convertedImg;
    }

}
