package camera;

import input.KeyboardInputHandler;
import main.GamePanel;
import player.Player;

public class CameraManager {

    private static int cameraXPos = 0;
    private static int cameraYPos = 0;
    private Player player;
    private int playerMoveSpeed;

    public CameraManager(Player player){
        this.player = player;
        this.playerMoveSpeed = player.getMoveSpeed();
    }

    public void update(){
        cameraXPos = player.getWorldXPos()-player.getScreenCenterX();
        cameraYPos = player.getWorldYPos()-player.getScreenCenterY();
//        if(KeyboardInputHandler.isRightPressed()){
//            cameraXPos -= playerMoveSpeed;
//        }
//        if(KeyboardInputHandler.isLeftPressed()){
//            cameraXPos += playerMoveSpeed;
//        }
//        if(!KeyboardInputHandler.isLeftPressed() && !KeyboardInputHandler.isRightPressed()){
//            if(KeyboardInputHandler.isUpPressed()){
//                cameraYPos += playerMoveSpeed;
//            }
//            if(KeyboardInputHandler.isDownPressed()){
//                cameraYPos -= playerMoveSpeed;
//            }
//        }

    }

    public static int getCameraXPos() {
        return cameraXPos;
    }

    public static int getCameraYPos() {
        return cameraYPos;
    }
}
