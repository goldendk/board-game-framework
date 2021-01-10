package com.goldenworkshop.boardgame;

public interface BoardPiece {

    Player getPlayer();

    String getId();

    BoardPieceType getType();
}
