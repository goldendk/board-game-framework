package com.goldenworkshop.boardgame.impl.listeners;

import com.goldenworkshop.boardgame.*;

public class CurrentPlayerListener implements BoardGameListener {
    private Player player;

    @Override
    public void onPieceMoved(BoardPiece boardPiece, Tile from, Tile to) {

    }

    @Override
    public void onDiceRolled(Player player, DiceRoll diceRoll) {

    }

    @Override
    public void onScoreChanged(Player player, int score) {

    }

    @Override
    public void onGameStarted(BoardGame boardGame) {

    }

    @Override
    public void onGameFinished(Player player, GameResult result) {

    }

    @Override
    public void onTurnStart(Player player) {
        this.player = player;
    }

    @Override
    public void onTurnEnd(Player player) {
        this.player = null;
    }

    public Player getPlayer() {
        return player;
    }
}
