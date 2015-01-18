package com.seniorproject.thehand.morohology;

import java.awt.image.BufferedImage;

/**
 * Opening operation for grayscaled images. The opening operand is the
 * {@link Dilation} of the {@link Erosion}.
 *
 * The opening operation will remove small "objects", i.e. brighter parts, in
 * the image. This operation can for instance be useful to remove uninteresting
 * structures yielding better accuracy when performing edge detection.
 *
 *
 */
public class Opening extends AbstractOperation {

    private STRUCTURING_ELEMENT_SHAPE shape;

    public Opening() {
        shapeSize = 2;
        shape = STRUCTURING_ELEMENT_SHAPE.SQUARE;
    }

    public Opening(STRUCTURING_ELEMENT_SHAPE shape, int shapeSize) {
        this.shape = shape;
        super.shapeSize = shapeSize;
    }

    /**
     * @see AbstractOperation
     */
    @Override
    public BufferedImage execute(BufferedImage img) {
        if (img.getType() != BufferedImage.TYPE_BYTE_GRAY) {
            throw new IllegalArgumentException("The image must be of type TYPE_BYTE_GRAY");
        }

        BufferedImage erodedImg, openedImg;
        Dilation dilation = new Dilation(shape, shapeSize);
        Erosion erosion = new Erosion(shape, shapeSize);

        erodedImg = erosion.execute(img);
        openedImg = dilation.execute(erodedImg);

        return openedImg;
    }

}
