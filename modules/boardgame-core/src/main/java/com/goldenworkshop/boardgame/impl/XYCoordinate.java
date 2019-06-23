package com.goldenworkshop.boardgame.impl;

import com.goldenworkshop.boardgame.Coordinate;

import java.util.Objects;

/**
 * Default Coordinate implemenation. Basically takes an x and y input.
 */
public class XYCoordinate implements Coordinate {
    private final int x;
    private final int y;

    /**
     * Default constructor.
     * @param x the X-coordinate
     * @param y the Y-coordinate
     */
    public XYCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XYCoordinate that = (XYCoordinate) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
