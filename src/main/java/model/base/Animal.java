package model.base;

import java.awt.*;

public class Animal extends Entity {

    public int health;
    public int speed; // TODO: figure out how to init these

    public Animal(int xPos, int yPos, int width, int height) {
        super(xPos, yPos, width, height);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void interact() {
        super.interact();
    }

    public void move(Point target) {
        int centerX = bounds.xPos + bounds.width / 2;
        int centerY = bounds.yPos + bounds.height / 2;

        int xOffset = target.x - centerX;
        int yOffset = target.y - centerY;

        if (xOffset > 0) {
            bounds.xPos += Math.min(speed, xOffset);
        }
        if (xOffset < 0) {
            bounds.xPos -= Math.min(speed, Math.abs(xOffset));
        }
        if (yOffset > 0) {
            bounds.yPos += Math.min(speed, yOffset);
        }
        if (yOffset < 0) {
            bounds.yPos -= Math.min(speed, Math.abs(yOffset));
        }
    }
}
