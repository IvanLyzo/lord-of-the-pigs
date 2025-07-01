package main;

import helpers.Bound;
import helpers.Tile;
import model.Man;

import java.util.ArrayList;
import java.util.List;

public class Game {

    // WORLD SETTINGS
    private final int seed;

    public final int mapTileWidth = 100;
    public final int mapTileHeight = 100;

    // SCREEN SETTINGS
    public final int tileScreenWidth = 40;
    public final int tileScreenHeight = 30;

    public int screenWidth = tileScreenWidth * Tile.TILESIZE;
    public int screenHeight = tileScreenHeight * Tile.TILESIZE;

    // Default boundaries (in the future can change with some settings) TODO
    Bound escapeWindow = new Bound(300, 300, screenWidth - 600, screenHeight - 600);

    public Tile[][] map;

    public List<Man> boys;
    public Man active;

    public boolean pauseMode = false;

    public Game(int seed) {
        this.seed = seed;

        map = new Tile[mapTileWidth][mapTileHeight];
        boys = new ArrayList<>();
    }
}
