package com.goldenworkshop.boardgame.cargame;

import com.goldenworkshop.boardgame.BoardGameFactory;
import com.goldenworkshop.boardgame.BoardGameState;
import com.goldenworkshop.boardgame.BoardGame;

public class CarGameFactory implements BoardGameFactory {
    @Override
    public BoardGameState createBoardGameState(BoardGame abstractStandardGame) {
        return new CarGameState( abstractStandardGame);
    }
}
