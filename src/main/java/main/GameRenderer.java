package main;

import helpers.Bound;
import helpers.Renderer;
import helpers.Tile;

import java.awt.*;

// maybe have a Map class and remove gameRenderer to have individual classes draw themselves as with update (away from MVC) TODO
public class GameRenderer {

    private final Game game;

    public GameRenderer(Game game) {
        this.game = game;
    }

    public void drawMap(Graphics2D g) {
        int firstX = Math.max(game.camera.xPos / Tile.TILESIZE - 2, 0);
        int firstY = Math.max(game.camera.yPos / Tile.TILESIZE - 2, 0);

        int lastX = Math.min(firstX + (Math.round((float) game.camera.width / Tile.TILESIZE + 0.49f)) + 4, game.map.length);
        int lastY = Math.min(firstY + (Math.round((float) game.camera.height / Tile.TILESIZE+ 0.49f)) + 4, game.map[0].length);

        for (int x = firstX; x < lastX; x++) {
            for (int y = firstY; y < lastY; y++) {
                Bound tile = new Bound(x * Tile.TILESIZE, y * Tile.TILESIZE, Tile.TILESIZE, Tile.TILESIZE);
                Renderer.drawEntity(g, game, tile, Color.GREEN);
            }
        }
    }
}
