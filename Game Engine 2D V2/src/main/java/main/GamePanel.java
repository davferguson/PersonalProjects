package main;

import game_map.GameMapGeneration;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class GamePanel extends JPanel implements Runnable{
    public static final int ORIGINAL_TILE_SIZE = 16; //TILE SIZE IN PX
    public static final int SCALE = 3; //HOW MUCH TO SCALE UP
    public static final int NUM_TILES_WIDE = 10;
    public static final int NUM_TILES_HIGH = 8;
    public static final String MAP_FILE_TILE_DELIMITER = " ";

    public static final int SCREEN_WIDTH = ORIGINAL_TILE_SIZE * SCALE * NUM_TILES_WIDE;
    public static final int SCREEN_HEIGHT = ORIGINAL_TILE_SIZE * SCALE * NUM_TILES_HIGH;

    private Thread gameThread;
    private double drawStart = 0.0;
    private double drawEnd = 0.0;
    private DecimalFormat dfZero = new DecimalFormat("0.00");
    private final int FPS = 60;
    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        GameMapGeneration gameMapGeneration = new GameMapGeneration();
        gameMapGeneration.GenerateTileIndexMatrix("src/main/resources/map_data.txt");
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void update(){

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //DEBUG
        drawStart = System.nanoTime(); //SYSTEM TIME WHEN DRAWING BEGAN
        //DEBUG END

        //DRAW
            //PUT DRAW CODE HERE
        //END DRAW

        //DEBUG
        drawEnd = System.nanoTime(); //SYSTEM TIME WHEN DRAWING FINISHED
        //DEBUG END

        g2.dispose();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0/FPS;
        double delta = 0.0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();

//                if(gameState == GameState.EXITING){
//                    //updatePlayerPosition();
//                    System.exit(0);
//                }

                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("UPS: " + drawCount + "    MAX FPS: " + dfZero.format(1/((drawEnd - drawStart)/1000000000.0)));
                drawCount = 0;
                timer = 0;
            }
        }
    }
}
