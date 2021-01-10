package com.goldenworkshop.boardgame.ui;

import com.goldenworkshop.boardgame.Board;
import com.goldenworkshop.boardgame.Player;
import com.goldenworkshop.boardgame.Tile;

import java.util.Collection;

/**
 * Renders a board in a XY configuration such as a chess-board.
 * This implementation can be configured with several strategies as to the look-and-feel of tiles and board-pieces.
 */
public class XYBoardRenderer implements BoardRenderer {
    private XYSwingBoard boardUi;

    public XYBoardRenderer() {

    }

    @Override
    public void setupInitialState(Board board, Collection<Player> players) {
        boardUi = new XYSwingBoard("Some title", board);
        players.forEach(player -> boardUi.registerPlayer(player));
    }

    @Override
    public void renderTile(Tile tile) {

    }
}
