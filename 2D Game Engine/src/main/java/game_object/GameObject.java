package game_object;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {

    private int objectId;
    private int worldX;
    private int worldY;
    private Rectangle bounds;
    private BufferedImage image;

    public GameObject(int objectId, int worldX, int worldY, Rectangle bounds, BufferedImage image){
        this.objectId = objectId;
        this.worldX = worldX;
        this.worldY = worldY;
        this.bounds = bounds;
        this.image = image;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
