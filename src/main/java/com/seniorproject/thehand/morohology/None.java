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
 */
public class None extends AbstractOperation {

    @SuppressWarnings("unused")
    private STRUCTURING_ELEMENT_SHAPE shape;

    public None() {
        shapeSize = 2;
        shape = STRUCTURING_ELEMENT_SHAPE.SQUARE;
    }

    public None(STRUCTURING_ELEMENT_SHAPE shape, int shapeSize) {
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
                    "The image must be of type TYPE_BYTE_GRAY");
        }

        return img;
    }

}
