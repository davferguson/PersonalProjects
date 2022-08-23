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
