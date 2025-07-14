package model;

import model.base.Tile;
import input.InputHandler;
import main.Game;
import model.base.Entity;
import model.base.Item;

import java.awt.*;
import java.util.ArrayList;

public class Boy extends Entity {

    // character stats (here for now) TODO
    public String name = "Hanny!";
    public int age;

    public InputHandler inputHandler;

    public boolean activated;

    // maybe make an inventory class TODO
    public ArrayList<Item> inventory;

    public Point tgt;
    public Item item_tgt;

    public Boy(Game game, Point p, InputHandler inputHandler) {
        super(game, new Rectangle(p.x, p.y, Tile.scaledTileSize, Tile.scaledTileSize), "/player/player.png");

        this.inputHandler = inputHandler;
        inventory = new ArrayList<>();

        speed = 4;
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

        activated = !activated;
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

        if (activated) {
            g.setColor(Color.BLACK);

            Point screenPos = game.camera.getScreenCords(new Point(bounds.x, bounds.y));
            g.drawRect(screenPos.x, screenPos.y, bounds.width, bounds.height);
        }
    }
}
