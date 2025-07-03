package main;

import helpers.Bound;
import helpers.Tile;
import input.InputHandler;
import model.Man;
import ui.DetailsWindow;
import ui.OptionsWindow;

import java.awt.event.KeyEvent;
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
    // WINDOWS
    public OptionsWindow optionsWindow;
    public DetailsWindow detailsWindow;

    public Tile[][] map;

    public List<Man> boys;

    public boolean pauseMode = false;

    public Game(int seed, InputHandler inputHandler) {
        this.seed = seed;

        map = new Tile[mapTileWidth][mapTileHeight];
        boys = new ArrayList<>();
        optionsWindow = new OptionsWindow(new Bound(300, 300, screenWidth - 600, screenHeight - 600), KeyEvent.VK_O, inputHandler);
        detailsWindow = new DetailsWindow(new Bound(100, 500, screenWidth - 200, screenHeight - 600), KeyEvent.VK_E, inputHandler);
    }
}
