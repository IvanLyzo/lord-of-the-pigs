package model.base;

import helpers.Bound;
import main.Game;

import java.awt.*;

public class Entity {

    public Bound bounds;

    public Color drawColor = Color.WHITE;

    public Entity(int xPos, int yPos, int width, int height) {
        bounds = new Bound(xPos, yPos, width, height);
    }

    public void update() {

    }

    public void draw(Graphics2D g, Game game) {
        g.setColor(drawColor);

        // get screen coords
        int screenX = bounds.xPos - game.camera.xPos;
        int screenY = bounds.yPos - game.camera.yPos;

        g.fillRect(screenX, screenY, bounds.width, bounds.height);
    }
}
