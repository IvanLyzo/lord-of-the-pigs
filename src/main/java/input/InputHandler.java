package input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener, MouseListener {

    public List<Integer> keysPressed = new ArrayList<>();

    public Point clickPoint;
    public ClickFlag clickFlag;

    public Point cameraDir;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W -> cameraDir.y = -1;
            case KeyEvent.VK_A -> cameraDir.x = -1;
            case KeyEvent.VK_S -> cameraDir.y = 1;
            case KeyEvent.VK_D -> cameraDir.x = 1;
            default -> keysPressed.add(code);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W -> cameraDir.y = 0;
            case KeyEvent.VK_A -> cameraDir.x = 0;
            case KeyEvent.VK_S -> cameraDir.y = 0;
            case KeyEvent.VK_D -> cameraDir.x = 0;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clickPoint = new Point(e.getX(), e.getY());
        clickFlag = ClickFlag.EMPTY;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public enum ClickFlag {
        EMPTY,
        ENTITY,
        UI,
        INVALID
    }
}
