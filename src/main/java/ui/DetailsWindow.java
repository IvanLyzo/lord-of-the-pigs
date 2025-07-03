package ui;

import helpers.Bound;
import input.InputHandler;
import model.Man;

import java.awt.*;

public class DetailsWindow extends Window {

    public Man activeBoy;
    public boolean forceOpen = false;

    public DetailsWindow(Bound bounds, int keyCode, InputHandler inputHandler) {
        super(bounds, keyCode, inputHandler);
    }

    public void update() {
        super.update();

        if (!active) {
            if (inputHandler.keysPressed.contains(keyCode)) {
                forceOpen = true;
                active = true;
                return;
            }

            if (activeBoy != null) {
                active = true;
                return;
            }
        }
        if (active) {
            if (inputHandler.keysPressed.contains(keyCode) || (activeBoy == null && !forceOpen)) {
                forceOpen = false;
                active = false;
            }
        }
    }

    public void draw(Graphics2D g) {
        super.draw(g);
    }
}
