package tile;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage image;
    private boolean isCollidable = false;

    public Tile(){

    }
    public Tile(BufferedImage image, boolean isCollidable){
        this.image = image;
        this.isCollidable = isCollidable;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean isCollidable() {
        return isCollidable;
    }

    public void setCollidable(boolean collidable) {
        isCollidable = collidable;
    }
}
