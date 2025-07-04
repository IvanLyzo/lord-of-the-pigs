package helpers;

import input.InputHandler;
import main.Game;

public class Camera {

    // SCREEN SETTINGS
    public final int tileScreenWidth = 40;
    public final int tileScreenHeight = 30;

    public int screenWidth = tileScreenWidth * Tile.TILESIZE;
    public int screenHeight = tileScreenHeight * Tile.TILESIZE;

    public Bound view;
    public Bound viewLimit;

    private final InputHandler inputHandler;

    public Camera(Game game, InputHandler inputHandler) {
        this.inputHandler = inputHandler;

        view = new Bound(-screenWidth / 2, -screenHeight / 2, screenWidth, screenHeight);

        int seaPad = game.map.seaPadding * Tile.TILESIZE;
        viewLimit = new Bound(game.map.bounds.xPos - seaPad, game.map.bounds.yPos - seaPad, game.map.bounds.width + 2*seaPad, game.map.bounds.height + 2*seaPad);
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
