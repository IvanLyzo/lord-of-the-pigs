package main;

import helpers.NoiseGenerator;
import helpers.Renderer;
import helpers.Tile;
import input.InputHandler;

import java.awt.*;

public class Map {

    // NEW MAP GEN FIELDS (part of future mapGen class) TODO
    public final int tileMapSize = 128;

    public final int width = tileMapSize * Tile.TILESIZE;
    public final int height = tileMapSize * Tile.TILESIZE;

    public double[][] heightMap;
    public Tile[][] tileMap;

    public Map(int seed) {
        NoiseGenerator perlin = new NoiseGenerator(seed);
        generateHeightMap(perlin);

        generateTileMap(perlin);
    }

    private void generateHeightMap(NoiseGenerator perlin) {
        heightMap = new double[tileMapSize][tileMapSize];

        double min = 0;
        double max = 0;

        for (int y = 0; y < tileMapSize; y++) {
            for (int x = 0; x < tileMapSize; x++) {

                // Centered coordinates from -1 to 1
                double nx = (2.0 * x / tileMapSize) - 1.0;
                double ny = (2.0 * y / tileMapSize) - 1.0;

                // Distance from center
                double distance = Math.sqrt(nx * nx + ny * ny); // [0, ~1.414]
                double falloff = (double) (int) (Math.pow(distance, 4) * 100) / 100; // exponent to sharpen edges

                // Get Perlin noise
                double noiseValue = perlin.noise(x, y, 0); // range ~[-1,1]
                noiseValue = (noiseValue + 1) / 2; // Normalize to [0,1]

                noiseValue = Math.clamp(noiseValue, 0, 1);

                // Island shaping
                double finalValue = noiseValue - falloff;
                heightMap[x][y] = finalValue;

                if (finalValue > max) {
                    max = finalValue;
                }

                if (finalValue < min) {
                    min = finalValue;
                }
            }
        }

        for (int y = 0; y < tileMapSize; y++) {
            for (int x = 0; x < tileMapSize; x++) {
                heightMap[x][y] = (heightMap[x][y] - min) / (max - min);
            }
        }
    }

    private void generateTileMap(NoiseGenerator perlin) {
        tileMap = new Tile[tileMapSize][tileMapSize];

        for (int y = 0; y < tileMapSize; y++) {
            for (int x = 0; x < tileMapSize; x++) {

                // Threshold to define land
                for (Tile.Type type : Tile.Type.values()) {
                    if (type.minHeight == -2) {
                        continue;
                    }

                    if (heightMap[x][y] > type.minHeight) {
                        tileMap[x][y] = new Tile(x, y, heightMap[x][y], type);
                        break;
                    }
                }
            }
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

                Renderer.drawEntity(g, game, tile, tileMap[x][y].type.sprite);
            }
        }
    }
}
