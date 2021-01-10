package com.goldenworkshop.boardgame.cargame;

import com.goldenworkshop.boardgame.Board;
import com.goldenworkshop.boardgame.Coordinate;
import com.goldenworkshop.boardgame.Tile;
import com.goldenworkshop.boardgame.ui.XYSwingBoard;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class CarGameApp {
    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                XYSwingBoard swingBoard = new XYSwingBoard("Ready to race?", new Board() {
                    @Override
                    public Collection<Tile> getTiles() {
                        return null;
                    }

                    @Override
                    public Tile getTile(Coordinate coordinate) {
                        return null;
                    }

                    @Override
                    public int getMaxX() {
                        return 9;
                    }

                    @Override
                    public int getMaxY() {
                        return 1;
                    }
                });

                JFrame f = new JFrame("Car Game");
                f.add(swingBoard.getGui());
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
            }
        };
        // Swing GUIs should be created and updated on the EDT
        // http://docs.oracle.com/javase/tutorial/uiswing/concurrency
        SwingUtilities.invokeLater(r);
    }
}
