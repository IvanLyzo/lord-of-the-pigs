package model;

import input.MouseHandler;
import model.base.Animal;

import java.awt.*;

public class Man extends Animal {

    public MouseHandler mouseHandler;
    public boolean activated;

    public Man(int xPos, int yPos, int width, int height) {
        super(xPos, yPos, width, height);

        mouseHandler = new MouseHandler();

        speed = 4;
    }

    @Override
    public void update() {
        super.update();

        if (mouseHandler.newClick) {
            if (bounds.inBounds(mouseHandler.clickPoint)) {
                if (activated) {
                    activated = false;
                    drawColor = Color.WHITE;
                } else {
                    activated = true;
                    drawColor = Color.BLUE;
                }
            }
        }

        move();
    }

    public void move() {
        if (!activated) {
            return;
        }

        if (mouseHandler.clickPoint != null) {
            super.move(mouseHandler.clickPoint);
        }
    }
}
