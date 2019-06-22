package com.goldenworkshop.boardgame;

public interface BoadGameFactory {

    BoardGame crateGame();
    BoardGameState createGameState();

}
