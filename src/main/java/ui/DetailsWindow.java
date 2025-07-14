package ui;

import helpers.ResHandler;
import input.InputHandler;
import main.Game;
import model.Boy;
import ui.elements.Text;
import ui.elements.WindowElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class DetailsWindow extends GameWindow {

    public Rectangle profileBounds;

    Text name;

    public Boy activeBoy;
    public boolean forceOpen = false;

    public DetailsWindow(Game game, InputHandler inputHandler) {
        super(game, inputHandler, new Rectangle(10, 60, 80, 30), null, KeyEvent.VK_E);

        profileBounds = new Rectangle(getAbsBounds().x + 32, getAbsBounds().y - 112, 160, 160);

        name = new Text(this, new Point(12, 32), normal, "Name: ", WindowElement.DEFAULT_COLOR);
    }

    @Override
    public void update() {
        if (!active) {
            if (inputHandler.keysPressed.contains(keyCode)) {
                forceOpen = true;
                active = true;
            } else if (activeBoy != null) {
                active = true;
                name.content = "name: " + Objects.requireNonNull(activeBoy).name;
            }
            return;
        }

        if (active) {
            if (inputHandler.keysPressed.contains(keyCode) || (activeBoy == null && !forceOpen)) {
                forceOpen = false;
                active = false;
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);

        if (activeBoy == null) {
            return;
        }

        // draw profile
        g.drawImage(bg, profileBounds.x, profileBounds.y, profileBounds.width, profileBounds.height, null);
        g.drawImage(activeBoy.sprite, profileBounds.x + 16, profileBounds.y + 16, profileBounds.width - 32, profileBounds.height - 32, null);
        drawBorder(g, profileBounds);

        // draw all window text
        name.draw(g);
    }
}
