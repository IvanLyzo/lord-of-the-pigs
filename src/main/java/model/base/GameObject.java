package model.base;

import helpers.Camera;
import helpers.Renderer;
import input.InputHandler;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameObject {

    public Rectangle bounds;

    public int centerX() {
        return bounds.x + bounds.width / 2;
    }
    public int centerY() {
        return bounds.y + bounds.height / 2;
    }

    // old code for generating with fillRect()
    public Color drawColor;

    // new code for sprite rendering
    public BufferedImage sprite;

    public GameObject(Rectangle bounds) {
        this.bounds = bounds;
    }

    public GameObject(Rectangle bounds, String spriteLoc) {
        this(bounds);

        try {
            sprite = ImageIO.read(getClass().getResourceAsStream(spriteLoc));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {

    }

    public void interact(InputHandler.ClickType clickType) {

    }

    public void draw(Graphics2D g, Game game) {
        if (sprite != null) {
            Renderer.drawEntity(g, game, bounds, sprite);
            return;
        }

        Renderer.drawEntity(g, game, bounds, drawColor);
    }
}
