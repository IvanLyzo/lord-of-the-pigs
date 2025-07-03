package model;

import helpers.Bound;
import input.InputHandler;
import model.base.Animal;

import java.awt.*;

public class Man extends Animal {

    // character stats (here for now) TODO
    public String name;
    public int age;

    public InputHandler inputHandler;

    public boolean activated;
    public Point tgt;

    public Man(int xPos, int yPos, int width, int height, InputHandler inputHandler) {
        super(xPos, yPos, width, height);

        this.inputHandler = inputHandler;

        speed = 2;
    }

    @Override
    public void update() {
        super.update();

        if (activated) {
            move();
        }
    }

    public void move() {
        if (inputHandler.clickFlag == InputHandler.ClickFlag.EMPTY) {
            tgt = inputHandler.clickPoint;
        }

        if (tgt != null) {
            super.move(tgt);
        }
    }
}
