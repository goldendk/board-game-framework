package com.goldenworkshop.boardgame.impl;

import com.goldenworkshop.boardgame.*;

/**
 * Standard You-Go-I-Go game that alternates turns between players until the game ends.
 */
public class TurnBasedGame extends AbstractStandardGame {


    public TurnBasedGame(BoardGameFactory factory) {
        super(factory);
    }
}
