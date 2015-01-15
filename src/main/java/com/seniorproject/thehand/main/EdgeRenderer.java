package com.seniorproject.thehand.main;

import com.seniorproject.thehand.algorithm.CannyEdgeDetector;
import com.seniorproject.thehand.algorithm.ColorConverter;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;

/**
 *
 * @author RainWhileLoop
 */
public class EdgeRenderer extends JLabel {

    private BufferedImage image;
    ColorConverter converter = new ColorConverter();
    CannyEdgeDetector edgeDetector = new CannyEdgeDetector();

    public EdgeRenderer() {
        edgeDetector.setLowThreshold(1f);
        edgeDetector.setHighThreshold(8f);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        if (image != null) {
            Graphics2D g2 = (Graphics2D) g;

            //Fixed the sized to draw the image
            g2.drawImage(image, 0, 0, 320, 240, null);
            g2.dispose();
        }
    }

    public void setImage(BufferedImage image) {
        converter.setBufferedImage(image);
        converter.process();
        edgeDetector.setData(converter.getPixelOutput(), converter.width, converter.height);
        edgeDetector.process();
//        this.image = converter.getImage();
        this.image = edgeDetector.getEdgesImage();
    }

    public void setLowThreshold(int h,int s,int v) {
        int hsv[] = {h,s,v};
        converter.setLowThreshold(hsv);
    }

    public void setHighThreshold(int h,int s,int v) {
        int hsv[] = {h,s,v};
        converter.setHighThreshold(hsv);
    }

    public void setLowThreshold(int h) {
        converter.setLowThreshold(h);
    }

    public void setHighThreshold(int h) {
        converter.setHighThreshold(h);
    }

}
