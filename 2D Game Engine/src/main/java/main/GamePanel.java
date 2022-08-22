package main;

import database.control.DatabaseController;
import database.model.PlayerModel;
import database.model.PositionModel;
import entity.Player;
import game_object.GameObject;
import game_object.GameObjectHandler;
import game_object.GameObjectRenderer;
import map.TileRenderer;
import ui.UI;

import java.awt.*;
import java.text.DecimalFormat;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    public static final int PLAYER_SIZE = 16;
    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int SCALE = 3;

    public static final int SCALED_PLAYER_SIZE = PLAYER_SIZE * SCALE;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    public static final int MAX_SCREEN_COL = 32; //32
    public static final int MAX_SCREEN_ROW = 18; //18
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    public static final int EMPTY_TILE = -1;
    public static String USERNAME = "Dared5";
    public static String PASSWORD = "4565";


    //FPS
    public final int FPS = 60;

    private KeyHandler keyHandler = new KeyHandler();
    private MouseHandler mouseHandler = new MouseHandler();
    private Thread gameThread;
    private GameObjectHandler gameObjectHandler = new GameObjectHandler(this);
    private GameObjectRenderer gameObjectRenderer = new GameObjectRenderer(this);
    private Player player = new Player(this, keyHandler);
    private PlayerModel playerModel;
    private PositionModel positionModel;
    private TileRenderer tileRenderer = new TileRenderer(this);
    private UI ui = new UI(this);
//    private DatabaseController databaseController = new DatabaseController();

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

//        playerModel = databaseController.login(USERNAME, PASSWORD);
//        positionModel = databaseController.getCurrentPosition(playerModel);
//        player.setWorldX(positionModel.getxPos());
//        player.setWorldY(positionModel.getyPos());
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
            gameObjectRenderer.draw(g2);
//            for(GameObject curObject : gameObjectHandler.getGameObjects()){
//                if(curObject.getPostion().getY() < player.getWorldY()){
//                    curObject.draw(g2);
//                    player.draw(g2);
//                } else{
//                    player.draw(g2);
//                    curObject.draw(g2);
//                }
//            }
        }
        ui.draw(g2);
        //END DRAW

        //DEBUG
        drawEnd = System.nanoTime();
        //DEBUG END

        g2.dispose();
    }

    public void updatePlayerPosition(){
//        positionModel.setxPos(player.getWorldX());
//        positionModel.setyPos(player.getWorldY());
//        databaseController.updatePosition(positionModel);
    }

    public Player getPlayer(){
        return this.player;
    }

    public TileRenderer getTileRenderer() {
        return tileRenderer;
    }

    public GameObjectHandler getGameObjectHandler() {
        return gameObjectHandler;
    }

    public MouseHandler getMouseHandler() {
        return mouseHandler;
    }

}
