package model;

import helpers.Tile;
import input.InputHandler;
import main.Game;
import model.base.Entity;
import model.base.Item;

import java.awt.*;

public class Conch extends Item {

    public Conch(Point p) {
        super(new Rectangle(p.x, p.y, Tile.itemSize, Tile.itemSize), "/tree/conch.png");
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void interact(InputHandler.ClickType clickType) {
        super.interact(clickType);

        if (clickType == InputHandler.ClickType.RIGHT_CLICK) {
            System.out.println("clicked on conch");
        }
    }

    @Override
    public void entityInteract(Entity entity) {
        super.entityInteract(entity);

        if (entity.getClass() != Boy.class) {
            return;
        }

        System.out.println("Player picked up conch");

        dropped = false;
        ((Boy) entity).inventory.add(this);
    }

    @Override
    public void draw(Graphics2D g, Game game) {
        super.draw(g, game);
    }
}
