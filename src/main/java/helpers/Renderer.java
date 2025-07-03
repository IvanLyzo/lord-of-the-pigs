package helpers;

import main.Game;

import java.awt.*;

public class Renderer {

    private final Game game;

    public Renderer(Game game) {
        this.game = game;
    }

    public void draw(Graphics2D g, Bound worldBound, Color fillColor) {
        g.setColor(fillColor);

        Point screenCords = new Point(worldBound.xPos - game.camera.xPos, worldBound.yPos - game.camera.yPos);

        g.fillRect(screenCords.x, screenCords.y, worldBound.width, worldBound.height);
    }
}
