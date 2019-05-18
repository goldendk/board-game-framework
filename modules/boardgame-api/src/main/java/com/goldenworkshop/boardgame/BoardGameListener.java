package com.goldenworkshop.boardgame;

import com.goldenworkshop.boardgame.BoardPiece;
import com.goldenworkshop.boardgame.DiceRoll;
import com.goldenworkshop.boardgame.Player;
import com.goldenworkshop.boardgame.Tile;
import com.goldenworkshop.boardgame.game.GameResult;

public interface BoardGameListener {

    void onPieceMoved(BoardPiece boardPiece, Tile from, Tile to);
    void onDiceRolled(Player player, DiceRoll diceRoll);


    void onScoreChanged(Player player, int score);
    void onGameFinished(Player player, GameResult result);


    void onTurnStart(Player player);
    void onTurnEnd(Player player);
}
