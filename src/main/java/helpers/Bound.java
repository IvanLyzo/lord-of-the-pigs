package helpers;

import model.base.Entity;

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

    public CollisionCheckResponse inBounds(Point point) {
        if (point == null) {
            return CollisionCheckResponse.NULL;
        }

        return point.x > xPos && point.x < xPos + width && point.y > yPos && point.y < yPos + height ? CollisionCheckResponse.TRUE : CollisionCheckResponse.FALSE;
    }

    public enum CollisionCheckResponse {
        TRUE,
        FALSE,
        NULL
    }
}
