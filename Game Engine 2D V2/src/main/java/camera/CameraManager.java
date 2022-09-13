package camera;

import input.KeyboardInputHandler;
import main.GamePanel;

public class CameraManager {

    private static int cameraXPos = 0;
    private static int cameraYPos = 0;

    public void update(){
        if(KeyboardInputHandler.isRightPressed()){
            cameraXPos -= GamePanel.MOVE_SPEED;
        }
        if(KeyboardInputHandler.isLeftPressed()){
            cameraXPos += GamePanel.MOVE_SPEED;
        }
        if(!KeyboardInputHandler.isLeftPressed() && !KeyboardInputHandler.isRightPressed()){
            if(KeyboardInputHandler.isUpPressed()){
                cameraYPos += GamePanel.MOVE_SPEED;
            }
            if(KeyboardInputHandler.isDownPressed()){
                cameraYPos -= GamePanel.MOVE_SPEED;
            }
        }

    }

    public static int getCameraXPos() {
        return cameraXPos;
    }

    public static int getCameraYPos() {
        return cameraYPos;
    }
}
