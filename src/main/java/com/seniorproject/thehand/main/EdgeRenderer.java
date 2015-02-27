package com.seniorproject.thehand.main;

import com.seniorproject.thehand.algorithm.CannyEdgeDetector;
import com.seniorproject.thehand.algorithm.ColorConverter;
import com.seniorproject.thehand.algorithm.FastConvexHull;
import com.seniorproject.thehand.morohology.Opening;
import com.seniorproject.thehand.utils.ImageUtil;
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
    FastConvexHull fastConvexHull = new FastConvexHull();
    Opening opening = new Opening();

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
        this.image = opening.execute(ImageUtil.convertType(converter.getImage(), BufferedImage.TYPE_BYTE_GRAY));
//        this.image = closing.execute(ImageUtil.convertType(converter.getImage(), BufferedImage.TYPE_BYTE_GRAY));
        edgeDetector.setSourceImage(this.image);
//        edgeDetector.setData(converter.getPixelOutput(), converter.width, converter.height);
        edgeDetector.process();
//        fastConvexHull.setInput(edgeDetector.getData2Dim());
//        try {
//            fastConvexHull.execute();
//            this.image = fastConvexHull.getImage();
//        } catch (Exception ex) {
//            ex.printStackTrace();
            this.image = edgeDetector.getEdgesImage();
//
//        }

//        convexHull.setInput(edgeDetector.getData2Dim());
//        convexHull.execute();
//        this.image = converter.getImage();
//        this.image = convexHull.getImage();
//        this.image = image;
    }

    public void setLowThreshold(int h, int s, int v) {
        int hsv[] = {h, s, v};
        converter.setLowThreshold(hsv);
    }

    public void setHighThreshold(int h, int s, int v) {
        int hsv[] = {h, s, v};
        converter.setHighThreshold(hsv);
    }

    public void setLowThreshold(int h) {
        converter.setLowThreshold(h);
    }

    public void setHighThreshold(int h) {
        converter.setHighThreshold(h);
    }

}
