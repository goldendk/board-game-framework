package com.goldenworkshop.boardgame;

public interface BoardGameFactory {

    Board createBoard(BoardGame game);
    DiceRoller createDiceRoller();
    BoardPieceLayout createBoardPieceLayout();
}
