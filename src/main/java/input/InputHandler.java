package input;

import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener, MouseListener {

    public Game game;

    public List<Integer> keysPressed = new ArrayList<>();

    public Point clickPoint;
    public ClickType clickType;
    public ClickFlag clickFlag;

    public Point cameraDir = new Point(0, 0);

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
        if (e.getButton() == MouseEvent.BUTTON1) {
            clickType = ClickType.LEFT_CLICK;
            System.out.println("Primary Click");
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            clickType = ClickType.RIGHT_CLICK;
            System.out.println("Secondary click");
        }

        clickPoint = new Point(e.getX() + game.camera.view.xPos, e.getY() + game.camera.view.yPos);
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

    public enum ClickType {
        LEFT_CLICK,
        RIGHT_CLICK
    }
}
