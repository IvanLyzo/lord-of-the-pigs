package model.base;

import helpers.ResHandler;

import java.awt.image.BufferedImage;

public class Tile {

    public static final int TILESIZE = 64;
    public static int scaledTileSize = 48; // 16px x 16px
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
        LAND(true, 0.85, "/textures/tiles/grass.png"),
        SAND(true, 0.80, "/textures/tiles/sand.png"),
        SHALLOW_WATER(false, 0.75, "/textures/tiles/shallow_water.png"),
        TRANSITION_WATER(false, 0.5, "/textures/tiles/transition_water.png"),
        DEEP_WATER(false, -1, "/textures/tiles/deep_water.png");

        public final boolean land;
        public final double minHeight;
        public final BufferedImage sprite;

        Type(boolean land, double minHeight, String spriteLoc) {
            this.land = land;
            this.minHeight = minHeight;

            sprite = ResHandler.getSprite(spriteLoc);
        }
    }
}
