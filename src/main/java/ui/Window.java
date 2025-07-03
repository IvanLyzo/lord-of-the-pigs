package ui;

import helpers.Bound;
import helpers.Renderer;
import input.InputHandler;
import model.base.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Window extends GameObject {

    public int keyCode;

    public final InputHandler inputHandler;

    public List<Text> textboxes;

    public boolean pausesGame = false;
    public boolean active = false;

    public Window(Bound bounds, int keyCode, InputHandler inputHandler) {
        super(bounds);

        this.keyCode = keyCode;
        this.inputHandler = inputHandler;

        textboxes = new ArrayList<>();
    }

    @Override
    public void update() {
        // check default behaviour (exited, minimized window?)
        super.update();
    }

    @Override
    public void interact(InputHandler.ClickType clickType) {
        super.interact(clickType);
    }

    public void draw(Graphics2D g) {
        // draw base window (borders, exit and minimize windows?)
        Renderer.drawWindow(g, bounds);
    }
}
