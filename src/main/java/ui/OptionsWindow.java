package ui;

import helpers.Bound;
import input.InputHandler;

import java.awt.*;

public class OptionsWindow extends Window {

    public OptionsWindow(Bound bounds, int keyCode, InputHandler inputHandler) {
        super(bounds, keyCode, inputHandler);
    }

    public void update() {
        super.update();

        if (!active) {
            if (inputHandler.keysPressed.contains(keyCode)) {
                active = true;
                return;
            }
        }
        if (active) {
            if (bounds.inBounds(inputHandler.clickPoint) == Bound.CollisionCheckResponse.FALSE || inputHandler.keysPressed.contains(keyCode)) {
                active = false;
            }

        }
    }

    public void draw(Graphics2D g) {
        super.draw(g);
    }
}
