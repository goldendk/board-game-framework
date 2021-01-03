package com.goldenworkshop.boardgame.cargame;

import com.goldenworkshop.boardgame.*;

import java.util.Collection;

public class CarGameRule implements GameRule {
    @Override
    public Player pickStartingPlayer(Collection<Player> players) {
        return players.iterator().next();
    }

    @Override
    public void onNewPlayerTurn(Player player) {

    }

    @Override
    public boolean isMoveAllowed(Tile from, Tile to, BoardPiece boardPiece, DiceRoll diceRoll) {
        int distance = to.getCoordinate().getX() - from.getCoordinate().getX();

        if (distance == diceRoll.getSum()
                && from.getBoardPieces().contains(boardPiece)) {
            return true;
        } else {
            return false;
        }

    }
}
