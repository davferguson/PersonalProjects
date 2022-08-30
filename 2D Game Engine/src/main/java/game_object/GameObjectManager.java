package game_object;

import entity.Player;
import main.Camera;
import main.CollisionHandler;
import main.GamePanel;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameObjectManager {

    TreeObject treeObject;
    GamePanel gp;
    Camera mainCamera;
    CollisionHandler collisionHandler;
    List<GameObject> gameObjects = new ArrayList<>();
    Player player;

    public GameObjectManager(GamePanel gp){
        this.gp = gp;
        this.mainCamera = gp.getMainCamera();
        this.collisionHandler = gp.getCollisionHandler();
        this.player = gp.getPlayer();
//        generateObjects("resources/maps/Tiled_Map01.txt");
//        collisionHandler.addObjectCollisions(gameObjects);
    }

    public void draw(Graphics2D g2){
        int curScreenXPos = -mainCamera.getWorldX() + GamePanel.SCREEN_WIDTH/2;
        int curScreenYPos = -mainCamera.getWorldY() + GamePanel.SCREEN_HEIGHT/2;
        for(GameObject object : gameObjects){
//            player.draw(g2);
            g2.drawImage(object.getImage(), object.getWorldX()+curScreenXPos, object.getWorldY()+curScreenYPos, null);

        }

    }

    private void generateObjects(String filePath){
        try {
            File initialFile = new File(filePath);
            InputStream is = new FileInputStream(initialFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String curLine = br.readLine();
            int y = -1;
            while(curLine != null){
                String[] column = curLine.split(" ");
                for(int x = 0; x < column.length; x++){
                    if(column[x].equals("46")){
                        gameObjects.add(new TreeObject(x*GamePanel.TILE_SIZE, y*GamePanel.TILE_SIZE));
                    }
                }
                y++;
                curLine = br.readLine();
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
