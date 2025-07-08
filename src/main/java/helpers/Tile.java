package helpers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {

    public static final int TILESIZE = 32;
    public static int scaledTileSize = 32; // 16px x 16px
    public static final int itemSize = 16;

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
        LAND(true, 0.85, "/tiles/grass.png"),
        SAND(true, 0.80, "/tiles/sand.png"),
        SHALLOW_WATER(false, 0.75, "/tiles/shallow_water.png"),
        TRANSITION_WATER(false, 0.5, "/tiles/transition_water.png"),
        DEEP_WATER(false, -1, "/tiles/deep_water.png");

        public final boolean land;
        public final double minHeight;
        public final BufferedImage sprite;

        Type(boolean land, double minHeight, String spriteLoc) {
            this.land = land;
            this.minHeight = minHeight;

            try {
                sprite = ImageIO.read(getClass().getResourceAsStream(spriteLoc));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
