package main;

import input.InputHandler;
import model.Boy;
import model.base.Entity;
import model.base.GameObject;
import model.base.Item;

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
        if (inputHandler.clickPoint == null) {
            return;
        }

        // environment interaction iteration
        for (GameObject obj : game.environment) {
            if (obj.bounds.contains(inputHandler.clickPoint)) {
                inputHandler.clickFlag = InputHandler.ClickFlag.ENVIRONMENT; // flag check (possibly make more evident)
                inputHandler.clickObj = obj;

                obj.interact(inputHandler.clickType); // call entity interact
            }
        }

        // entity interaction iteration
        for (Entity entity : game.entities) {
            if (entity.bounds.contains(inputHandler.clickPoint)) {
                inputHandler.clickFlag = InputHandler.ClickFlag.ENTITY; // flag check (possibly make more evident)
                inputHandler.clickObj = entity;

                entity.interact(inputHandler.clickType); // call entity interact

                if (entity.getClass() == Boy.class) {
                    game.detailsWindow.activeBoy = ((Boy) entity).activated ? (Boy) entity : null;
                }
            }
        }

        // item interaction iteration
        for (Item item : game.items) {
            if (item.bounds.contains(inputHandler.clickPoint)) {
                inputHandler.clickFlag = InputHandler.ClickFlag.ITEM; // flag check (possibly make more evident)
                inputHandler.clickObj = item;

                item.interact(inputHandler.clickType); // call entity interact
            }
        }

        // ui interactions
        if (game.optionsWindow.bounds.contains(inputHandler.clickPoint) && game.optionsWindow.active) {
            inputHandler.clickFlag = InputHandler.ClickFlag.UI;
            inputHandler.clickObj = game.optionsWindow;

            game.optionsWindow.interact(inputHandler.clickType);
        }

        if (game.detailsWindow.bounds.contains(inputHandler.clickPoint) && game.detailsWindow.active) {
            inputHandler.clickFlag = InputHandler.ClickFlag.UI;
            inputHandler.clickObj = game.detailsWindow;

            game.detailsWindow.interact(inputHandler.clickType);
        }
    }

    public void update() {
        for (GameObject object : game.environment) {
            object.update();
        }

        for (Entity entity : game.entities) {
            entity.update();
        }

        for (Item item : game.items) {
            item.update();
        }

        game.optionsWindow.update();
        game.detailsWindow.update();

        game.camera.move();
    }

    public void draw(Graphics2D g2) {
        game.map.draw(g2, game);

        for (GameObject object : game.environment) {
            object.draw(g2, game);
        }

        for (Entity entity : game.entities) {
            entity.draw(g2, game);
        }

        for (Item item : game.items) {
            item.draw(g2, game);
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
