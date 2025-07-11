package model;

import helpers.Tile;
import input.InputHandler;
import main.Game;
import model.base.GameObject;

import java.awt.*;

public class Tree extends GameObject {

    public Tree(Point p) {
        super(new Rectangle(p.x, p.y, Tile.scaledTileSize, Tile.scaledTileSize), "/tree/tree.png");
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