package com.goldenworkshop.boardgame.ui;

import com.goldenworkshop.boardgame.Board;
import com.goldenworkshop.boardgame.Player;
import com.goldenworkshop.boardgame.Tile;

import java.util.Collection;

public interface BoardRenderer {

    /**
     * Renders the initial state of the board.
     * @param board - the tile to render.
     */
    void setupInitialState(Board board, Collection<Player> players);

    /**
     * (Re)-renders a single tile on the board.
     * @param tile - the tile to render.
     */
    void renderTile(Tile tile);

}
