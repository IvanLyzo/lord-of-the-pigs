package helpers;

import input.InputHandler;
import main.Game;

import java.awt.*;

public class Camera {

    // SCREEN SETTINGS
    public int screenWidth = 1200;
    public int screenHeight = 900;

    public Rectangle view;
    public Rectangle viewLimit;

    private final InputHandler inputHandler;

    public Camera(Game game, InputHandler inputHandler) {
        this.inputHandler = inputHandler;

        view = new Rectangle(-screenWidth / 2, -screenHeight / 2, screenWidth, screenHeight);
        viewLimit = new Rectangle(game.map.bounds.x, game.map.bounds.y, game.map.bounds.width, game.map.bounds.height);
    }

    public void move() {
        view.x += inputHandler.cameraDir.x * 8;
        view.y += inputHandler.cameraDir.y * 8;

        if (view.x < viewLimit.x) {
            view.x = viewLimit.x;
        }
        if (view.y < viewLimit.y) {
            view.y = viewLimit.y;
        }
        if (view.x + view.width > viewLimit.x + viewLimit.width) {
            view.x = viewLimit.x + viewLimit.width - view.width;
        }
        if (view.y + view.height > viewLimit.y + viewLimit.height) {
            view.y = viewLimit.y + viewLimit.height - view.height;
        }

    }
}
