package com.seniorproject.thehand.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;

/**
 *
 * @author RainWhileLoop
 */
public class WebCamRenderer extends JLabel {

    private BufferedImage image;

    @Override
    public void paintComponent(Graphics g) {
        if (image != null) {
            Graphics2D g2 = (Graphics2D) g;
            
            //Fixed the sized to draw the image
            g2.drawImage(image, 0, 0, 320,240, null);
            g2.dispose();
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
