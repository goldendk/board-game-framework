package com.goldenworkshop.boardgame;

public interface BoardGameFactory {
    BoardGameState createBoardGameState(BoardGame abstractStandardGame);
}
