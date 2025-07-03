package main;

import helpers.Tile;

import java.awt.*;

public class GameRenderer {

    private final Game game;

    public GameRenderer(Game game) {
        this.game = game;
    }

    public void drawMap(Graphics2D g, Tile[][] map) {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                g.setColor(Color.GREEN);
                g.fillRect(x * Tile.TILESIZE, y * Tile.TILESIZE, Tile.TILESIZE, Tile.TILESIZE);
            }
        }
    }
}
