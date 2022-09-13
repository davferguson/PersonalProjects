package player;

import game_object.TestObject;
import input.KeyboardInputHandler;
import main.GamePanel;
import tool.UtilityTool;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Player {
    private int worldXPos = 0;
    private int worldYPos = 0;
    private Rectangle collisionBounds;
    private BufferedImage playerImage = null;
    private int screenCenterX, screenCenterY;
    private int moveSpeed = GamePanel.MOVE_SPEED;

    public Player(){
        try{
            BufferedImage playerSpritesheet = ImageIO.read(new FileInputStream("src/main/resources/player/player_spritesheet.png"));
            playerImage = playerSpritesheet.getSubimage(GamePanel.ORIGINAL_TILE_SIZE, 0, GamePanel.ORIGINAL_TILE_SIZE, GamePanel.ORIGINAL_TILE_SIZE);
            playerImage = UtilityTool.scaleImage(playerImage, GamePanel.ORIGINAL_TILE_SIZE * GamePanel.SCALE, GamePanel.ORIGINAL_TILE_SIZE * GamePanel.SCALE);
        } catch(IOException e){
            System.out.println("error reading player spritesheet image: " + e.getMessage());
        }
        screenCenterX = GamePanel.SCREEN_WIDTH/2-((GamePanel.ORIGINAL_TILE_SIZE*GamePanel.SCALE)/2);
        screenCenterY = GamePanel.SCREEN_HEIGHT/2-((GamePanel.ORIGINAL_TILE_SIZE*GamePanel.SCALE)/2);
        collisionBounds = new Rectangle(screenCenterX, screenCenterY, GamePanel.ORIGINAL_TILE_SIZE*GamePanel.SCALE, GamePanel.ORIGINAL_TILE_SIZE*GamePanel.SCALE);
    }

    public void update(){
        if(KeyboardInputHandler.isRightPressed()){
            worldXPos += GamePanel.MOVE_SPEED;
        }
        if(KeyboardInputHandler.isLeftPressed()){
            worldXPos -= GamePanel.MOVE_SPEED;
        }
        if(!KeyboardInputHandler.isLeftPressed() && !KeyboardInputHandler.isRightPressed()){
            if(KeyboardInputHandler.isUpPressed()){
                worldYPos -= GamePanel.MOVE_SPEED;
            }
            if(KeyboardInputHandler.isDownPressed()){
                worldYPos += GamePanel.MOVE_SPEED;
            }
        }

    }

    public void draw(Graphics2D g2){
        g2.drawImage(playerImage, screenCenterX, screenCenterY, null);
        g2.setColor(Color.RED);
        Font font = new Font("Arial", 1, 20);
        g2.setFont(font);
        g2.drawString("player worldXPos: " + worldXPos, 10, 20);
        g2.drawString("player worldYPos: " + worldYPos, 10, 40);
        g2.drawRect(collisionBounds.x, collisionBounds.y, collisionBounds.width, collisionBounds.height);
    }

    public int getWorldXPos() {
        return worldXPos;
    }

    public int getWorldYPos() {
        return worldYPos;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public int getScreenCenterX() {
        return screenCenterX;
    }

    public int getScreenCenterY() {
        return screenCenterY;
    }
}
