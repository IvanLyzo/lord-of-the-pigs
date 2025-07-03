package main;

import helpers.Bound;
import helpers.Renderer;
import helpers.Tile;

import java.awt.*;

public class GameRenderer {

    private final Game game;
    private final Renderer renderer;

    public GameRenderer(Game game, Renderer renderer) {
        this.game = game;
        this.renderer = renderer;
    }

    public void drawMap(Graphics2D g) {
        int firstX = Math.max(game.camera.xPos / Tile.TILESIZE - 2, 0);
        int firstY = Math.max(game.camera.yPos / Tile.TILESIZE - 2, 0);

        int lastX = Math.min(firstX + (Math.round((float) game.camera.width / Tile.TILESIZE + 0.49f)) + 4, game.map.length);
        int lastY = Math.min(firstY + (Math.round((float) game.camera.height / Tile.TILESIZE+ 0.49f)) + 4, game.map[0].length);

        for (int x = firstX; x < lastX; x++) {
            for (int y = firstY; y < lastY; y++) {
                Bound tile = new Bound(x * Tile.TILESIZE, y * Tile.TILESIZE, Tile.TILESIZE, Tile.TILESIZE);
                renderer.draw(g, tile, Color.GREEN);
            }
        }
    }
}
