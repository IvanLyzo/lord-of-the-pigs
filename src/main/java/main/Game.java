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

    // SCREEN SETTINGS
    public final int tileScreenWidth = 40;
    public final int tileScreenHeight = 30;

    public int screenWidth = tileScreenWidth * Tile.TILESIZE;
    public int screenHeight = tileScreenHeight * Tile.TILESIZE;

    // WINDOWS
    public OptionsWindow optionsWindow;
    public DetailsWindow detailsWindow;

    public Map map;

    public List<Man> boys;

    public Bound camera;

    public boolean pauseMode = false;

    public Game(int seed, InputHandler inputHandler) {
        map = new Map(seed);

        boys = new ArrayList<>();
        optionsWindow = new OptionsWindow(new Bound(300, 300, screenWidth - 600, screenHeight - 600), KeyEvent.VK_O, inputHandler);
        detailsWindow = new DetailsWindow(new Bound(100, 500, screenWidth - 200, screenHeight - 600), KeyEvent.VK_E, inputHandler);

        camera = new Bound(-screenWidth / 2, -screenHeight / 2, screenWidth, screenHeight);
    }
}
