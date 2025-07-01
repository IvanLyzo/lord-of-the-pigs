package main;

import helpers.Tile;
import model.Man;

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

    public void drawDetailsWindow(Graphics2D g, Man active) {
        g.setColor(Color.BLACK);

        g.fillRect(100, 500, game.screenWidth - 200, game.screenHeight - 600);
    }

    public void drawEscapeWindow(Graphics2D g) {
        g.setColor(Color.BLACK);

        g.fillRect(300, 300, game.screenWidth - 600, game.screenHeight - 600);
    }
}
