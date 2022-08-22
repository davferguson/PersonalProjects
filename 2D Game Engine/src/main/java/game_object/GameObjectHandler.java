package game_object;

import main.GamePanel;
import main.UtilityTool;
import tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameObjectHandler {
    private List<GameObject> gameObjects = new ArrayList<>();
    private BufferedImage treeImage;

    public GameObjectHandler(GamePanel gp) {
        generateObjectImages();

        gameObjects.add(new TreeObject(gp, treeImage, 22, 16, 48, 48));
    }

    public void draw(Graphics2D g2){
        for(GameObject curObject : gameObjects){
            curObject.draw(g2);
        }
    }

    public boolean isColliding(int xPos, int yPos, int width, int height){
        Rectangle curBounds = new Rectangle(xPos, yPos, width, height);

        for(GameObject curObject : gameObjects){
            if(curObject.getBounds().contains(curBounds)){
                return true;
            }
        }

        return false;
    }

    private void generateObjectImages() {
        try {
            //TREE_LARGE
            treeImage = ImageIO.read(new FileInputStream("resources/objects/TreeLarge_Object.png"));
            treeImage = UtilityTool.scaleImage(treeImage, GamePanel.TILE_SIZE*3, GamePanel.TILE_SIZE*3);
            //END TREE_LARGE
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
}
