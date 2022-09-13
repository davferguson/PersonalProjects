package game_map;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameMapRenderer {
    private BufferedImage gameMap;

    public GameMapRenderer(BufferedImage gameMap){
        this.gameMap = gameMap;
    }

    public void draw(Graphics2D g2){
//        int curScreenXPos = -mainCamera.getWorldX() + GamePanel.SCREEN_WIDTH/2;
//        int curScreenYPos = -mainCamera.getWorldY() + GamePanel.SCREEN_HEIGHT/2;
        g2.drawImage(gameMap, 0, 0, null);
    }
}
