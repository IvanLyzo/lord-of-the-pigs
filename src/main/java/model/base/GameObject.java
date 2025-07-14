package model.base;

import helpers.ResHandler;
import input.InputHandler;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {

    public final Game game;

    public Rectangle bounds;
    public BufferedImage sprite;

    public int centerX() {
        return bounds.x + bounds.width / 2;
    }

    public int centerY() {
        return bounds.y + bounds.height / 2;
    }

    public GameObject(Game game, Rectangle bounds) {
        this.game = game;
        this.bounds = bounds;
    }

    public GameObject(Game game, Rectangle bounds, String spriteLoc) {
        this(game, bounds);

        sprite = ResHandler.getSprite(spriteLoc);
    }

    public void update() {

    }

    public void interact(InputHandler.ClickType clickType) {

    }

    public void draw(Graphics2D g, Game game) {
        Point screenCords = game.camera.getScreenCords(new Point(bounds.x, bounds.y));
        g.drawImage(sprite, screenCords.x, screenCords.y, bounds.width, bounds.height, null);
    }
}
