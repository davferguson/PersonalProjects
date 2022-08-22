package ui.button;

import main.GamePanel;
import main.GameState;

import java.awt.*;

public class ContinueButton extends UIButton{
    public ContinueButton(int xPos, int yPos, int width, int height, Color frontColor, Color backColor, Font font, String text) {
        super(xPos, yPos, width, height, frontColor, backColor, font, text);
    }


    @Override
    public void buttonClicked(){
        GamePanel.gameState = GameState.PLAYING;
    }
}
