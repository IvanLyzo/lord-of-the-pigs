package main;

import model.base.Tile;
import input.InputHandler;
import model.Conch;
import model.Boy;
import model.base.Entity;
import model.base.GameObject;
import model.base.Item;
import ui.DetailsWindow;
import ui.OptionsWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private final int seed;

    // WINDOWS
    public OptionsWindow optionsWindow;
    public DetailsWindow detailsWindow;

    // important singleton objects
    public Map map;
    public Camera camera;

    // all game objects
    public List<GameObject> environment;
    public List<Item> items;
    public List<Entity> entities;

    public Point worldSpawn;

    private final Random r;

    // make order of game generation more obvious and actually think it through this time TODO
    public Game(int seed, InputHandler inputHandler) {
        this.seed = seed;

        map = new Map(seed);
        camera = new Camera(this, inputHandler);

        r = new Random(seed);

        environment = new ArrayList<>();
        genEnvironment();

        entities = new ArrayList<>();
        genEntities(inputHandler);

        items = new ArrayList<>();
        genItems();

        genUI(inputHandler);
    }

    private void genEnvironment() {
//        Tree tree = new Tree(new Point(map.width / 2, map.height / 2));
//        environment.add(tree);
    }

    private void genEntities(InputHandler inputHandler) {
        worldSpawn = new Point(r.nextInt(map.width), r.nextInt(map.height));
        while (map.getTile(worldSpawn).type != Tile.Type.SAND) {
            worldSpawn.x = r.nextInt(map.width);
            worldSpawn.y = r.nextInt(map.height);
        }

        entities.add(genBoy(inputHandler, r));
        entities.add(genBoy(inputHandler, r));

        camera.teleport(worldSpawn);
    }

    // TODO: random sand tiles not necessarily beach and can be anywhere; fix this
    private Boy genBoy(InputHandler inputHandler, Random r) {
        Point spawn = new Point(worldSpawn.x + r.nextInt(41) - 20, worldSpawn.y + r.nextInt(41) - 20);

        while (map.getTile(spawn).type != Tile.Type.SAND) {
            int x = r.nextInt(41) - 20;
            int y = r.nextInt(41) - 20;

            spawn = new Point(worldSpawn.x + x, worldSpawn.y + y);
        }

        return new Boy(this, spawn, inputHandler);
    }

    private void genItems() {
        Point spawn = new Point(worldSpawn.x + r.nextInt(41) - 20, worldSpawn.y + r.nextInt(41) - 20);

        while (map.getTile(spawn).type != Tile.Type.SAND) {
            int x = r.nextInt(101) - 50;
            int y = r.nextInt(101) - 50;

            spawn = new Point(worldSpawn.x + x, worldSpawn.y + y);
        }

        Conch conch = new Conch(this, spawn);
        items.add(conch);
    }

    private void genUI(InputHandler inputHandler) {
        Rectangle screen = new Rectangle(0, 0, camera.screenWidth, camera.screenHeight);

        optionsWindow = new OptionsWindow(this, inputHandler);
        detailsWindow = new DetailsWindow(this, inputHandler);
    }
}