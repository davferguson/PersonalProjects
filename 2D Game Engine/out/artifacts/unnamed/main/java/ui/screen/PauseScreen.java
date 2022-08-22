package ui.screen;

import main.GamePanel;
import ui.UI;
import ui.button.ContinueButton;
import ui.button.ExitGameButton;
import ui.button.MainMenuButton;
import ui.button.UIButton;
import ui.UIComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PauseScreen {
    private List<UIComponent> uiComponentList = new ArrayList<>();

    public PauseScreen(){
        UIButton continueBtn = new ContinueButton(GamePanel.SCREEN_WIDTH/2 - UI.UI_SPACING*2, GamePanel.SCREEN_HEIGHT/2 + UI.UI_SPACING*3,200,40, new Color(0, 0, 0, 147), new Color(103, 103, 103, 147), new Font("BASE", Font.PLAIN, 30), "Continue");
        uiComponentList.add(continueBtn);

        UIButton exitBtn = new ExitGameButton(GamePanel.SCREEN_WIDTH/2 - UI.UI_SPACING*2, GamePanel.SCREEN_HEIGHT/2 + UI.UI_SPACING*4,200,40, new Color(0, 0, 0, 147), new Color(103, 103, 103, 147), new Font("BASE", Font.PLAIN, 30), "Exit Game");
        uiComponentList.add(exitBtn);

        UIButton mainMenuBtn = new MainMenuButton(GamePanel.SCREEN_WIDTH/2 - UI.UI_SPACING*2, GamePanel.SCREEN_HEIGHT/2 + UI.UI_SPACING*5,200,40, new Color(0, 0, 0, 147), new Color(103, 103, 103, 147), new Font("BASE", Font.PLAIN, 30), "Main Menu");
        uiComponentList.add(mainMenuBtn);
    }

    public void managePaused(Graphics2D g2, GamePanel gp){

        Color color = new Color(0,0,0,110);
        g2.setColor(color);
        g2.fillRect(0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT);

        color = new Color(255,255,255);
        g2.setColor(color);
        g2.setFont(new Font("Plain", Font.BOLD, 50));
        String pausedText = "Game Paused";
        g2.drawString(pausedText, UI.getCenteredTextX(g2, pausedText), GamePanel.SCREEN_HEIGHT/2);



        for(UIComponent component : uiComponentList){
            component.update(gp, g2);
            component.draw(g2);
        }

    }
}
