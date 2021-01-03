package com.goldenworkshop.boardgame;

import java.util.Collection;

/**
 * A board is a container for Tiles.
 */
public interface Board {

    /**
     * Get all the Tiles on this Board.
     * @return all the tiles on the Board.
     */
    Collection<Tile> getTiles();

    /**
     * Finds a specific Tile based on coordinate.
     * @param coordinate
     * @return The Tile found, null if none found.
     */
    Tile getTile(Coordinate coordinate);
}
