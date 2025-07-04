package main;

import helpers.Bound;
import helpers.Camera;
import input.InputHandler;
import model.Man;
import ui.DetailsWindow;
import ui.OptionsWindow;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private final int seed;

    // WINDOWS
    public OptionsWindow optionsWindow;
    public DetailsWindow detailsWindow;

    public Map map;

    public List<Man> boys;

    public Camera camera;

    public boolean pauseMode = false;

    public Game( int seed, InputHandler inputHandler) {
        this.seed = seed;
        map = new Map();
        camera = new Camera(this, inputHandler);

        optionsWindow = new OptionsWindow(new Bound(300, 300, camera.screenWidth - 600, camera.screenHeight - 600), KeyEvent.VK_O, inputHandler);
        detailsWindow = new DetailsWindow(new Bound(100, 500, camera.screenWidth - 200, camera.screenHeight - 600), KeyEvent.VK_E, inputHandler);

        Random r = new Random(seed);

        boys = new ArrayList<>();
        boys.add(generateBoy(inputHandler, r));
        boys.add(generateBoy(inputHandler, r));
    }

    private Man generateBoy(InputHandler inputHandler, Random r) {
        Point spawnPoint = new Point(-50, -50);
        while (map.bounds.inBounds(spawnPoint) == Bound.CollisionCheckResponse.FALSE) {
            spawnPoint = new Point(map.bounds.xPos + r.nextInt(150), map.bounds.yPos + r.nextInt(150));
        }

        return new Man(spawnPoint, inputHandler);
    }
}