package com.goldenworkshop.boardgame.cargame;

import com.goldenworkshop.boardgame.*;
import com.goldenworkshop.boardgame.impl.BasicBoardPiece;
import com.goldenworkshop.boardgame.impl.XYCoordinate;

import java.util.List;

public class CarGameBoardPieceLayout implements BoardPieceLayout {
    @Override
    public void setupInitialPostions(Board board, List<Player> players) {


        for (int i = 0; i < players.size(); i++) {
            Tile tile = board.getTile(new XYCoordinate(0, i));
            Player p = players.get(i);

            BoardPiece bp = new BasicBoardPiece(p.getId() + "bp", p, CarGame.BOARDPIECE_TYPE_CAR);

            tile.addBoardPiece(bp);
        }

    }
}
