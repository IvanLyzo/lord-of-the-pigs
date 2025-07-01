package model.base;

import helpers.Bound;

import java.awt.*;
import java.util.Vector;

public class Entity {

    public Bound bounds;

    public Color drawColor = Color.WHITE;

    public Entity(int xPos, int yPos, int width, int height) {
        bounds = new Bound(xPos, yPos, width, height);
    }

    public void update() {

    }

    public void draw(Graphics2D g) {
        g.setColor(drawColor);
        g.fillRect(bounds.xPos, bounds.yPos, bounds.width, bounds.height);
    }
}
