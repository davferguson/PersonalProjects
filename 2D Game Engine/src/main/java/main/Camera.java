package main;

import entity.Entity;

public class Camera {
    private int worldX;
    private int worldY;
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

    public Entity getFollowEntity() {
        return followEntity;
    }

    public void setFollowEntity(Entity followEntity) {
        this.followEntity = followEntity;
    }
}
