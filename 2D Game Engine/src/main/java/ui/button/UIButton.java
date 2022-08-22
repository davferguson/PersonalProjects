package ui.button;

import main.GamePanel;
import main.GameState;
import main.MouseHandler;
import main.Position;
import ui.UI;
import ui.UIComponent;

import java.awt.*;

public class UIButton extends UIComponent {
    private int width;
    private int height;
    private String text;
    private Color frontColor;
    private Color backColor;
    private Font font;
    private boolean isHovering;
    private String alignment = "";

    public UIButton(int xPos, int yPos, int width, int height, Color frontColor, Color backColor, Font font, String text){
        setPosition(new Position(xPos, yPos));
        this.width = width;
        this.height = height;
        this.text = text;
        this.frontColor = frontColor;
        this.backColor = backColor;
        this.font = font;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setFont(font);
        g2.setColor(backColor);
        FontMetrics fm = g2.getFontMetrics();
        g2.fillRoundRect(getPosition().getX()-3, getPosition().getY() - (fm.getHeight()/2) - 3, width+6, height+6, 20, 20);

        g2.setColor(frontColor);
        g2.fillRoundRect(getPosition().getX(), getPosition().getY() - (fm.getHeight()/2), width, height, 20, 20);

        Color color;
        color = new Color(255, 255, 255);
        if(isHovering){
            color = new Color(0,0,0);
        }
        g2.setColor(color);
        g2.setFont(font);
        fm = g2.getFontMetrics();
        int xPos = getPosition().getX() + (width/2 - fm.stringWidth(text)/2);
        int yPos = getPosition().getY() + (height/2 - fm.getHeight()/4);
        g2.drawString(text, xPos, yPos);
    }

    @Override
    public void update(GamePanel gp, Graphics2D g2) {
        MouseHandler mouseHandler = gp.getMouseHandler();
        int mouseX = mouseHandler.getMousePosition().getX();
        int mouseY = mouseHandler.getMousePosition().getY();

        isHovering = getBounds(g2).contains(mouseX, mouseY);
        if(isHovering && mouseHandler.isMousePressed()){
            buttonClicked();
        }
    }
    public void buttonClicked(){
        System.out.println("button functionality not created");
    }

    private Rectangle getBounds(Graphics2D g2){
        FontMetrics fm = g2.getFontMetrics();
        return new Rectangle(getPosition().getX(), getPosition().getY() - (fm.getHeight()/2), width, height);
    }
}
