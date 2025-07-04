package main;

import helpers.Bound;
import helpers.Renderer;
import helpers.Tile;
import input.InputHandler;
import model.base.GameObject;

import java.awt.*;

public class Map extends GameObject {

    // WORLD SETTINGS
    private final int seed;

    public final int tileWidth = 100;
    public final int tileHeight = 100;

    public final int seaPadding = 10;

    public Tile[][] tileMap;

    public Map(int seed) {
        // messy way of settings bounds for map
        super(new Bound(0, 0, 0, 0));

        bounds = new Bound(0, 0, tileWidth * Tile.TILESIZE, tileHeight * Tile.TILESIZE);
        this.seed = seed;

        generateTileMap();
    }

    private void generateTileMap() {
        tileMap = new Tile[tileWidth][tileHeight];

        for (int x = 0; x < tileWidth; x++) {
            for (int y = 0; y < tileHeight; y++) {
                tileMap[x][y] = new Tile(x, y, Tile.Type.LAND);
            }
        }
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void interact(InputHandler.ClickType clickType) {
        super.interact(clickType);
    }

    @Override
    public void draw(Graphics2D g, Game game) {
        // draw water behind
        Renderer.drawEntity(g, game, game.camera.viewLimit, Color.BLUE);

        // draw map
        int firstX = Math.max(game.camera.view.xPos / Tile.TILESIZE - 2, 0);
        int firstY = Math.max(game.camera.view.yPos / Tile.TILESIZE - 2, 0);

        int lastX = Math.min(firstX + (Math.round((float) game.camera.view.width / Tile.TILESIZE + 0.49f)) + 4, tileWidth);
        int lastY = Math.min(firstY + (Math.round((float) game.camera.view.height / Tile.TILESIZE+ 0.49f)) + 4, tileHeight);

        for (int x = firstX; x < lastX; x++) {
            for (int y = firstY; y < lastY; y++) {
                Bound tile = new Bound(x * Tile.TILESIZE, y * Tile.TILESIZE, Tile.TILESIZE, Tile.TILESIZE);
                Renderer.drawEntity(g, game, tile, Color.GREEN);
            }
        }
    }
}
