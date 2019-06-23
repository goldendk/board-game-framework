package com.goldenworkshop.boardgame.impl;

import com.goldenworkshop.boardgame.BoardPiece;
import com.goldenworkshop.boardgame.Coordinate;
import com.goldenworkshop.boardgame.Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * Default Tile implementation. A tile can hold infinite board pieces unless prevented by game rules. This class enforces no
 * game-rules what-so-ever.
 */
public class BasicTile implements Tile {

    private final Coordinate coordinate;
    private final List<BoardPiece> boardPieces = new ArrayList<>();
    public BasicTile(Coordinate coordinate) {
        this.coordinate = coordinate;
    }


    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public void addBoardPiece(BoardPiece boardPiece) {
        this.boardPieces.add(boardPiece);
    }

    @Override
    public List<BoardPiece> getBoardPieces() {
        return null;
    }
}
