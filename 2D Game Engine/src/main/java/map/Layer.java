package map;

import main.GamePanel;
import tile.Tile;
import java.io.*;
import java.util.Map;

public class Layer {
    private int maxWorldRow;
    private int maxWorldCol;
    private int[][] tileNumMatrix;
    private Tile[][] tileMatrix;
    private Map<Integer, Tile> availableTiles;

    public Layer(String filePath, Map<Integer, Tile> availableTiles){
        this.availableTiles = availableTiles;
        loadLayer(filePath);
        tileMatrix = generateTileMatrix();
    }

    private void loadLayer(String filePath){
        initializeMaxWorldColAndRow(filePath);
        populateTileNumMatrix(filePath);
    }

    private int getNumOfColFromText(BufferedReader br){
        try{
            String firstLine = br.readLine();
            String[] lineLength = firstLine.split(" ");
            return lineLength.length;
        } catch(IOException e){
            System.out.println("error reading test: " + e.getMessage());
        }
        return -1;
    }

    private int getNumOfRowFromText(BufferedReader br){
        int lineNum = 1;
        try{
            while(br.readLine() != null){
                lineNum++;
            }
            return lineNum;
        } catch(IOException e){
            System.out.println("error reading test: " + e.getMessage());
        }
        return -1;
    }

    private void initializeMaxWorldColAndRow(String filePath){
        try{
            File initialFile = new File(filePath);
            InputStream is = new FileInputStream(initialFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            maxWorldCol = getNumOfColFromText(br);
            maxWorldRow = getNumOfRowFromText(br);
            tileNumMatrix = new int[maxWorldRow][maxWorldCol];
            br.close();
        } catch (FileNotFoundException e){
            System.out.println("error initializing: " + e.getMessage());
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private Tile[][] generateTileMatrix(){
        Tile[][] tileMatrix = new Tile[maxWorldRow][maxWorldRow];
        for(int ir = 0; ir < maxWorldRow; ir++){
            for(int ic = 0; ic < maxWorldRow; ic++){
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

    private void populateTileNumMatrix(String filePath){
        try{
            InputStream is = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for(int row = 0; row < maxWorldRow; row++){
                String line = br.readLine();
                String[] numbers = line.split(" ");
                for(int col = 0; col < maxWorldCol; col++){
                    int num = Integer.parseInt(numbers[col]);
                    tileNumMatrix[row][col] = num;
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
