package model;

import input.InputHandler;
import main.Game;
import model.base.GameObject;

import java.awt.*;

public class Chest extends GameObject {

    public Chest(Rectangle bounds) {
        super(bounds);

        drawColor = Color.ORANGE;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void interact(InputHandler.ClickType clickType) {
        super.interact(clickType);
    }

    @Override
    public void draw(Graphics2D g, Game game) {
        super.draw(g, game);
    }
}
