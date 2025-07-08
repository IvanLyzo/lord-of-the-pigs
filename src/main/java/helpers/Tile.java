package helpers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {

    public static final int TILESIZE = 64;
    public static int scaledTileSize = 64; // 16px x 16px
    public static final int itemSize = 32;

    public final int x;
    public final int y;

    public final double height;

    public Type type;

    public Tile(int x, int y, double height, Type type) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.type = type;
    }

    public enum Type {
        LAND(true, 0.15, "/tiles/grass.png"),
        SAND(true, Color.YELLOW),
        WATER(false, Color.BLUE);

        public final boolean land;
        public final double minHeight;

        public BufferedImage sprite;
        public Color color;

        Type(boolean land, double minHeight, String spriteLoc) {
            this.land = land;
            this.minHeight = minHeight;

            try {
                sprite = ImageIO.read(getClass().getResourceAsStream(spriteLoc));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Type(boolean land, Color color) {
            this.land = land;
            this.color = color;
            this.minHeight = -1;
        }
    }
}
