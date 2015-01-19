package com.seniorproject.thehand.morohology;

import java.awt.image.BufferedImage;

/**
 * Closing operation for grayscaled images. The closing operand is the
 * {@link Erosion} of the {@link Dilation}.
 *
 * The closing operation will remove "holes", i.e. darker parts, in the image.
 * This operation can for instance be useful to remove uninteresting structures
 * yielding better accuracy when performing edge detection.
 *
 *
 */
public class Closing extends AbstractOperation {

    private STRUCTURING_ELEMENT_SHAPE shape;

    public Closing() {
        shapeSize = 2;
        shape = STRUCTURING_ELEMENT_SHAPE.SQUARE;
    }

    public Closing(STRUCTURING_ELEMENT_SHAPE shape, int shapeSize) {
        this.shape = shape;
        super.shapeSize = shapeSize;
    }

    /**
     * @see AbstractOperation
     */
    @Override
    public BufferedImage execute(BufferedImage img) {
        if (img.getType() != BufferedImage.TYPE_BYTE_GRAY) {
            throw new IllegalArgumentException(
                    "The image must be of type TYPE_BYTE_GRAY " + img.getType() + " " + BufferedImage.TYPE_INT_ARGB + " " + BufferedImage.TYPE_BYTE_GRAY);
        }

        BufferedImage dilatedImg, closedImg;
        Erosion erosion = new Erosion(shape, shapeSize);
        Dilation dilation = new Dilation(shape, shapeSize);

        dilatedImg = dilation.execute(img);
        closedImg = erosion.execute(dilatedImg);

        return closedImg;
    }

}
