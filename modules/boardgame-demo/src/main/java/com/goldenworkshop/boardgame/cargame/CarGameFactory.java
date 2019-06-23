package com.goldenworkshop.boardgame.cargame;

import com.goldenworkshop.boardgame.Board;
import com.goldenworkshop.boardgame.BoardGame;
import com.goldenworkshop.boardgame.BoardGameFactory;
import com.goldenworkshop.boardgame.impl.XYBoard;

public class CarGameFactory implements BoardGameFactory {

    @Override
    public Board createBoard(BoardGame game) {
        return new XYBoard(55, game.getPlayers().size());
    }
}
