package com.goldenworkshop.boardgame.impl;

import com.goldenworkshop.boardgame.Board;
import com.goldenworkshop.boardgame.Coordinate;
import com.goldenworkshop.boardgame.GameStateException;
import com.goldenworkshop.boardgame.Tile;

import java.util.*;
import java.util.function.Function;

/**
 * Represents a 2D-grid based board. Each (x,y) combination is represented by a tile.
 */
public class XYBoard implements Board {

    private Map<Coordinate, Tile> tileMap = new HashMap<>();

    /**
     * @param x number of tiles in the x direction.
     * @param y number of tiles in the y direction.
     */
    public XYBoard(int x, int y) {
        if (x < 1) {
            throw new IllegalArgumentException("Size of board must be greater than zero in x-direction");
        }
        if (y < 1) {
            throw new IllegalArgumentException("Size of board must be greater than zero in y-direction");
        }
        for (int i = 0; i < x; i++) {
            for (int ii = 0; ii < y; ii++) {
                XYCoordinate xyCoordinate = new XYCoordinate(i, ii);
                tileMap.put(xyCoordinate, new BasicTile(xyCoordinate));
            }
        }
    }

    @Override
    public Collection<Tile> getTiles() {
        return this.tileMap.values();
    }

    @Override
    public Tile getTile(Coordinate coordinate) {
        Tile tile = tileMap.get(coordinate);
        if (tile == null) {
            throw new GameStateException("Tile for " + coordinate + " does not exist");
        }
        return tile;
    }

    @Override
    public int getMaxX() {
        return findMaxCoordComponent(e -> e.getCoordinate().getX());
    }

    @Override
    public int getMaxY() {
        return findMaxCoordComponent(e -> e.getCoordinate().getY());
    }

    private Integer findMaxCoordComponent(Function<Tile, Integer> mapFunction) {
        Optional<Integer> max = this.tileMap.values().stream().map(mapFunction).max(Comparator.naturalOrder());
        if (!max.isPresent()) {
            throw new GameStateException("Cannot find max coordinate on board with no tiles.");
        }
        return max.get();
    }
}
