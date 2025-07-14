package ui.elements;

import ui.GameWindow;

import java.awt.*;

public class WindowElement {

    public static final Color DEFAULT_COLOR = new Color(122, 94, 60);

    public GameWindow parent;
    public Point center;

    public WindowElement(GameWindow parent, Point center) {
        this.parent = parent;
        this.center = center;
    }

    public Point getAbsCenter() {
        return new Point(parent.getAbsBounds().x + center.x * parent.getAbsBounds().width / 100, parent.getAbsBounds().y + center.y * parent.getAbsBounds().height / 100);
    }

    public void draw(Graphics2D g) {

    }

    public Point getAbsDraw(Graphics2D g) {
        return getAbsCenter();
    }

    public Rectangle getBounds(Graphics2D g) {
        return null;
    }
}
