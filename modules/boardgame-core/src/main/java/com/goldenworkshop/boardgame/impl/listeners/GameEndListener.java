package com.goldenworkshop.boardgame.impl.listeners;

import com.goldenworkshop.boardgame.*;

public class GameEndListener implements BoardGameListener {
    private GameResult gameResult;
    private Player winner;

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
    public void onGameFinished(Player winner, GameResult result) {
        this.winner = winner;
        this.gameResult = result;
    }

    @Override
    public void onTurnStart(Player player) {

    }

    @Override
    public void onTurnEnd(Player player) {

    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public Player getWinner() {
        return winner;
    }
}
