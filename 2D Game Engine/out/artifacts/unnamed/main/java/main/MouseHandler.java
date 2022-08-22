package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {

    private Position mousePosition = new Position(0 ,0);
    private boolean isMousePressed = false;

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        System.out.println("Mouse clicked at X: " + mouseX + " Y: " + mouseY);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        isMousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isMousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition.setX(e.getX());
        mousePosition.setY(e.getY());
    }

    public Position getMousePosition() {
        return mousePosition;
    }

    public boolean isMousePressed() {
        return isMousePressed;
    }
}
