package com.goldenworkshop.boardgame;

import java.util.List;

public interface BoardPieceLayout {
    /**
     * Setup board-pieces in the initial position on the board.
     * @param board
     * @param players the players in the order they were added to the game.
     */
    void setupInitialPositions(Board board, List<Player> players);
}
