package model.base;

import input.InputHandler;

import java.awt.*;

public class Animal extends GameObject {

    public int health;
    public int speed; // TODO: figure out how to init these

    public Animal(Rectangle bounds) {
        super(bounds);
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
        int centerX = bounds.x + bounds.width / 2;
        int centerY = bounds.y + bounds.height / 2;

        int xOffset = target.x - centerX;
        int yOffset = target.y - centerY;

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
}
