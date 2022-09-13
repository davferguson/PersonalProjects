package game_map;

import main.GamePanel;

import java.awt.image.BufferedImage;
import java.io.*;

public class GameMapGeneration {

    private int numTilesInWorldCol;
    private int numTilesInWorldRow;

    public int[][] GenerateTileIndexMatrix(String filePath){
        int[][] tileIndexMatrix = null;
        try{
            File initialFile = new File(filePath);
            InputStream is = new FileInputStream(initialFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            numTilesInWorldCol = getNumOfColFromBufferedReader(br);
            numTilesInWorldRow = getNumOfRowFromBufferedReader(br);
            tileIndexMatrix = PopulateTileIndexMatrix(filePath);
            br.close();
        } catch (FileNotFoundException e){
            System.out.println("File at: " + filePath + " was not found.    " + e.getMessage());
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        return tileIndexMatrix;
    }

    private int[][] PopulateTileIndexMatrix(String filePath){
        int[][] tileIndexMatrix = new int[numTilesInWorldRow][numTilesInWorldCol];

        try{
            File initialFile = new File(filePath);
            InputStream is = new FileInputStream(initialFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for(int y = 0; y < numTilesInWorldRow; y++){
                String curLine = br.readLine();
                String[] curLineTileIndexes = curLine.split(GamePanel.MAP_FILE_TILE_DELIMITER);
                for(int x = 0; x < numTilesInWorldCol; x++){
                    try{
                        tileIndexMatrix[y][x] = Integer.parseInt(curLineTileIndexes[x]);
                    } catch (NumberFormatException e){
                        System.out.println("error populating tile matrix: map file must contain only integers");
                        System.out.println(e.getMessage());
                        tileIndexMatrix[y][x] = -1;
                    }

                }
            }

            br.close();
        } catch (FileNotFoundException e){
            System.out.println("File at: " + filePath + " was not found.    " + e.getMessage());
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println(tileIndexMatrix);
        return tileIndexMatrix;
    }

    private int getNumOfColFromBufferedReader(BufferedReader br){
        try{
            String firstLine = br.readLine();
            String[] lineLength = firstLine.split(GamePanel.MAP_FILE_TILE_DELIMITER);
            return lineLength.length;
        } catch(IOException e){
            System.out.println("error reading test: " + e.getMessage());
        }
        return -1;
    }

    private int getNumOfRowFromBufferedReader(BufferedReader br){
        int lineNum = 1; //FIRST LINE ALREADY READ WHEN FINDING NUM OF TILES IN A COLUMN
        try{
            while(br.readLine() != null){
                lineNum++;
            }
            return lineNum;
        } catch(IOException e){
            System.out.println("error reading file: " + e.getMessage());
        }
        return -1;
    }
}
