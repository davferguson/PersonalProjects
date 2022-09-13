package input;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputHandler implements KeyListener {

    private static boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_W){
            upPressed = true;
        }
        if(keyCode == KeyEvent.VK_S){
            downPressed = true;
        }
        if(keyCode == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(keyCode == KeyEvent.VK_D){
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_W){
            upPressed = false;
        }
        if(keyCode == KeyEvent.VK_S){
            downPressed = false;
        }
        if(keyCode == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(keyCode == KeyEvent.VK_D){
            rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public static boolean isUpPressed() {
        return upPressed;
    }

    public static boolean isDownPressed() {
        return downPressed;
    }

    public static boolean isLeftPressed() {
        return leftPressed;
    }

    public static boolean isRightPressed() {
        return rightPressed;
    }
}
