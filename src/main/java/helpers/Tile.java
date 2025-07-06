package helpers;

import java.awt.*;

public class Tile {

    public static final int TILESIZE = 32; // 16px x 16px

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
        LAND(true, 0.15, Color.GREEN),
        SAND(true, Color.YELLOW),
        WATER(false, Color.BLUE);

        public final boolean land;
        public final double minHeight;
        public final Color color;

        Type(boolean land, double minHeight, Color color) {
            this.land = land;
            this.minHeight = minHeight;
            this.color = color;
        }

        Type(boolean land, Color color) {
            this.land = land;
            this.color = color;
            this.minHeight = -1;
        }
    }
}
