package game_object;

import entity.Entity;
import main.GamePanel;
import main.GameState;

import java.awt.*;
import java.util.List;

public class GameObjectRenderer {

    private GamePanel gp;
    private List<GameObject> gameObjects;
    private final int ENTITY_HEIGHT = GamePanel.SCALED_PLAYER_SIZE+10;

    public GameObjectRenderer(GamePanel gp){
        this.gp = gp;
        gameObjects = gp.getGameObjectHandler().getGameObjects();
    }

    public void draw(Graphics2D g2){

        for(GameObject curObject : gameObjects){
            if(isEntityAboveGameObject(gp.getPlayer(), curObject)){
                gp.getPlayer().draw(g2);
                curObject.draw(g2);
            } else{
                curObject.draw(g2);
                gp.getPlayer().draw(g2);
            }

            //DEBUG
            boolean isDebug = true;
            if(isDebug){
                curObject.drawDebug(g2);
                gp.getPlayer().drawDebug(g2);
            }
        }
    }

    public boolean isEntityAboveGameObject(Entity entity, GameObject gameObject){
        if(entity.getWorldY() - ENTITY_HEIGHT < gameObject.getPosition().getY()){
            return true;
        }
        return false;
    }
}
