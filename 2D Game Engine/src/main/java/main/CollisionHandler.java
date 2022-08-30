package main;

import entity.Entity;
import game_object.GameObject;
import map.Layer;
import map.WorldLayers;
import tile.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CollisionHandler {
    private GamePanel gp;
    private List<Integer> collidableTiles = new ArrayList<>();
    private int[] colideTileIndex = new int[]{35, 45, 17, 18, 19, 27, 28, 29, 37, 38, 39, 47, 48, 49, 24, 26, 25};

    private Rectangle testRect;
    private List<Rectangle> collisionBoxes = new ArrayList<>();

    public CollisionHandler(GamePanel gp){
        this.gp = gp;
        testRect = new Rectangle(100,100, GamePanel.TILE_SIZE, 1000);
//        generateCollidableTiles();
//        generateCollisionBoxes();
        generateWorldBorders();
    }
    public boolean isColliding(int x, int y){
        if(collisionBoxes != null){
            for(int i = 0; i < collisionBoxes.size(); i++){
                if(collisionBoxes.get(i).contains(x, y)){
                    return true;
                }
            }
        }
        return false;
    }

    public void addObjectCollisions(List<GameObject> objects){
        for(GameObject object : objects){
            collisionBoxes.add(object.getBounds());
        }
    }

    public void generateCollisionBoxes(){
        List<Rectangle> collisionBoxes = new ArrayList<>();

        WorldLayers worldLayers = gp.getTileRenderer().getWorldLayers();
        Layer collisionLayer = worldLayers.getLayers().get(1);
        Tile[][] tileMatrix = collisionLayer.getTileMatrix();

        for(int y = 0; y < collisionLayer.getMaxWorldRow(); y++){
            for(int x = 0; x < collisionLayer.getMaxWorldCol(); x++){
                if(tileMatrix[y][x] != null){
                    collisionBoxes.add(new Rectangle((x*GamePanel.TILE_SIZE), (y*GamePanel.TILE_SIZE), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
                }
            }
        }

        this.collisionBoxes = collisionBoxes;
    }

    public void generateWorldBorders(){
        collisionBoxes.add(new Rectangle(GamePanel.MAP_WIDTH-GamePanel.TILE_SIZE/2, 0, GamePanel.TILE_SIZE, GamePanel.MAP_HEIGHT));
        collisionBoxes.add(new Rectangle(-GamePanel.TILE_SIZE / 2, 0, GamePanel.TILE_SIZE, GamePanel.MAP_HEIGHT));
        collisionBoxes.add(new Rectangle(0, -GamePanel.TILE_SIZE / 2, GamePanel.MAP_WIDTH, GamePanel.TILE_SIZE));
        collisionBoxes.add(new Rectangle(0, GamePanel.MAP_HEIGHT-GamePanel.TILE_SIZE / 2, GamePanel.MAP_WIDTH, GamePanel.TILE_SIZE));
    }

    public boolean hasCollidedWithTile(int newWorldX, int newWorldY){
        WorldLayers worldLayers = gp.getTileRenderer().getWorldLayers();
        boolean isColliding = false;
        for(Layer curLayer : worldLayers.getLayers()){
            Tile[][] curTileMatrix;
            curTileMatrix = curLayer.getTileMatrix();
            int curPlayerRow = (int) Math.round((newWorldY + GamePanel.SCALED_PLAYER_SIZE/2) / (double)GamePanel.TILE_SIZE);
            //int curPlayerRow = (newWorldY + GamePanel.SCALED_PLAYER_SIZE/2) / GamePanel.TILE_SIZE;
            int curPlayerCol = (newWorldX + GamePanel.SCALED_PLAYER_SIZE/2) / GamePanel.TILE_SIZE;

            if(curPlayerRow >= 1 && curPlayerRow < curLayer.getMaxWorldRow()-1){
                if(curPlayerCol >= 1 && curPlayerCol < curLayer.getMaxWorldCol()-1){
                    if(curTileMatrix[curPlayerRow][curPlayerCol] != null){
                        isColliding = curTileMatrix[curPlayerRow][curPlayerCol].isCollidable();
                    }

                }
            }
        }
        return isColliding;
    }

    private void generateCollidableTiles(){
        for(int cur : colideTileIndex){
            collidableTiles.add(cur);
        }
    }

    public List<Integer> getCollidableTiles() {
        return collidableTiles;
    }
}
