package main;

import helpers.Bound;
import helpers.Camera;
import input.InputHandler;
import model.Man;
import ui.DetailsWindow;
import ui.OptionsWindow;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Game {

    // WINDOWS
    public OptionsWindow optionsWindow;
    public DetailsWindow detailsWindow;

    public Map map;

    public List<Man> boys;

    public Camera camera;

    public boolean pauseMode = false;

    public Game(int seed, InputHandler inputHandler) {
        map = new Map(seed);
        camera = new Camera(this, inputHandler);

        boys = new ArrayList<>();
        optionsWindow = new OptionsWindow(new Bound(300, 300, camera.screenWidth - 600, camera.screenHeight - 600), KeyEvent.VK_O, inputHandler);
        detailsWindow = new DetailsWindow(new Bound(100, 500, camera.screenWidth - 200, camera.screenHeight - 600), KeyEvent.VK_E, inputHandler);
    }
}
