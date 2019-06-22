package com.goldenworkshop.boardgame.impl;

import com.goldenworkshop.boardgame.Tile;

public class BasicTile implements Tile {
    private final int x;
    private final int y;

    public BasicTile(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
