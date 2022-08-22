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
import java.util.List;
import java.util.Map;

public class TileRenderer {

    private GamePanel gp;
    private Tile[] tiles;
    private Player player;
    private final int TILE_RENDER_DISTANCE_X = GamePanel.MAX_SCREEN_COL/2;
    private final int TILE_RENDER_DISTANCE_Y = GamePanel.MAX_SCREEN_ROW/2;
    private TileMap tileMap;
    private Map<Integer, Tile> availableTiles = new HashMap<>();

    public TileRenderer(GamePanel gp){
        this.gp = gp;
        this.player = gp.getPlayer();

        generateMapOfTiles();
        tileMap = new TileMap();
        tileMap.addLayer("src/main/resources/maps/test_map.txt", availableTiles);

//        setPlayerStartLocation();
    }

    private void generateMapOfTiles(){
        BufferedImage curTileImage;
        BufferedImage scaledImage;
        Tile tempTile;

        try{
            //GRASS01
            curTileImage = ImageIO.read(new FileInputStream("src/main/resources/tiles/Grass01_Tile.png"));
            scaledImage = UtilityTool.scaleImage(curTileImage, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
            tempTile = new Tile(scaledImage, false);
            availableTiles.put(1, tempTile);
            //END GRASS01

            //GRASS02
            curTileImage = ImageIO.read(new FileInputStream("src/main/resources/tiles/Grass02_Tile.png"));
            scaledImage = UtilityTool.scaleImage(curTileImage, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
            tempTile = new Tile(scaledImage, false);
            availableTiles.put(2, tempTile);
            //END GRASS02

            //GRASS_FLOWER
            curTileImage = ImageIO.read(new FileInputStream("src/main/resources/tiles/GrassFlower_Tile.png"));
            scaledImage = UtilityTool.scaleImage(curTileImage, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
            tempTile = new Tile(scaledImage, false);
            availableTiles.put(3, tempTile);
            //END GRASS_FLOWER

            //GRASS_WEED
            curTileImage = ImageIO.read(new FileInputStream("src/main/resources/tiles/GrassWeed_Tile.png"));
            scaledImage = UtilityTool.scaleImage(curTileImage, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
            tempTile = new Tile(scaledImage, false);
            availableTiles.put(4, tempTile);
            //END GRASS_WEED
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2){
        for(Layer curLayer : tileMap.getLayers()){
            Tile[][] curTileMatrix = curLayer.getTileMatrix();

            int curPlayerRow = (player.getWorldY() + GamePanel.SCALED_PLAYER_SIZE/2) / GamePanel.TILE_SIZE;
            int curPlayerCol = (player.getWorldX() + GamePanel.SCALED_PLAYER_SIZE/2) / GamePanel.TILE_SIZE;
            for(int curRow = curPlayerRow - TILE_RENDER_DISTANCE_Y; curRow < curLayer.getMaxWorldRow() && curRow <= curPlayerRow + TILE_RENDER_DISTANCE_Y; curRow++){
                curRow = Math.max(curRow, 0);
                for(int curCol = curPlayerCol - TILE_RENDER_DISTANCE_X; curCol < curLayer.getMaxWorldCol() && curCol <= curPlayerCol + TILE_RENDER_DISTANCE_X; curCol++){
                    curCol = Math.max(curCol, 0);
                    if(curTileMatrix[curRow][curCol] != null){
                        int curTileWorldXPos = curCol * GamePanel.TILE_SIZE;
                        int curTileWorldYPos = curRow * GamePanel.TILE_SIZE;

                        int curTileScreenXPos = curTileWorldXPos - player.getWorldX() + player.getScreenX();
                        int curTileScreenYPos = curTileWorldYPos - player.getWorldY() + player.getScreenY();

                        g2.drawImage(curTileMatrix[curRow][curCol].getImage(), curTileScreenXPos, curTileScreenYPos, null);
                    }
                }
            }
        }
    }

//    private void setPlayerStartLocation(){
//        player.setWorldX((tileMap.getLayers().get(0).getMaxWorldCol() * GamePanel.TILE_SIZE)/2);
//        player.setWorldY((tileMap.getLayers().get(0).getMaxWorldRow() * GamePanel.TILE_SIZE) / 2);
//    }



    public TileMap getTileMap() {
        return tileMap;
    }

    public Tile[] spriteToTileset(String src){
        BufferedImage spriteSheet;
        //List<Integer> collidableTiles = gp.getCollisionHandler().getCollidableTiles();
        try{
            spriteSheet = ImageIO.read(new FileInputStream(src));
            int numOfSpriteSheetRows = spriteSheet.getHeight() / GamePanel.ORIGINAL_TILE_SIZE;
            int numOfSpriteSheetCols = spriteSheet.getWidth() / GamePanel.ORIGINAL_TILE_SIZE;
            Tile[] tileSet = new Tile[numOfSpriteSheetRows * numOfSpriteSheetCols];
            for(int i = 0; i < tileSet.length; i++){
                tileSet[i] = new Tile();
            }

            int index = 0;
            for(int row = 0; row*GamePanel.ORIGINAL_TILE_SIZE < spriteSheet.getHeight(); row++){
                for(int col = 0; col*GamePanel.ORIGINAL_TILE_SIZE < spriteSheet.getWidth(); col++){
                    BufferedImage originalImage = spriteSheet.getSubimage(col * GamePanel.ORIGINAL_TILE_SIZE, row * GamePanel.ORIGINAL_TILE_SIZE, GamePanel.ORIGINAL_TILE_SIZE, GamePanel.ORIGINAL_TILE_SIZE);
                    BufferedImage scaledImage = UtilityTool.scaleImage(originalImage, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
                    tileSet[index].setImage(scaledImage);
//                    if(collidableTiles.contains(index)){
//                        tileSet[index].setCollidable(true);
//                    }

                    index++;
                }
            }
            return tileSet;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
