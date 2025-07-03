package ui;

import helpers.Bound;
import input.InputHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Window {

    public int keyCode;
    public Bound bounds;

    public final InputHandler inputHandler;

    public List<Text> textboxes;

    public boolean pausesGame = false;
    public boolean active = false;

    public Window(Bound bounds, int keyCode, InputHandler inputHandler) {
        this.bounds = bounds;
        this.keyCode = keyCode;
        this.inputHandler = inputHandler;

        textboxes = new ArrayList<>();
    }

    public void update() {
        // check default behaviour (exited, minimized window?)
    }

    public void draw(Graphics2D g) {
        // draw base window (borders, exit and minimize windows?)

        g.setColor(Color.BLACK);
        g.fillRect(bounds.xPos, bounds.yPos, bounds.width, bounds.height);
    }
}
