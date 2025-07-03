package main;

import helpers.Bound;
import helpers.Renderer;
import helpers.Tile;
import input.InputHandler;
import model.Man;

import java.awt.*;

public class GameController {

    public Game game;

    private final GameRenderer gameRenderer;
    private final InputHandler inputHandler;

    public GameController(int seed, InputHandler inputHandler) {
        game = new Game(seed, inputHandler);

        gameRenderer = new GameRenderer(game);

        this.inputHandler = inputHandler;
        inputHandler.game = game;

        generateWorld();
    }

    private void generateWorld() {
        for (int x = 0; x < game.mapTileWidth; x++) {
            for (int y = 0; y < game.mapTileHeight; y++) {
                game.map[x][y] = new Tile(x, y, Tile.Type.GRASS);
            }
        }

        game.boys.add(new Man(100, 100, Tile.TILESIZE, Tile.TILESIZE, inputHandler));
        game.boys.add(new Man(100, 200, Tile.TILESIZE, Tile.TILESIZE, inputHandler));
    }

    public void update() {

        // probably should have a camera class TODO
        game.camera.xPos += inputHandler.cameraDir.x * 4;
        game.camera.yPos += inputHandler.cameraDir.y * 4;

        // first check for interactions (replace with event system if exists) TODO

        for (Man boy : game.boys) {
            if (boy.bounds.inBounds(inputHandler.clickPoint) == Bound.CollisionCheckResponse.TRUE) {
                inputHandler.clickFlag = InputHandler.ClickFlag.ENTITY; // flag check (possibly make more evident)

                game.detailsWindow.activeBoy = boy.activated ? null : boy;

                boy.interact();
            }
        }

        if (game.optionsWindow.bounds.inBounds(inputHandler.clickPoint) == Bound.CollisionCheckResponse.TRUE && game.optionsWindow.active) {
            inputHandler.clickFlag = InputHandler.ClickFlag.UI;
            game.optionsWindow.interact();
        }

        if (game.detailsWindow.bounds.inBounds(inputHandler.clickPoint) == Bound.CollisionCheckResponse.TRUE && game.detailsWindow.active) {
            inputHandler.clickFlag = InputHandler.ClickFlag.UI;
            game.detailsWindow.interact();
        }

        // now update behaviour

        for (Man boy : game.boys) {
            boy.update();
        }

        game.optionsWindow.update();
        game.detailsWindow.update();
    }

    public void draw(Graphics2D g2) {
        gameRenderer.drawMap(g2);

        for (Man boy : game.boys) {
            boy.draw(g2, game);
        }

        // draw UI after everything
        if (game.optionsWindow.active) {
            game.optionsWindow.draw(g2);
        }

        if (game.detailsWindow.active) {
            game.detailsWindow.draw(g2);
        }
    }
}
