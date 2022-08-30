package game_object;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TreeObject extends GameObject{


    public TreeObject(int worldX, int worldY) throws IOException {
        super(46, worldX, worldY,
                new Rectangle(worldX, worldY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE*2),
                UtilityTool.scaleImage(ImageIO.read(new FileInputStream("resources/objects/TreeObject.png")), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE*2) );
    }
}
