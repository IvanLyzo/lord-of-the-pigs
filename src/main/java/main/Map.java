package main;

import helpers.NoiseGenerator;
import helpers.Renderer;
import helpers.Tile;
import input.InputHandler;
import model.base.GameObject;

import java.awt.*;
import java.util.Random;

public class Map extends GameObject {

    // NEW MAP GEN FIELDS (part of future mapGen class) TODO
    public final int tileWidth = 50;
    public final int tileHeight = 50;

    public Tile[][] tileMap;

    NoiseGenerator perlin;

    public Map(int seed) {
        // messy way of settings bounds for map
        super(new Rectangle(0, 0, 0, 0));
        bounds = new Rectangle(0, 0, tileWidth * Tile.TILESIZE, tileHeight * Tile.TILESIZE);

        perlin = new NoiseGenerator(seed);

        generateTileMap();
    }

    public void generateTileMap() {
        tileMap = new Tile[tileWidth][tileHeight];

        for (int x = 0; x < tileWidth; x++) {
            for (int y = 0; y < tileHeight; y++) {
                tileMap[x][y] = new Tile(x, y, 0, Tile.Type.WATER);
            }
        }

        int sandRadius = new Random().nextInt(2) + 1;

        for (int y = 0; y < tileWidth; y++) {
            for (int x = 0; x < tileHeight; x++) {

                // Centered coordinates from -1 to 1
                double nx = (2.0 * x / tileWidth) - 1.0;
                double ny = (2.0 * y / tileHeight) - 1.0;

                // Distance from center
                double distance = Math.sqrt(nx * nx + ny * ny); // [0, ~1.414]
                double falloff = Math.pow(distance, 5); // exponent to sharpen edges

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
                    check(x, y, sandRadius);
                }
            }
        }
    }

    private void check(int x, int y, int sandRadius) {
        // check for isolated land tiles
        if (!tileMap[x - 2][y].type.land && !tileMap[x][y].type.land && !tileMap[x][y - 2].type.land) {
            tileMap[x - 1][y - 1].type = Tile.Type.WATER;
        }

        // check for water tiles amongst land tiles
        if (tileMap[x - 2][y].type.land && tileMap[x][y].type.land) {
            tileMap[x - 1][y].type = Tile.Type.LAND;
        }
        if (tileMap[x][y - 2].type.land && tileMap[x][y].type.land) {
            tileMap[x][y - 1].type = Tile.Type.LAND;
        }

        // check for sand
        // TODO make smarter to handle corners with radius of 2
        if ((!tileMap[x - sandRadius * 2][y - sandRadius].type.land || !tileMap[x][y - sandRadius].type.land
                || !tileMap[x - sandRadius][y - sandRadius * 2].type.land || !tileMap[x - sandRadius][y].type.land) && tileMap[x - sandRadius][y - sandRadius].type.land) {
            tileMap[x - sandRadius][y - sandRadius].type = Tile.Type.SAND;
        } else if (sandRadius > 1) {
            check(x, y, sandRadius - 1);
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
        // draw map
        int firstX = Math.max(game.camera.view.x / Tile.TILESIZE - 2, 0);
        int firstY = Math.max(game.camera.view.y / Tile.TILESIZE - 2, 0);

        int lastX = Math.min(firstX + (Math.round((float) game.camera.view.width / Tile.TILESIZE + 0.49f)) + 4, tileWidth);
        int lastY = Math.min(firstY + (Math.round((float) game.camera.view.height / Tile.TILESIZE + 0.49f)) + 4, tileHeight);

        for (int x = firstX; x < lastX; x++) {
            for (int y = firstY; y < lastY; y++) {
                Rectangle tile = new Rectangle(x * Tile.TILESIZE, y * Tile.TILESIZE, Tile.TILESIZE, Tile.TILESIZE);
                Renderer.drawEntity(g, game, tile, tileMap[x][y].type.color);
            }
        }
    }
}
