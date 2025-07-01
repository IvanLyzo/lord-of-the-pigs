package input;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    public Point clickPoint; // TODO: probably shouldn't be null before first click
    public boolean newClick = false;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (clickPoint == null) {
                clickPoint = new Point(e.getX(), e.getY());
                newClick = true;
                return;
            }

            Point newClickPoint = new Point(e.getX(), e.getY());

            if (clickPoint.x != newClickPoint.x && clickPoint.y != newClickPoint.y) {
                newClick = true;
                clickPoint = newClickPoint;
            } else {
                newClick = false;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        newClick = false;
    }

//    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
