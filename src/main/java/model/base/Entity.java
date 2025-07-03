package model.base;

import helpers.Bound;
import helpers.Renderer;
import main.Game;

import java.awt.*;

public class Entity {

    public Bound bounds;

    public Color drawColor;

    public Entity(int xPos, int yPos, int width, int height) {
        bounds = new Bound(xPos, yPos, width, height);
    }

    public Entity(Bound bounds) {
        this.bounds = bounds;
    }

    public void update() {

    }

    public void interact() {

    }

    public void draw(Graphics2D g, Game game) {
        Renderer.drawEntity(g, game, bounds, drawColor);
    }
}
