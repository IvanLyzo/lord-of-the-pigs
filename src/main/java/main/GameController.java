package main;

import helpers.Tile;
import input.InputHandler;
import model.Man;

import java.awt.*;

public class GameController {

    public Game game;

    private final GameRenderer gameRenderer;
    private final InputHandler inputHandler;

    public GameController(int seed, InputHandler inputHandler) {
        game = new Game(seed);
        this.inputHandler = inputHandler;
        gameRenderer = new GameRenderer(game);

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
        for (Man boy : game.boys) {
            boy.update();

            if (boy.bounds.inBounds(inputHandler.clickPoint)) {
                if (game.active == boy) {
                    game.active = null;

                    boy.activated = false;
                    boy.drawColor = Color.WHITE;
                } else {
                    makeBoyActive(boy);
                }
            }
        }

        if (game.pauseMode) {
            if (inputHandler.clickPoint != null) {
                if (!game.escapeWindow.inBounds(inputHandler.clickPoint)) {
                    game.pauseMode = false;
                }
                if (inputHandler.oPressed) {
                    game.pauseMode = false;
                }
            }
        } else {
            if (inputHandler.oPressed) {
                game.pauseMode = true;
                System.out.println("entered pause mode");
            }
        }
    }

    private void makeBoyActive(Man boy) {
        game.active = boy;

        boy.activated = true;
        boy.drawColor = Color.BLUE;
    }

    public void draw(Graphics2D g2) {
        gameRenderer.drawMap(g2, game.map);

        for (Man boy : game.boys) {
            boy.draw(g2);
        }

        // draw UI after everything
        if (game.active != null) {
            gameRenderer.drawDetailsWindow(g2, game.active);
        }

        if (game.pauseMode) {
            gameRenderer.drawEscapeWindow(g2);
        }
    }
}
