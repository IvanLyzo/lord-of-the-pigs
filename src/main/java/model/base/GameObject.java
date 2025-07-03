package model.base;

import helpers.Bound;
import helpers.Renderer;
import input.InputHandler;
import main.Game;

import java.awt.*;

public class GameObject {

    public Bound bounds;

    public Color drawColor;

    public GameObject(Bound bounds) {
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
