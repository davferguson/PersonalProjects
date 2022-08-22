package main;

import entity.Player;
import map.TileRenderer;
import ui.UI;

import java.awt.*;
import java.text.DecimalFormat;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    public static final int ORIGINAL_PLAYER_SIZE = 16;
    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int SCALE = 3;

    public static final int SCALED_PLAYER_SIZE = ORIGINAL_PLAYER_SIZE * SCALE;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int SCALE_WIDTH = 2;
    public static final double SCALE_HEIGHT = 1.5;
    public static final int MAX_SCREEN_COL = (int)((SCREEN_SIZE.getWidth()/SCALE_WIDTH) / TILE_SIZE); //32
    public static final int MAX_SCREEN_ROW = (int)((SCREEN_SIZE.getHeight()/SCALE_HEIGHT) / TILE_SIZE); //18

    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    public static int MAP_WIDTH = 0;
    public static int MAP_HEIGHT = 0;

//    public static final int EMPTY_TILE = -1;
    public static final int EMPTY_TILE = 0;


    //FPS
    public final int FPS = 60;

    private KeyHandler keyHandler = new KeyHandler();
    private MouseHandler mouseHandler = new MouseHandler();
    private Thread gameThread;
    private Player player = new Player(this, keyHandler);
    private Camera mainCamera = new Camera(0, 0, player);
    private TileRenderer tileRenderer = new TileRenderer(this);
    private UI ui = new UI(this);

    private double drawStart = 0.0;
    private double drawEnd = 0.0;
    private DecimalFormat dfZero = new DecimalFormat("0.00");

    public static GameState gameState = GameState.TITLE_SCREEN;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
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

                if(gameState == GameState.EXITING){
                    //updatePlayerPosition();
                    System.exit(0);
                }

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

    public void update(){
        if(gameState == GameState.PLAYING){
            player.update();
            mainCamera.update();
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //DEBUG
        drawStart = System.nanoTime();
        //DEBUG END

        //DRAW
        if(gameState.equals(GameState.PLAYING) || gameState.equals(GameState.PAUSED)){
            tileRenderer.draw(g2);
            player.draw(g2);
        }
        ui.draw(g2);
        //END DRAW

        //DEBUG
        drawEnd = System.nanoTime();
        //DEBUG END

        g2.dispose();
    }


    public Player getPlayer(){
        return this.player;
    }

    public TileRenderer getTileRenderer() {
        return tileRenderer;
    }


    public MouseHandler getMouseHandler() {
        return mouseHandler;
    }

    public Camera getMainCamera() {
        return mainCamera;
    }
}
