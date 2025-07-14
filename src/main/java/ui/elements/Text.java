package ui.elements;

import ui.GameWindow;

import java.awt.*;

public class Text extends WindowElement {

    public Font font;

    public String content;
    public Color color;

    public Text(GameWindow parent, Point center, Font font, String content, Color color) {
        super(parent, center);

        this.font = font;

        this.content = content;
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);

        Point drawPos = getAbsDraw(g);

        g.setFont(font);
        g.setColor(color);

        g.drawString(content, drawPos.x, drawPos.y);
    }

    @Override
    public Point getAbsDraw(Graphics2D g) {
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int width = fontMetrics.stringWidth(content);
        int height = fontMetrics.getMaxAscent();

        int x = getAbsCenter().x - width / 2;
        int y = getAbsCenter().y + height / 2;

        return new Point(x, y);
    }

    @Override
    public Rectangle getBounds(Graphics2D g) {
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int width = fontMetrics.stringWidth(content);
        int height = fontMetrics.getMaxAscent();

        int x = getAbsCenter().x - width / 2;
        int y = getAbsCenter().y - height / 2;

        return new Rectangle(x, y, width, height);
    }
}
