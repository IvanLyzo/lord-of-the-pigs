package helpers;

public class Tile {

    public static final int TILESIZE = 32; // 32px x 32px

    public final int x;
    public final int y;
    public final Type type;

    public Tile(int x, int y, Type type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public enum Type {
        GRASS,
        WATER,
        ROCK,
        SAND
    }
}
