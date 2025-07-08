package main;

import helpers.NoiseGenerator;
import helpers.Renderer;
import helpers.Tile;
import input.InputHandler;

import java.awt.*;

public class Map {

    // NEW MAP GEN FIELDS (part of future mapGen class) TODO
    public final int tileMapSize = 64;

    public final int width = tileMapSize * Tile.TILESIZE;
    public final int height = tileMapSize * Tile.TILESIZE;

    public Tile[][] tileMap;

    NoiseGenerator perlin;

    public Map(int seed) {
        // messy way of settings bounds for map
//        bounds = new Rectangle(0, 0, tileWidth * Tile.TILESIZE, tileHeight * Tile.TILESIZE);

        perlin = new NoiseGenerator(seed);

        generateTileMap();
    }

    private void generateTileMap() {
        tileMap = new Tile[tileMapSize][tileMapSize];

        for (int x = 0; x < tileMapSize; x++) {
            for (int y = 0; y < tileMapSize; y++) {
                tileMap[x][y] = new Tile(x, y, 0, Tile.Type.WATER);
            }
        }

        int sandRadius = 3;

        for (int y = 0; y < tileMapSize; y++) {
            for (int x = 0; x < tileMapSize; x++) {

                // Centered coordinates from -1 to 1
                double nx = (2.0 * x / tileMapSize) - 1.0;
                double ny = (2.0 * y / tileMapSize) - 1.0;

                // Distance from center
                double distance = Math.sqrt(nx * nx + ny * ny); // [0, ~1.414]
                double falloff = Math.pow(distance, 3); // exponent to sharpen edges

                // Get Perlin noise
                double noiseValue = perlin.noise(x, y, 0); // range ~[-1,1]
                noiseValue = (noiseValue + 1) / 2; // Normalize to [0,1]

                // Island shaping
                double finalValue = noiseValue - falloff;

                // Threshold to define land
                for (Tile.Type type : Tile.Type.values()) {
                    if (type.minHeight == -1) {
                        continue;
                    }

                    if (finalValue > type.minHeight) {
                        tileMap[x][y] = new Tile(x, y, finalValue, type);
                        break;
                    }
                }

                if (x > sandRadius * 2 && y > sandRadius * 2) {
                    sandCheck(x, y, sandRadius);
                }
            }
        }
    }

    private void sandCheck(int x, int y, int sandRadius) {
        int r = sandRadius;

        // TODO make smarter to handle corners with radius of 2
        while (r > 1) {
            if ((!tileMap[x - r * 2][y - r].type.land || !tileMap[x][y - r].type.land
                    || !tileMap[x - r][y - r * 2].type.land || !tileMap[x - r][y].type.land) && tileMap[x - r][y - r].type.land) {
                tileMap[x - r][y - r].type = Tile.Type.SAND;
            }

            r--;
        }
    }

    public void update() {

    }

    public Tile getTile(Point p) {
        int xTile = p.x / Tile.TILESIZE;
        int yTile = p.y / Tile.TILESIZE;

        return tileMap[xTile][yTile];
    }

    public void interact(InputHandler.ClickType clickType) {

    }

    public void draw(Graphics2D g, Game game) {
        // draw map
        int firstX = Math.max(game.camera.view.x / Tile.TILESIZE - 2, 0);
        int firstY = Math.max(game.camera.view.y / Tile.TILESIZE - 2, 0);

        int lastX = Math.min(firstX + (Math.round((float) game.camera.view.width / Tile.TILESIZE + 0.49f)) + 4, tileMapSize);
        int lastY = Math.min(firstY + (Math.round((float) game.camera.view.height / Tile.TILESIZE + 0.49f)) + 4, tileMapSize);

        for (int x = firstX; x < lastX; x++) {
            for (int y = firstY; y < lastY; y++) {
                Rectangle tile = new Rectangle(x * Tile.TILESIZE, y * Tile.TILESIZE, Tile.TILESIZE, Tile.TILESIZE);

                if (tileMap[x][y].type.sprite != null) {
                    Renderer.drawEntity(g, game, tile, tileMap[x][y].type.sprite);
                    continue;
                }

                Renderer.drawEntity(g, game, tile, tileMap[x][y].type.color);
            }
        }
    }
}
