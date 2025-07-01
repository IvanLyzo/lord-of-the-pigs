package helpers;

import java.awt.*;

public class Bound {

    public int xPos;
    public int yPos;
    public int width;
    public int height;

    public Bound(int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    public boolean inBounds(Point point) {
        return point.x > xPos && point.x < xPos + width
                && point.y > yPos && point.y < yPos + height;
    }
}
