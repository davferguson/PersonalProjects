package map;

import entity.Player;
import main.GamePanel;
import main.UtilityTool;
import tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TileRenderer {

    private Player player;
    private final int TILE_RENDER_DISTANCE_X = GamePanel.MAX_SCREEN_COL/2;
    private final int TILE_RENDER_DISTANCE_Y = GamePanel.MAX_SCREEN_ROW/2;
    private WorldLayers worldLayers;
    private Map<Integer, Tile> availableTiles;

    public TileRenderer(GamePanel gp){
        this.player = gp.getPlayer();

        availableTiles = generateMapOfTiles();

        String[] filePaths = new String[]{"resources/maps/test_map.txt"};
        worldLayers = generateWorldLayers(filePaths, availableTiles);
    }

    private WorldLayers generateWorldLayers(String[] filePaths, Map<Integer, Tile> availableTiles){
        WorldLayers worldLayers = new WorldLayers();
        for(String curFilePath : filePaths){
            worldLayers.addLayer(curFilePath, availableTiles);
        }
        return worldLayers;
    }

    private Map<Integer, Tile> generateMapOfTiles(){
        Map<Integer, Tile> tiles = new HashMap<>();
        BufferedImage curTileImage;
        BufferedImage scaledImage;
        Tile tempTile;

        try{
            //GRASS01
            curTileImage = ImageIO.read(new FileInputStream("resources/tiles/Grass01_Tile.png"));
            scaledImage = UtilityTool.scaleImage(curTileImage, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
            tempTile = new Tile(scaledImage, false);
            tiles.put(1, tempTile);
            //END GRASS01

            //GRASS02
            curTileImage = ImageIO.read(new FileInputStream("resources/tiles/Grass02_Tile.png"));
            scaledImage = UtilityTool.scaleImage(curTileImage, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
            tempTile = new Tile(scaledImage, false);
            tiles.put(2, tempTile);
            //END GRASS02

            //GRASS_FLOWER
            curTileImage = ImageIO.read(new FileInputStream("resources/tiles/GrassFlower_Tile.png"));
            scaledImage = UtilityTool.scaleImage(curTileImage, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
            tempTile = new Tile(scaledImage, false);
            tiles.put(3, tempTile);
            //END GRASS_FLOWER

            //GRASS_WEED
            curTileImage = ImageIO.read(new FileInputStream("resources/tiles/GrassWeed_Tile.png"));
            scaledImage = UtilityTool.scaleImage(curTileImage, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
            tempTile = new Tile(scaledImage, false);
            tiles.put(4, tempTile);
            //END GRASS_WEED
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return tiles;
    }

    public void draw(Graphics2D g2){
        for(Layer curLayer : worldLayers.getLayers()){
            Tile[][] curTileMatrix = curLayer.getTileMatrix();

            int rowPlayerIsCurrentlyIn = (player.getWorldY() + GamePanel.SCALED_PLAYER_SIZE/2) / GamePanel.TILE_SIZE;
            int colPlayerIsCurrentlyIn = (player.getWorldX() + GamePanel.SCALED_PLAYER_SIZE/2) / GamePanel.TILE_SIZE;
            for(int rowIndex = rowPlayerIsCurrentlyIn - TILE_RENDER_DISTANCE_Y; rowIndex < curLayer.getMaxWorldRow() && rowIndex <= rowPlayerIsCurrentlyIn + TILE_RENDER_DISTANCE_Y; rowIndex++){
                rowIndex = Math.max(rowIndex, 0);
                for(int colIndex = colPlayerIsCurrentlyIn - TILE_RENDER_DISTANCE_X; colIndex < curLayer.getMaxWorldCol() && colIndex <= colPlayerIsCurrentlyIn + TILE_RENDER_DISTANCE_X; colIndex++){
                    colIndex = Math.max(colIndex, 0);
                    if(curTileMatrix[rowIndex][colIndex] != null){
                        int curTileWorldXPos = colIndex * GamePanel.TILE_SIZE;
                        int curTileWorldYPos = rowIndex * GamePanel.TILE_SIZE;

                        int curTileScreenXPos = curTileWorldXPos - player.getWorldX() + player.getScreenX();
                        int curTileScreenYPos = curTileWorldYPos - player.getWorldY() + player.getScreenY();

                        g2.drawImage(curTileMatrix[rowIndex][colIndex].getImage(), curTileScreenXPos, curTileScreenYPos, null);
                    }
                }
            }
        }
    }


    public WorldLayers getTileMap() {
        return worldLayers;
    }

}
