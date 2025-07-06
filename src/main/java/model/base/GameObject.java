package model.base;

import helpers.Renderer;
import input.InputHandler;
import main.Game;

import java.awt.*;

public class GameObject {

    public Rectangle bounds;

    public Color drawColor;

    public GameObject(Rectangle bounds) {
        this.bounds = bounds;
    }

    public void update() {

    }

    public void interact(InputHandler.ClickType clickType) {

    }

    public void draw(Graphics2D g, Game game) {
        Renderer.drawEntity(g, game, bounds, drawColor);
    }
}
