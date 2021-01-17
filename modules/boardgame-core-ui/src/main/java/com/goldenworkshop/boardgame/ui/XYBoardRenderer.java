package com.goldenworkshop.boardgame.ui;

import com.goldenworkshop.boardgame.*;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

/**
 * Renders a board in a XY configuration such as a chess-board.
 * This implementation can be configured with several strategies as to the look-and-feel of tiles and board-pieces.
 */
public class XYBoardRenderer implements BoardRenderer {
    private XYSwingBoard boardUi;
    private BoardGameFactory boardGameFactory;
    public XYBoardRenderer(BoardGameFactory factory) {
        this.boardGameFactory = factory;
    }

    @Override
    public void setupInitialState(Board board, Collection<Player> players) {
        boardUi = new XYSwingBoard(boardGameFactory.getTitle(), board);
        players.forEach(player -> boardUi.registerPlayer(player));
        runTheUi();
    }

    private void runTheUi() {

        Runnable r = () -> {

            JFrame f = new JFrame("Car Game");
            f.add(boardUi.getGui());
            // Ensures JVM closes after frame(s) closed and
            // all non-daemon threads are finished
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            // See https://stackoverflow.com/a/7143398/418556 for demo.
            f.setLocationByPlatform(true);
            f.setExtendedState(f.getExtendedState() | Frame.MAXIMIZED_BOTH);
            // ensures the frame is the minimum size it needs to be
            // in order display the components within it
            f.pack();
            // ensures the minimum size is enforced.
            f.setMinimumSize(f.getSize());
            f.setVisible(true);
        };
        // Swing GUIs should be created and updated on the EDT
        // http://docs.oracle.com/javase/tutorial/uiswing/concurrency
        SwingUtilities.invokeLater(r);
    }

    @Override
    public void renderTile(Tile tile) {
        boardUi.updateTile(tile);
    }

    protected XYSwingBoard getBoardUi() {
        return this.boardUi;
    }
}
