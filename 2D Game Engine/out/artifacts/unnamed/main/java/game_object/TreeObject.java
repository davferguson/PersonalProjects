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

    public TreeObject(GamePanel gp, BufferedImage image, Rectangle bounds, int rowPos, int colPos){
        this.gp = gp;
        this.image = image;
        //this.bounds = bounds;
        this.bounds = new Rectangle((colPos) * GamePanel.TILE_SIZE, rowPos * GamePanel.TILE_SIZE, 48 * GamePanel.SCALE, 48 * GamePanel.SCALE);
        this.rowPos = rowPos;
        this.colPos = colPos;

    }

    public Position getPostition(){
        return new Position(colPos*GamePanel.TILE_SIZE, rowPos*GamePanel.TILE_SIZE);
    }

    public void draw(Graphics2D g2){
        int worldXPos = colPos * GamePanel.TILE_SIZE;
        int worldYPos = rowPos * GamePanel.TILE_SIZE;

        int screenXPos = worldXPos - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
        int screenYPos = worldYPos - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        g2.drawImage(image, screenXPos, screenYPos, null);

    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public Position getPostion() {
        return new Position(colPos*GamePanel.TILE_SIZE, rowPos*GamePanel.TILE_SIZE);
    }
}
