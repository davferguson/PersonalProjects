package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    public static BufferedImage scaleImage(BufferedImage originalImage, int newWidth, int newHeight){
        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g2.dispose();
        return scaledImage;
    }
}
