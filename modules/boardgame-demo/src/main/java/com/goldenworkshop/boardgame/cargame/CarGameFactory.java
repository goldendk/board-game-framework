package com.goldenworkshop.boardgame.cargame;

import com.goldenworkshop.boardgame.*;
import com.goldenworkshop.boardgame.impl.D6DiceRoller;
import com.goldenworkshop.boardgame.impl.XYBoard;

public class CarGameFactory implements BoardGameFactory {
    private final int boardLength;
    private String title;

    public CarGameFactory(int boardLength, String title) {
        this.boardLength = boardLength;
        this.title = title;

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

    @Override
    public String getTitle() {
        return this.title;
    }


}
