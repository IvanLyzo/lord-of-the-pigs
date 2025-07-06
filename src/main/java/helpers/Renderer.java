package helpers;

import main.Game;

import java.awt.*;

public class Renderer {

    public static void drawEntity(Graphics2D g, Game game, Rectangle worldBound, Color fillColor) {
        g.setColor(fillColor);

        Point screenCords = new Point(worldBound.x - game.camera.view.x, worldBound.y - game.camera.view.y);

        g.fillRect(screenCords.x, screenCords.y, worldBound.width, worldBound.height);

        // draw border (TEMP)
//        g.setColor(Color.RED);
//        g.drawRect(screenCords.x, screenCords.y, worldBound.width, worldBound.height);
    }

    public static void drawWindow(Graphics2D g, Rectangle bounds) {
        g.setColor(Color.BLACK);

        g.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, 20, 20);
    }
}
