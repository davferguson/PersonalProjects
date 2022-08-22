package ui.screen;

import main.GamePanel;
import main.UtilityTool;
import ui.UI;
import ui.UIComponent;
import ui.button.ContinueButton;
import ui.button.ExitGameButton;
import ui.button.StartGameButton;
import ui.button.UIButton;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TitleScreen {
    private List<UIComponent> uiComponentList = new ArrayList<>();
    private BufferedImage background;

    public TitleScreen() {
        UIButton startGameBtn = new StartGameButton(GamePanel.SCREEN_WIDTH / 2 - UI.UI_SPACING * 2, GamePanel.SCREEN_HEIGHT / 2 + UI.UI_SPACING * 3, 200, 40, new Color(0, 0, 0, 147), new Color(103, 103, 103, 147), new Font("BASE", Font.PLAIN, 30), "Start Game");
        uiComponentList.add(startGameBtn);

        UIButton exitBtn = new ExitGameButton(GamePanel.SCREEN_WIDTH / 2 - UI.UI_SPACING * 2, GamePanel.SCREEN_HEIGHT / 2 + UI.UI_SPACING * 4, 200, 40, new Color(0, 0, 0, 147), new Color(103, 103, 103, 147), new Font("BASE", Font.PLAIN, 30), "Exit Game");
        uiComponentList.add(exitBtn);

        getBackground();
    }

    public void manageTitleScreen(Graphics2D g2, GamePanel gp) {
        g2.drawImage(background, 0, 0, null);

        Color color = new Color(255, 255, 255);
        g2.setColor(color);
        g2.setFont(new Font("Plain", Font.BOLD, 50));
        String gameTitle = "Big Fighter Man";
        g2.drawString(gameTitle, UI.getCenteredTextX(g2, gameTitle), GamePanel.SCREEN_HEIGHT / 2);


        for (UIComponent component : uiComponentList) {
            component.update(gp, g2);
            component.draw(g2);
        }

    }

    private void getBackground() {
        try {
            background = ImageIO.read(new FileImageInputStream(new File("resources/backgrounds/fossil_cave.jpg")));
            //if(getClass().getResourceAsStream("backgrounds/fossil_cave.jpg") != null){
                //background = ImageIO.read(GamePanel.class.getResource("resources/images/fossil_cave.jpg"));
            //}

            background = UtilityTool.scaleImage(background, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}