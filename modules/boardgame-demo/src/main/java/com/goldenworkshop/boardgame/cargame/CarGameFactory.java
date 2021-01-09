package com.goldenworkshop.boardgame.cargame;

import com.goldenworkshop.boardgame.*;
import com.goldenworkshop.boardgame.impl.D6DiceRoller;
import com.goldenworkshop.boardgame.impl.XYBoard;

public class CarGameFactory implements BoardGameFactory {
    private final int boardLength;

    public CarGameFactory(int boardLength){
        this.boardLength = boardLength;
    }

    @Override
    public Board createBoard(BoardGame game) {
        return new XYBoard(boardLength, game.getPlayers().size());
    }


    public DiceRoller createDiceRoller() {
        return new D6DiceRoller();
    }

    @Override
    public BoardPieceLayout createBoardPieceLayout() {
        return new CarGameBoardPieceLayout();
    }

}
