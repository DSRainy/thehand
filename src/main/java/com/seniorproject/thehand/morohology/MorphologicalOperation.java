package com.seniorproject.thehand.morohology;

import java.awt.image.BufferedImage;

public interface MorphologicalOperation {

    /**
     * The different shapes of a structuring element
     */
    public enum STRUCTURING_ELEMENT_SHAPE {

        SQUARE, VERTICAL_LINE, HORIZONTAL_LINE, CIRCLE
    }

    public BufferedImage execute(BufferedImage img);

    public int getShapeSize();
}
