package model;

import input.InputHandler;
import main.Game;
import model.base.GameObject;

import java.awt.*;

public class Tree extends GameObject {

    public Tree(Rectangle bounds) {
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

    @Override
    public void draw(Graphics2D g, Game game) {
        super.draw(g, game);
    }
}
