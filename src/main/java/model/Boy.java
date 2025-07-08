package model;

import helpers.Tile;
import input.InputHandler;
import main.Game;
import model.base.Entity;
import model.base.Item;

import java.awt.*;
import java.util.ArrayList;

public class Boy extends Entity {

    // character stats (here for now) TODO
    public String name;
    public int age;

    public InputHandler inputHandler;

    public boolean activated;

    // maybe make an inventory class TODO
    public ArrayList<Item> inventory;

    public Point tgt;
    public Item item_tgt;

    public Boy(Point p, InputHandler inputHandler) {
        super(new Rectangle(p.x, p.y, Tile.scaledTileSize, Tile.scaledTileSize), "/player/player.png");

        this.inputHandler = inputHandler;
        inventory = new ArrayList<>();

        speed = 4;

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
        // set target for chasing
        if (inputHandler.clickFlag == InputHandler.ClickFlag.ITEM) {
            item_tgt = (Item) inputHandler.clickObj;
        } else if (inputHandler.clickFlag == InputHandler.ClickFlag.EMPTY) {
            tgt = inputHandler.clickPoint;
        }

        // execute chase for item target
        if (item_tgt != null) {
            super.move(new Point(item_tgt.centerX(), item_tgt.centerY()));

            if (atTarget(new Point(item_tgt.centerX(), item_tgt.centerY()))) {
                item_tgt.entityInteract(this);
                item_tgt = null;
            }
        }

        // execute movement to point
        if (tgt != null) {
            super.move(tgt);

            if (atTarget(tgt)) {
                tgt = null;
            }
        }
    }

    @Override
    public void draw(Graphics2D g, Game game) {
        super.draw(g, game);
    }
}
