package com.goldenworkshop.boardgame.impl;

import com.goldenworkshop.boardgame.*;

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
        return boardPieces;
    }

    @Override
    public void removeBoardPiece(BoardPiece bp) {
        if(!boardPieces.contains(bp)){
            throw new GameRuleException(this.toString() + " does not contain " + bp.toString());
        }
        this.boardPieces.remove(bp);
    }

    @Override
    public String toString() {
        return "BasicTile{" +
                "coordinate=" + coordinate +
                ", boardPieces=" + boardPieces +
                '}';
    }
}
