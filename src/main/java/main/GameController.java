package main;

import helpers.Bound;
import helpers.Tile;
import input.InputHandler;
import model.Man;

import java.awt.*;

public class GameController {

    public Game game;

    private final InputHandler inputHandler;

    public GameController(int seed, InputHandler inputHandler) {
        game = new Game(seed, inputHandler);

        this.inputHandler = inputHandler;
        inputHandler.game = game;
    }

    public void interact() {
        for (Man boy : game.boys) {
            if (boy.bounds.inBounds(inputHandler.clickPoint) == Bound.CollisionCheckResponse.TRUE) {
                inputHandler.clickFlag = InputHandler.ClickFlag.ENTITY; // flag check (possibly make more evident)

                game.detailsWindow.activeBoy = boy.activated ? null : boy;

                boy.interact(inputHandler.clickType);
            }
        }

        if (game.optionsWindow.bounds.inBounds(inputHandler.clickPoint) == Bound.CollisionCheckResponse.TRUE && game.optionsWindow.active) {
            inputHandler.clickFlag = InputHandler.ClickFlag.UI;
            game.optionsWindow.interact(inputHandler.clickType);
        }

        if (game.detailsWindow.bounds.inBounds(inputHandler.clickPoint) == Bound.CollisionCheckResponse.TRUE && game.detailsWindow.active) {
            inputHandler.clickFlag = InputHandler.ClickFlag.UI;
            game.detailsWindow.interact(inputHandler.clickType);
        }
    }

    public void update() {
        for (Man boy : game.boys) {
            boy.update();
        }

        game.optionsWindow.update();
        game.detailsWindow.update();

        game.camera.move();
    }

    public void draw(Graphics2D g2) {
        game.map.draw(g2, game);

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
