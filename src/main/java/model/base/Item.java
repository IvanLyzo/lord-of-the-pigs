package model.base;

import input.InputHandler;
import main.Game;

import java.awt.*;

public class Item extends GameObject {

    // stackable and other such values
    public boolean dropped = true;

    public Item(Game game, Rectangle bounds) {
        super(game, bounds);
    }

    public Item(Game game, Rectangle bounds, String spriteLoc) {
        super(game, bounds, spriteLoc);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void interact(InputHandler.ClickType clickType) {
        super.interact(clickType);
    }

    public void entityInteract(Entity entity) {

    }

    @Override
    public void draw(Graphics2D g, Game game) {
        if (!dropped) {
            return;
        }

        super.draw(g, game);
    }
}
