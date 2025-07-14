package ui;

import input.InputHandler;
import main.Game;
import ui.elements.Button;
import ui.elements.Text;
import ui.elements.WindowElement;

import java.awt.*;
import java.awt.event.KeyEvent;

public class OptionsWindow extends GameWindow {

    public Text title;
    public Button quit;

    public OptionsWindow(Game game, InputHandler inputHandler) {
        super(game, inputHandler, new Rectangle(20, 20, 60, 60), null, KeyEvent.VK_ESCAPE);

        title = new Text(this, new Point(50, 10), header, "Options", WindowElement.DEFAULT_COLOR);

        quit = new Button(this, new Point(50, 80), normal, "Quit", 32, 16);
    }

    public void update() {
        super.update();

        if (inputHandler.keysPressed.contains(keyCode)) {
            active = !active;
        }

        if (active && inputHandler.clickPoint != null) {
            if (!getAbsBounds().contains(game.camera.getScreenCords(inputHandler.clickPoint))) {
                active = false;
            }
        }
    }

    public void draw(Graphics2D g) {
        super.draw(g);

        title.draw(g);

        quit.draw(g);
    }
}
