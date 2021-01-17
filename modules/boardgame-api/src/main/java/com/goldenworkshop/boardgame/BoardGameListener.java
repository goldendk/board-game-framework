package com.goldenworkshop.boardgame;

public interface BoardGameListener {

    void onPieceMoved(BoardPiece boardPiece, Tile from, Tile to);
    void onDiceRolled(Player player, DiceRoll diceRoll);


    void onScoreChanged(Player player, int score);
    void onGameStarted(BoardGame boardGame);
    void onGameFinished(Player winner, GameResult result);


    void onTurnStart(Player player);
    void onTurnEnd(Player player);
}
