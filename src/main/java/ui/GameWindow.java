package ui;

import helpers.ResHandler;
import input.InputHandler;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameWindow {

    public final Game game;
    public final InputHandler inputHandler;

    public final Rectangle bounds;
    public final Rectangle parent;

    public int keyCode;

    public BufferedImage bg;
    public BufferedImage border;
    public BufferedImage border_corner;

    public Font header;
    public Font normal;

    public boolean active = false;

    public GameWindow(Game game, InputHandler inputHandler, Rectangle bounds, Rectangle parent, int keyCode) {
        this.game = game;
        this.inputHandler = inputHandler;
        this.bounds = bounds;
        this.parent = parent != null ? parent : new Rectangle(0, 0, game.camera.screenWidth, game.camera.screenHeight);
        this.keyCode = keyCode;

        bg = ResHandler.getSprite("/ui/bg.png");
        border = ResHandler.getSprite("/ui/border.png");
        border_corner = ResHandler.getSprite("/ui/border_corner.png");

        header = ResHandler.getFont("/font/ByteBounce.ttf").deriveFont(Font.PLAIN, 100f);
        normal = ResHandler.getFont("/font/ByteBounce.ttf").deriveFont(Font.PLAIN, 32f);
    }

    public Rectangle getAbsBounds() {
        return new Rectangle(
                parent.x + bounds.x * parent.width / 100,
                parent.y + bounds.y * parent.height / 100,
                bounds.width * parent.width / 100,
                bounds.height * parent.height / 100
        );
    }

    public void interact(InputHandler.ClickType clickType) {

    }

    public void update() {

    }

    public void draw(Graphics2D g) {
        Rectangle b = getAbsBounds();

        g.drawImage(bg, b.x, b.y, b.width, b.height, null);
        drawBorder(g, b);
    }

    protected void drawBorder(Graphics2D g, Rectangle b) {
        int size = border.getHeight();

        BufferedImage str = border;
        BufferedImage crn = border_corner;

        g.drawImage(str, b.x + size, b.y, b.width - size * 2, size, null);
        g.drawImage(crn, b.x + b.width - size, b.y, size, size, null);

        str = ResHandler.rotateSprite(str);
        crn = ResHandler.rotateSprite(crn);

        g.drawImage(str, b.x + b.width - size, b.y + size, size, b.height - size * 2, null);
        g.drawImage(crn, b.x + b.width - size, b.y + b.height - size, size, size, null);

        str = ResHandler.rotateSprite(str);
        crn = ResHandler.rotateSprite(crn);

        g.drawImage(str, b.x + size, b.y + b.height - size, b.width - size * 2, size, null);
        g.drawImage(crn, b.x, b.y + b.height - size, size, size, null);

        str = ResHandler.rotateSprite(str);
        crn = ResHandler.rotateSprite(crn);

        g.drawImage(str, b.x, b.y + size, size, b.height - size * 2, null);
        g.drawImage(crn, b.x, b.y, size, size, null);
    }
}
