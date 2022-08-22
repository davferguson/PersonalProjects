package main;

import entity.Player;
import map.Layer;
import map.TileMap;
import map.TileRenderer;
import tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class CollisionHandler {
    private GamePanel gp;
    private List<Integer> collidableTiles = new ArrayList<>();
    private int[] colideTileIndex = new int[]{35, 45, 17, 18, 19, 27, 28, 29, 37, 38, 39, 47, 48, 49, 24, 26, 25};

    public CollisionHandler(GamePanel gp){
        this.gp = gp;
        generateCollidableTiles();
    }


    public boolean hasCollidedWithTile(int newWorldX, int newWorldY){
        TileMap tileMap = gp.getTileRenderer().getTileMap();
        boolean isColliding = false;
        for(Layer curLayer :tileMap.getLayers()){
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
