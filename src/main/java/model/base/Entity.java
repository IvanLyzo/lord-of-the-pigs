package model.base;

import input.InputHandler;
import main.Game;

import java.awt.*;

public class Entity extends GameObject {

    public int health;
    public int speed; // TODO: figure out how to init these

    public Entity(Rectangle bounds, String spriteLoc) {
        super(bounds, spriteLoc);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void interact(InputHandler.ClickType clickType) {
        super.interact(clickType);
    }

    public void move(Point target) {
        int xOffset = target.x - centerX();
        int yOffset = target.y - centerY();

        if (xOffset > 0) {
            bounds.x += Math.min(speed, xOffset);
        }
        if (xOffset < 0) {
            bounds.x -= Math.min(speed, Math.abs(xOffset));
        }
        if (yOffset > 0) {
            bounds.y += Math.min(speed, yOffset);
        }
        if (yOffset < 0) {
            bounds.y -= Math.min(speed, Math.abs(yOffset));
        }
    }

    public boolean atTarget(Point tgt) {
        return centerX() == tgt.x && centerY() == tgt.y;
    }

    @Override
    public void draw(Graphics2D g, Game game) {
        super.draw(g, game);
    }
}
