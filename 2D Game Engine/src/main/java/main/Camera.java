package main;

import entity.Entity;

import java.util.Optional;

public class Camera {

    private int worldX = 0;
    private int worldY = 0;
    private Entity followEntity;

    public Camera (int worldX, int worldY, Entity followEntity){
        this.worldX = worldX;
        this.worldY = worldY;
        this.followEntity = followEntity;
    }

    public void update() {
        worldX = followEntity.getWorldX();
        worldY = followEntity.getWorldY();
        clampCamera();
    }

    private void clampCamera(){
        if(worldX - GamePanel.SCREEN_WIDTH/2 < 0){
            worldX = GamePanel.SCREEN_WIDTH/2;
        } else if(worldX + GamePanel.SCREEN_WIDTH/2 > GamePanel.MAP_WIDTH){
            worldX = GamePanel.MAP_WIDTH - GamePanel.SCREEN_WIDTH/2;
        }

        if(worldY - GamePanel.SCREEN_HEIGHT/2 < 0){
            worldY = GamePanel.SCREEN_WIDTH/2 - GamePanel.TILE_SIZE;
        }   else if(worldY + GamePanel.SCREEN_HEIGHT/2 > GamePanel.MAP_HEIGHT){
            worldY = GamePanel.MAP_HEIGHT - GamePanel.SCREEN_HEIGHT/2;
        }
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
}
