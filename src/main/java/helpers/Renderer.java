package helpers;

import main.Game;

import java.awt.*;

public class Renderer {

    public static void drawEntity(Graphics2D g, Game game, Bound worldBound, Color fillColor) {
        g.setColor(fillColor);

        Point screenCords = new Point(worldBound.xPos - game.camera.xPos, worldBound.yPos - game.camera.yPos);

        g.fillRect(screenCords.x, screenCords.y, worldBound.width, worldBound.height);

        // draw border (TEMP)
        g.setColor(Color.RED);
        g.drawRect(screenCords.x, screenCords.y, worldBound.width, worldBound.height);
    }

    public static void drawWindow(Graphics2D g, Bound bounds) {
        g.setColor(Color.BLACK);

        g.fillRoundRect(bounds.xPos, bounds.yPos, bounds.width, bounds.height, 20, 20);
    }
}
