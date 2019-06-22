package com.goldenworkshop.boardgame;

/**
 * Simple POJO implementation of X,Y coordinate.
 */
public class DefaultCoordinate {
    private int x;
    private int y;

    public DefaultCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
