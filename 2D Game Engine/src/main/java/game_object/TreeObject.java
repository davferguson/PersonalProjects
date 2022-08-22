package game_object;

import main.GamePanel;
import main.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TreeObject extends GameObject{
    private GamePanel gp;
    private BufferedImage image;
    private Rectangle bounds;
    private int rowPos, colPos;
    private int pixelWidth, pixelHeight;
    private int screenXPos, screenYPos;

    public TreeObject(GamePanel gp, BufferedImage image, int rowPos, int colPos, int pixelWidth, int pixelHeight){
        this.gp = gp;
        this.image = image;
        this.bounds = new Rectangle((colPos) * GamePanel.TILE_SIZE, rowPos * GamePanel.TILE_SIZE, pixelWidth * GamePanel.SCALE, pixelHeight * GamePanel.SCALE);
        this.pixelWidth = pixelWidth;
        this.pixelHeight = pixelHeight;
        this.rowPos = rowPos;
        this.colPos = colPos;

    }

    @Override
    public Position getPosition(){
        return new Position(colPos*GamePanel.TILE_SIZE, rowPos*GamePanel.TILE_SIZE);
    }

    public void draw(Graphics2D g2){
        int worldXPos = colPos * GamePanel.TILE_SIZE;
        int worldYPos = rowPos * GamePanel.TILE_SIZE;

        screenXPos = worldXPos - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
        screenYPos = worldYPos - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        g2.drawImage(image, screenXPos, screenYPos, null);

    }

    public void drawDebug(Graphics2D g2){
        g2.setColor(Color.RED);
        g2.drawRect(screenXPos + pixelWidth, screenYPos + pixelHeight, pixelWidth, pixelHeight);
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

}
