package com.goldenworkshop.boardgame;

import com.goldenworkshop.boardgame.game.GameRuleException;

/**
 * Standard You-Go-I-Go game that alternates turns between players until the game ends.
 */
public class TurnBasedGame extends AbstractStandardGame{

    public TurnBasedGame(BoardGameFactory factory) {
        super(factory);
    }

    @Override
    public Tile findTile(Coordinate c) {
        return null;
    }

    @Override
    public void movePiece(BoardPiece bp, Tile from, Tile to) throws GameRuleException {

    }

    @Override
    public void addListener(BoardGameListener listener) {

    }

    @Override
    public void removeListener(BoardGameListener listener) {

    }

    @Override
    public void startGame() throws GameRuleException {

    }

    @Override
    public void endTurn(Player player) throws GameRuleException {

    }
}
