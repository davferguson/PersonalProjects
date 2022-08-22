package ui;

import main.GamePanel;
import main.GameState;
import ui.screen.PauseScreen;
import ui.screen.TitleScreen;


import java.awt.*;

public class UI {
    public static final int UI_SPACING = 16 * GamePanel.SCALE;

    private GamePanel gp;
    private PauseScreen pauseScreen = new PauseScreen();
    private TitleScreen titleScreen = new TitleScreen();

    public UI(GamePanel gp){
        this.gp = gp;
    }

    public void draw(Graphics2D g2){
        if(GamePanel.gameState == GameState.PAUSED){
            pauseScreen.managePaused(g2, gp);
        } else if(GamePanel.gameState == GameState.TITLE_SCREEN){
            titleScreen.manageTitleScreen(g2, gp);
        }
    }

    public static int getCenteredTextX(Graphics2D g2, String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return GamePanel.SCREEN_WIDTH/2 - length/2;
    }

}
