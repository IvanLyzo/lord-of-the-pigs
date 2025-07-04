package model;

import helpers.Bound;
import helpers.Tile;
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

    public Man(Point pos, InputHandler inputHandler) {
        super(new Bound(pos.x, pos.y, Tile.TILESIZE, Tile.TILESIZE));

        this.inputHandler = inputHandler;

        speed = 2;

        drawColor = Color.WHITE;
    }

    @Override
    public void update() {
        super.update();

        if (activated) {
            move();
        }
    }

    @Override
    public void interact(InputHandler.ClickType clickType) {
        super.interact(clickType);

        if (activated) {
            activated = false;
            drawColor = Color.WHITE;
        } else {
            activated = true;
            drawColor = Color.BLUE;
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
