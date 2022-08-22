package game_object;

import main.Position;

import java.awt.*;

public abstract class GameObject {

    public abstract void draw(Graphics2D g2);

    public abstract Rectangle getBounds();

    public abstract Position getPostion();
}
