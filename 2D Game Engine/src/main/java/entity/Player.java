package entity;

import main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player extends Entity{

    private GamePanel gamePanel;
    private KeyHandler keyHandler;
    private int screenX;
    private int screenY;
    private int screenMiddleX, screenMiddleY;

    private int spriteIndex = 0;
    private int spriteCounter = 0;
    private int animationSpeed = 8;
    private int animationFrames = 3;
    private BufferedImage[] upAnimation = new BufferedImage[animationFrames];
    private BufferedImage[] downAnimation = new BufferedImage[animationFrames];
    private BufferedImage[] leftAnimation = new BufferedImage[animationFrames];
    private BufferedImage[] rightAnimation = new BufferedImage[animationFrames];
    private List<BufferedImage[]> playerAnimations = new ArrayList<>();
    private CollisionHandler collisionHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenMiddleX = GamePanel.SCREEN_WIDTH / 2 - (GamePanel.TILE_SIZE /2);
        screenMiddleY = GamePanel.SCREEN_HEIGHT / 2 - (GamePanel.TILE_SIZE /2);
        screenX = screenMiddleX;
        screenY = screenMiddleY;

        playerAnimations.add(upAnimation);
        playerAnimations.add(downAnimation);
        playerAnimations.add(leftAnimation);
        playerAnimations.add(rightAnimation);

        setDefaultValues();
        getPlayerImage();
    }


    public void setDefaultValues(){
        setWorldX(500);
        setWorldY(500);
        setSpeed(4);
        setDirection("down");
        setBounds(new Rectangle(getWorldX(), getWorldY(), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
    }

    public void getPlayerImage(){
        try{
            setSpriteSheet(ImageIO.read(new FileInputStream("resources/player/player_sheet.png")));
            BufferedImage spriteSheet = getSpriteSheet();
            upAnimation[0] = spriteSheet.getSubimage(0, GamePanel.ORIGINAL_PLAYER_SIZE * 3, GamePanel.ORIGINAL_PLAYER_SIZE, GamePanel.ORIGINAL_PLAYER_SIZE);
            upAnimation[1] = spriteSheet.getSubimage(GamePanel.ORIGINAL_PLAYER_SIZE, GamePanel.ORIGINAL_PLAYER_SIZE * 3, GamePanel.ORIGINAL_PLAYER_SIZE, GamePanel.ORIGINAL_PLAYER_SIZE);
            upAnimation[2] = spriteSheet.getSubimage(GamePanel.ORIGINAL_PLAYER_SIZE * 2, GamePanel.ORIGINAL_PLAYER_SIZE * 3, GamePanel.ORIGINAL_PLAYER_SIZE, GamePanel.ORIGINAL_PLAYER_SIZE);

            downAnimation[0] = spriteSheet.getSubimage(0, 0, GamePanel.ORIGINAL_PLAYER_SIZE, GamePanel.ORIGINAL_PLAYER_SIZE);
            downAnimation[1] = spriteSheet.getSubimage(GamePanel.ORIGINAL_PLAYER_SIZE, 0, GamePanel.ORIGINAL_PLAYER_SIZE, GamePanel.ORIGINAL_PLAYER_SIZE);
            downAnimation[2] = spriteSheet.getSubimage(GamePanel.ORIGINAL_PLAYER_SIZE * 2, 0, GamePanel.ORIGINAL_PLAYER_SIZE, GamePanel.ORIGINAL_PLAYER_SIZE);

            leftAnimation[0] = spriteSheet.getSubimage(0, GamePanel.ORIGINAL_PLAYER_SIZE, GamePanel.ORIGINAL_PLAYER_SIZE, GamePanel.ORIGINAL_PLAYER_SIZE);
            leftAnimation[1] = spriteSheet.getSubimage(GamePanel.ORIGINAL_PLAYER_SIZE, GamePanel.ORIGINAL_PLAYER_SIZE, GamePanel.ORIGINAL_PLAYER_SIZE, GamePanel.ORIGINAL_PLAYER_SIZE);
            leftAnimation[2] = spriteSheet.getSubimage(GamePanel.ORIGINAL_PLAYER_SIZE * 2, GamePanel.ORIGINAL_PLAYER_SIZE, GamePanel.ORIGINAL_PLAYER_SIZE, GamePanel.ORIGINAL_PLAYER_SIZE);

            rightAnimation[0] = spriteSheet.getSubimage(0, GamePanel.ORIGINAL_PLAYER_SIZE * 2, GamePanel.ORIGINAL_PLAYER_SIZE, GamePanel.ORIGINAL_PLAYER_SIZE);
            rightAnimation[1] = spriteSheet.getSubimage(GamePanel.ORIGINAL_PLAYER_SIZE, GamePanel.ORIGINAL_PLAYER_SIZE * 2, GamePanel.ORIGINAL_PLAYER_SIZE, GamePanel.ORIGINAL_PLAYER_SIZE);
            rightAnimation[2] = spriteSheet.getSubimage(GamePanel.ORIGINAL_PLAYER_SIZE * 2, GamePanel.ORIGINAL_PLAYER_SIZE * 2, GamePanel.ORIGINAL_PLAYER_SIZE, GamePanel.ORIGINAL_PLAYER_SIZE);

            for(int index = 0; index < playerAnimations.size(); index++){
                BufferedImage[] animation = playerAnimations.get(index);
                for(int i = 0; i < animation.length; i++){
                    BufferedImage originalImage = animation[i];
                    BufferedImage scaledImage = UtilityTool.scaleImage(originalImage, GamePanel.SCALED_PLAYER_SIZE, GamePanel.SCALED_PLAYER_SIZE);
                    animation[i] = scaledImage;
                }
            }

        }catch(IOException e){
            e.getMessage();
        }
    }

    public void update(){
        if(collisionHandler == null){
            collisionHandler = gamePanel.getCollisionHandler();
        }
        if(keyHandler.isDownPressed() || keyHandler.isLeftPressed() || keyHandler.isRightPressed() || keyHandler.isUpPressed()){
            if(keyHandler.isUpPressed()){

                if(!collisionHandler.isColliding(getWorldX(), getWorldY()-getSpeed())){
                    setWorldY(getWorldY()-getSpeed());
                    if(getWorldY()-GamePanel.SCREEN_HEIGHT/2 < 0){
                        screenY -= getSpeed();
                    }
                    if(screenY > screenMiddleY){
                        screenY -= getSpeed();
                    }
                }
                setDirection("up");
            } else if(keyHandler.isDownPressed()){

                if(!collisionHandler.isColliding(getWorldX(), getWorldY()+getSpeed())){
                    setWorldY(getWorldY()+getSpeed());
                    if(getWorldY()+GamePanel.SCREEN_HEIGHT/2 > GamePanel.MAP_HEIGHT){
                        screenY += getSpeed();
                    }
                    if(screenY < screenMiddleY){
                        screenY += getSpeed();
                    }
                }
                setDirection("down");
            } else if(keyHandler.isLeftPressed()){

                if(!collisionHandler.isColliding(getWorldX()-getSpeed(), getWorldY())){
                    setWorldX(getWorldX()-getSpeed());
                    if(getWorldX()-GamePanel.SCREEN_WIDTH/2 < 0){
                        screenX -= getSpeed();
                    }
                    if(screenX > screenMiddleX){
                        screenX -= getSpeed();
                    }
//                    if(getWorldX()-GamePanel.SCREEN_WIDTH/2 < 0){
//                        screenX -= getSpeed();
//                    }
                }
                setDirection("left");
            } else {
                if(!collisionHandler.isColliding(getWorldX()+getSpeed(), getWorldY())) {
                    setWorldX(getWorldX() + getSpeed());
                    if(getWorldX()+GamePanel.SCREEN_WIDTH/2 > GamePanel.MAP_WIDTH){
                        screenX += getSpeed();
                    }
                    if(screenX < screenMiddleX){
                        screenX += getSpeed();
                    }
//                    if(getWorldX()+GamePanel.SCREEN_WIDTH/2 > GamePanel.MAP_WIDTH){
//                        screenX += getSpeed();
//                    }
                }
                setDirection("right");
            }
            setBounds(new Rectangle(getWorldX(), getWorldY(), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));

            spriteCounter++;
            if(spriteCounter > animationSpeed){
                spriteIndex++;
                if(spriteIndex >= animationFrames){
                    spriteIndex = 0;
                }
                spriteCounter = 0;
            }
        } else{
            spriteIndex = 1;
        }

    }

    public void draw(Graphics2D g2){
        BufferedImage image = downAnimation[spriteIndex];
        switch(getDirection()){
            case "up":
                image = upAnimation[spriteIndex];
                break;
            case "down":
                image = downAnimation[spriteIndex];
                break;
            case "left":
                image = leftAnimation[spriteIndex];
                break;
            case "right":
                image = rightAnimation[spriteIndex];
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
        g2.drawString("world x-pos: " + getWorldX(), 10, 10);
        g2.drawString("world y-pos: " + getWorldY(), 10, 20);
        g2.drawString("screen x-pos: " + screenX, 10, 30);
        g2.drawString("screen y-pos: " + screenY, 10, 40);

    }
    public void drawDebug(Graphics2D g2){
        g2.setColor(Color.RED);
        g2.drawRect(screenX, screenY, GamePanel.SCALED_PLAYER_SIZE, GamePanel.SCALED_PLAYER_SIZE);
    }

    public int getScreenX() {
        return screenX;
    }

    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }
}
