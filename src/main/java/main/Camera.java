package main;

import input.InputHandler;

import java.awt.*;

public class Camera {

    // SCREEN SETTINGS
    public int screenWidth = 1200;
    public int screenHeight = 900;

    public Rectangle view;
    public Rectangle viewLimit;

    private final InputHandler inputHandler;

    public Camera(Game game, InputHandler inputHandler) {
        this.inputHandler = inputHandler;

        view = new Rectangle(-screenWidth / 2, -screenHeight / 2, screenWidth, screenHeight);
        viewLimit = new Rectangle(0, 0, game.map.width, game.map.height);
    }

    public Point getWorldCords(Point p) {
        return new Point(p.x + view.x, p.y + view.y);
    }

    public Point getScreenCords(Point p) {
        return new Point(p.x - view.x, p.y - view.y);
    }

    public void move() {
        view.x += inputHandler.cameraDir.x * 8;
        view.y += inputHandler.cameraDir.y * 8;

        if (view.x < viewLimit.x) {
            view.x = viewLimit.x;
        }
        if (view.y < viewLimit.y) {
            view.y = viewLimit.y;
        }
        if (view.x + view.width > viewLimit.x + viewLimit.width) {
            view.x = viewLimit.x + viewLimit.width - view.width;
        }
        if (view.y + view.height > viewLimit.y + viewLimit.height) {
            view.y = viewLimit.y + viewLimit.height - view.height;
        }
    }

    public void teleport(Point p) {
        view.x = p.x - view.width / 2;
        view.y = p.y - view.height / 2;

        if (view.x < viewLimit.x) {
            view.x = viewLimit.x;
        }
        if (view.y < viewLimit.y) {
            view.y = viewLimit.y;
        }
        if (view.x + view.width > viewLimit.x + viewLimit.width) {
            view.x = viewLimit.x + viewLimit.width - view.width;
        }
        if (view.y + view.height > viewLimit.y + viewLimit.height) {
            view.y = viewLimit.y + viewLimit.height - view.height;
        }
    }
}
