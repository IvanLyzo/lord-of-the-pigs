package ui.elements;

import ui.GameWindow;

import java.awt.*;

public class Button extends WindowElement {

    Text content;

    int padX;
    int padY;
    Color padColor;

    public Button(GameWindow parent, Point center, Font font, String content, int padX, int padY) {
        super(parent, center);

        this.content = new Text(parent, center, font, content, Color.WHITE);

        this.padX = padX;
        this.padY = padY;
        padColor = DEFAULT_COLOR;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);

        g.setColor(padColor);

        Rectangle b = getBounds(g);
        g.fillRect(b.x, b.y, b.width, b.height);

        content.draw(g);
    }

    @Override
    public Point getAbsDraw(Graphics2D g) {
        return new Point(getAbsCenter().x - padX, getAbsCenter().y + padY);
    }

    @Override
    public Rectangle getBounds(Graphics2D g) {
        Rectangle textBounds = content.getBounds(g);
        return new Rectangle(textBounds.x - padX, textBounds.y - padY, textBounds.width + 2 * padX, textBounds.height + 2 * padY);
    }
}
