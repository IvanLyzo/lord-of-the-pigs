package helpers;

import input.InputHandler;
import main.Game;

public class Camera {

    // SCREEN SETTINGS
    public int screenWidth = 1200;
    public int screenHeight = 900;

    public Bound view;
    public Bound viewLimit;

    private final InputHandler inputHandler;

    public Camera(Game game, InputHandler inputHandler) {
        this.inputHandler = inputHandler;

        view = new Bound(-screenWidth / 2, -screenHeight / 2, screenWidth, screenHeight);
        viewLimit = new Bound(game.map.bounds.xPos, game.map.bounds.yPos, game.map.bounds.width, game.map.bounds.height);
    }

    public void move() {
        view.xPos += inputHandler.cameraDir.x * 8;
        view.yPos += inputHandler.cameraDir.y * 8;

        if (view.xPos < viewLimit.xPos) {
            view.xPos = viewLimit.xPos;
        }
        if (view.yPos < viewLimit.yPos) {
            view.yPos = viewLimit.yPos;
        }
        if (view.xPos + view.width > viewLimit.xPos + viewLimit.width) {
            view.xPos = viewLimit.xPos + viewLimit.width - view.width;
        }
        if (view.yPos + view.height > viewLimit.yPos + viewLimit.height) {
            view.yPos = viewLimit.yPos + viewLimit.height - view.height;
        }

    }
}
