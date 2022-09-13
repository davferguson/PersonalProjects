package game_object;

import camera.CameraManager;
import main.GamePanel;
import tool.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class TestObject {
    private static int worldXPos = 48;
    private static int worldYPos = 0;
    public static Rectangle collisionBounds = new Rectangle(worldXPos, worldYPos, 48, 48);
    private BufferedImage objectImage;

    public TestObject(){
        try{
            BufferedImage tileset = ImageIO.read(new FileInputStream("src/main/resources/tileset/ashlands_tileset.png"));
            objectImage = tileset.getSubimage(16, 16, GamePanel.ORIGINAL_TILE_SIZE, GamePanel.ORIGINAL_TILE_SIZE);
            objectImage = UtilityTool.scaleImage(objectImage, GamePanel.ORIGINAL_TILE_SIZE * GamePanel.SCALE, GamePanel.ORIGINAL_TILE_SIZE * GamePanel.SCALE);
        } catch(IOException e){
            System.out.println("error reading player spritesheet image: " + e.getMessage());
        }
    }
    public void update(){
        collisionBounds.setBounds(-CameraManager.getCameraXPos()+worldXPos, -CameraManager.getCameraYPos()+worldYPos, collisionBounds.width, collisionBounds.height);
    }
    public void draw(Graphics2D g2){
        g2.drawImage(objectImage, -CameraManager.getCameraXPos()+worldXPos, -CameraManager.getCameraYPos()+worldYPos, null);
        g2.setColor(Color.RED);
        g2.drawRect(collisionBounds.x, collisionBounds.y, collisionBounds.width, collisionBounds.height);
    }

}
