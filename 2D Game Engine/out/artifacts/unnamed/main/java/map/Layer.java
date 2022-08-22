package map;

import main.GamePanel;
import tile.Tile;

import java.io.*;
import java.util.Map;

public class Layer {
    private int maxWorldRow;
    private int maxWorldCol;
    private int[][] tileNumMatrix;
    private String filePath;
    private Tile[] tiles;
    private Tile[][] tileMatrix;
    private Map<Integer, Tile> availableTiles;

    public Layer(String filePath, Map<Integer, Tile> availableTiles){
        this.filePath = filePath;
        this.availableTiles = availableTiles;
        loadLayer(filePath);
        tileMatrix = generateTileMatrix();
    }

    private Tile[][] generateTileMatrix(){
        int maxRows = maxWorldRow;
        int maxCols = maxWorldCol;
        Tile[][] tileMatrix = new Tile[maxRows][maxCols];

        for(int ir = 0; ir < maxRows; ir++){
            for(int ic = 0; ic < maxCols; ic++){
                int curTileNumber = tileNumMatrix[ir][ic];
                if(curTileNumber != GamePanel.EMPTY_TILE){
                    tileMatrix[ir][ic] = availableTiles.get(curTileNumber);
                } else{
                    tileMatrix[ir][ic] = null;
                }

            }
        }
        return tileMatrix;
    }

    private void loadLayer(String filePath){
        try{
            File initialFile = new File(filePath);
            InputStream is = new FileInputStream(initialFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            //Find number of tiles in a col
            String firstLine = br.readLine();
            String[] lineLength = firstLine.split(" ");
            maxWorldCol = lineLength.length;

            //Find number of tiles in a row
            maxWorldRow = 1;
            while(br.readLine() != null){
                maxWorldRow++;
            }
            br.close();

            tileNumMatrix = new int[maxWorldRow][maxWorldCol];

            is = new FileInputStream(initialFile);
            br = new BufferedReader(new InputStreamReader(is));

            for(int row = 0; row < maxWorldRow; row++){
                String line = br.readLine();
                String[] numbers = line.split(" ");
                for(int col = 0; col < maxWorldCol; col++){
                    int num = Integer.parseInt(numbers[col]);
                    tileNumMatrix[row][col] = num;
                }
            }
            br.close();
        } catch(Exception ignored){

        }
    }

    public Tile[][] getTileMatrix() {
        return tileMatrix;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int[][] getTileNumMatrix() {
        return tileNumMatrix;
    }
}
