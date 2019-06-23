package com.goldenworkshop.boardgame;

import java.util.List;

/**
 * Tile is the container of board-pieces of the game being played.
 */
public interface Tile {

    /**
     * Retrieves the Coordinate of this Tile.
     * @return
     */
    Coordinate getCoordinate();

    /**
     * Adds a board-piece to this Tile. Remember implementations of this class are not required to enforce game-rules.
     *
     * @param boardPiece the BoardPiece to add.
     */
    void addBoardPiece(BoardPiece boardPiece);

    /**
     * Retrieve all board-pieces of this Tile in the order they were placed on the Tile.
     * @return
     */
    List<BoardPiece> getBoardPieces();
}
