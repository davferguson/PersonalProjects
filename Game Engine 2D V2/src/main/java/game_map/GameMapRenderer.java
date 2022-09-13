package game_map;

import camera.CameraManager;
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
        g2.drawImage(gameMap, -CameraManager.getCameraXPos(), -CameraManager.getCameraYPos(), null);
    }

    public BufferedImage getGameMap() {
        return gameMap;
    }

    public void setGameMap(BufferedImage gameMap) {
        this.gameMap = gameMap;
    }
}
