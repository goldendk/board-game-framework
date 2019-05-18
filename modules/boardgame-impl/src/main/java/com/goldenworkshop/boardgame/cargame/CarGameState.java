package com.goldenworkshop.boardgame.cargame;

import com.goldenworkshop.boardgame.*;
import com.goldenworkshop.boardgame.BasicPlayer;
import com.goldenworkshop.boardgame.BoardGame;
import com.goldenworkshop.boardgame.game.GameResult;

/**
 * Rules for car game. Updates the game as appropriate.
 */
public class CarGameState implements BoardGameState {
    private final BoardGame game;


    public CarGameState(BoardGame game) {
        this.game = game;
    }

    @Override
    public void initGame() {
        game.addPlayer(new BasicPlayer("red"));
        game.addPlayer(new BasicPlayer("blue"));

        for (int i  = 0 ; i < 2; i ++){
            for (int j = 0 ; j < 25; j++){
                game.addTile(new BasicTile(j, i));
            }
        }
    }

    @Override
    public void startGame() {

    }
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
    public void onGameFinished(Player player, GameResult result) {

    }

    @Override
    public void onTurnStart(Player player) {

    }

    @Override
    public void onTurnEnd(Player player) {

    }


}
