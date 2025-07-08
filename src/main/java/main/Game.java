package main;

import helpers.Camera;
import input.InputHandler;
import model.Conch;
import model.Boy;
import model.base.Entity;
import model.base.GameObject;
import model.base.Item;
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

    public List<GameObject> environment;
    public List<Item> items;
    public List<Entity> entities;

    public Camera camera;

    public boolean pauseMode = false;

    // make order of game generation more obvious and actually think it through this time TODO
    public Game( int seed, InputHandler inputHandler) {
        this.seed = seed;
        map = new Map(seed);
        camera = new Camera(this, inputHandler);

        optionsWindow = new OptionsWindow(new Rectangle(300, 300, camera.screenWidth - 600, camera.screenHeight - 600), KeyEvent.VK_O, inputHandler);
        detailsWindow = new DetailsWindow(new Rectangle(100, 500, camera.screenWidth - 200, camera.screenHeight - 600), KeyEvent.VK_E, inputHandler);

        Random r = new Random(seed);

        environment = new ArrayList<>();
        generateEnvironment(seed);

        items = new ArrayList<>();
        generateItems(seed);

        Point spawnPoint = new Point(r.nextInt(1000) + 750, r.nextInt(1000) + 750);

        entities = new ArrayList<>();
        entities.add(generateBoy(inputHandler, r, spawnPoint));
        entities.add(generateBoy(inputHandler, r, spawnPoint));
    }

    private void generateEnvironment(int seed) {
//        Tree tree = new Tree(new Point(map.width / 2, map.height / 2));
//        environment.add(tree);
    }

    private void generateItems(int seed) {
        Conch conch = new Conch(new Point(map.width / 2, map.height / 2));
        items.add(conch);
    }

    private Boy generateBoy(InputHandler inputHandler, Random r, Point coreSpawn) {
        Point offset = new Point(r.nextInt(41) - 20, r.nextInt(41) - 20);

        Point spawn = new Point(coreSpawn.x + offset.y, coreSpawn.y + offset.y);
//        while (map.getTile(spawn).type != Tile.Type.LAND) {
//            offset.x = r.nextInt(41) - 20;
//            offset.y = r.nextInt(41) - 20;
//
//            spawn.x = coreSpawn.x + offset.x;
//            spawn.y = coreSpawn.y + offset.y;
//        }

        return new Boy(spawn, inputHandler);
    }
}