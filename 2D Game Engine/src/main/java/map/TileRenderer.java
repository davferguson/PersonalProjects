package map;

import entity.Player;
import main.Camera;
import main.GamePanel;
import main.UtilityTool;
import tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TileRenderer {

    private Player player;
    private Camera mainCamera;
    private final int TILE_RENDER_DISTANCE_X = GamePanel.MAX_SCREEN_COL/2;
    private final int TILE_RENDER_DISTANCE_Y = GamePanel.MAX_SCREEN_ROW/2;
    private WorldLayers worldLayers;
    private Map<Integer, Tile> availableTiles;

    private BufferedImage mapImage;

    public TileRenderer(GamePanel gp){
        this.player = gp.getPlayer();
        this.mainCamera = gp.getMainCamera();

        Map<Integer, Tile> tileset = new HashMap<>();
        try {
            BufferedImage curTileset = ImageIO.read(new FileInputStream("resources/tiles/tileSpritesheet.png"));
            tileset = generateTileset(curTileset, 16);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        String[] filePaths = new String[]{"resources/maps/Tiled_Map.txt", "resources/maps/Tiled_Map01.txt"};
        worldLayers = generateWorldLayers(filePaths, tileset);

        List<Layer> layers = worldLayers.getLayers();
        GamePanel.MAP_WIDTH = layers.get(0).getMaxWorldCol() * GamePanel.TILE_SIZE;
        GamePanel.MAP_HEIGHT = layers.get(0).getMaxWorldRow() * GamePanel.TILE_SIZE;

        mapImage = createMapImage(tileset);

//        availableTiles = generateMapOfTiles();
//
//        String[] filePaths = new String[]{"resources/maps/test_map.txt"};
//        worldLayers = generateWorldLayers(filePaths, availableTiles);
    }

    private WorldLayers generateWorldLayers(String[] filePaths, Map<Integer, Tile> availableTiles){
        WorldLayers worldLayers = new WorldLayers();
        for(String curFilePath : filePaths){
            worldLayers.addLayer(curFilePath, availableTiles);
        }
        return worldLayers;
    }

    private BufferedImage createMapImage(Map<Integer, Tile> tileset){
        List<Layer> layers = worldLayers.getLayers();
        int imageWidth = layers.get(0).getMaxWorldCol() * GamePanel.TILE_SIZE;
        int imageHeight = layers.get(0).getMaxWorldRow() * GamePanel.TILE_SIZE;
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        for(Layer layer : worldLayers.getLayers()){
            Tile[][] tileMatrix = layer.getTileMatrix();

            for(int y = 0; y < layer.getMaxWorldRow(); y++){
                for(int x = 0; x < layer.getMaxWorldCol(); x++){
                    if(tileMatrix[y][x] != null){
                        g2d.drawImage(tileMatrix[y][x].getImage(),
                                x * GamePanel.TILE_SIZE,
                                y * GamePanel.TILE_SIZE, null);
                    }
                }
            }

        }

        try{
            File file = new File("resources/maps/mapimage.png");
            ImageIO.write(image, "png", file);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return image;
    }

    private Map<Integer, Tile> generateTileset(BufferedImage tileset, int tileSize){
        Map<Integer, Tile> generatedTileset = new HashMap<>();
        int imageWidth = 0;
        int imageHeight = 0;
        try {
            imageWidth = tileset.getWidth();
            imageHeight = tileset.getHeight();
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        BufferedImage curTileImage;
        BufferedImage scaledImage;
        int index = 1;
        for(int y = 0; y * tileSize < imageHeight; y++){
            for(int x = 0; x * tileSize < imageWidth; x++){
                curTileImage = tileset.getSubimage(x*tileSize, y*tileSize, tileSize, tileSize);
                scaledImage = UtilityTool.scaleImage(curTileImage, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
                Tile tempTile = new Tile(scaledImage, false);
                generatedTileset.put(index, tempTile);
                index++;
            }
        }

    return generatedTileset;
    }

    public void draw(Graphics2D g2){
        int curTileScreenXPos = -mainCamera.getWorldX() + GamePanel.SCREEN_WIDTH/2;
        int curTileScreenYPos = -mainCamera.getWorldY() + GamePanel.SCREEN_HEIGHT/2;
        g2.drawImage(mapImage, curTileScreenXPos, curTileScreenYPos, null);
    }

//    public void draw(Graphics2D g2){
//        for(Layer curLayer : worldLayers.getLayers()){
//            Tile[][] curTileMatrix = curLayer.getTileMatrix();
//
//            int rowPlayerIsCurrentlyIn = (player.getWorldY() + GamePanel.SCALED_PLAYER_SIZE/2) / GamePanel.TILE_SIZE;
//            int colPlayerIsCurrentlyIn = (player.getWorldX() + GamePanel.SCALED_PLAYER_SIZE/2) / GamePanel.TILE_SIZE;
//            for(int rowIndex = rowPlayerIsCurrentlyIn - TILE_RENDER_DISTANCE_Y; rowIndex < curLayer.getMaxWorldRow() && rowIndex <= rowPlayerIsCurrentlyIn + TILE_RENDER_DISTANCE_Y; rowIndex++){
//                rowIndex = Math.max(rowIndex, 0);
//                for(int colIndex = colPlayerIsCurrentlyIn - TILE_RENDER_DISTANCE_X; colIndex < curLayer.getMaxWorldCol() && colIndex <= colPlayerIsCurrentlyIn + TILE_RENDER_DISTANCE_X; colIndex++){
//                    colIndex = Math.max(colIndex, 0);
//                    if(curTileMatrix[rowIndex][colIndex] != null){
//                        int curTileWorldXPos = colIndex * GamePanel.TILE_SIZE;
//                        int curTileWorldYPos = rowIndex * GamePanel.TILE_SIZE;
//
//                        int curTileScreenXPos = curTileWorldXPos - player.getWorldX() + player.getScreenX();
//                        int curTileScreenYPos = curTileWorldYPos - player.getWorldY() + player.getScreenY();
//
//                        g2.drawImage(curTileMatrix[rowIndex][colIndex].getImage(), curTileScreenXPos, curTileScreenYPos, null);
//                    }
//                }
//            }
//        }
//    }


    public WorldLayers getWorldLayers() {
        return worldLayers;
    }

}
