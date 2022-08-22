package ui;

import main.GamePanel;
import main.Position;

import java.awt.*;

public abstract class UIComponent {
    private Position position = new Position(0, 0);
    private boolean isVisible = true;

    public abstract void draw(Graphics2D g2);
    public abstract void update(GamePanel gp, Graphics2D g2);

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
