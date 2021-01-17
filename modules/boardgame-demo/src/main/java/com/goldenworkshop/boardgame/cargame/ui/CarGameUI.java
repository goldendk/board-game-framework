package com.goldenworkshop.boardgame.cargame.ui;

import com.goldenworkshop.boardgame.*;
import com.goldenworkshop.boardgame.ui.XYBoardRenderer;

public class CarGameUI extends XYBoardRenderer implements BoardGameListener {
    public CarGameUI(BoardGameFactory factory) {
        super(factory);
    }

    @Override
    public void onPieceMoved(BoardPiece boardPiece, Tile from, Tile to) {
        getBoardUi().updateTile(from);
        getBoardUi().updateTile(to);
    }

    @Override
    public void onDiceRolled(Player player, DiceRoll diceRoll) {

    }

    @Override
    public void onScoreChanged(Player player, int score) {

    }

    @Override
    public void onGameStarted(BoardGame game) {
        this.setupInitialState(game.getBoard(), game.getPlayers());
        game.getTiles().forEach(tile -> getBoardUi().updateTile(tile));
    }

    @Override
    public void onGameFinished(Player winner, GameResult result) {

    }

    @Override
    public void onTurnStart(Player player) {

    }

    @Override
    public void onTurnEnd(Player player) {

    }
}
