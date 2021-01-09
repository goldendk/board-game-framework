package com.goldenworkshop.boardgame.impl;

import com.goldenworkshop.boardgame.*;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * Util class for board-games.
 */
public class StandardGameHelper {


    public static void checkMoveAllowed(BoardPiece bp, Tile from, Tile to, DiceRoll diceRoll, Collection<GameRule> gameRules) {
        for (GameRule rule : gameRules) {
            if (!rule.isMoveAllowed(from, to, bp, diceRoll)) {
                throw new GameRuleException("Move from " + from + " to " + to + " is not allowed with roll " + diceRoll);
            }
        }
    }

    public static void notifyListenersOfMovement(BoardPiece bp, Tile from, Tile to, Collection<BoardGameListener> listeners) {
        for (BoardGameListener listener : listeners) {
            listener.onPieceMoved(bp, from, to);
        }
    }

    public static void notifyListenersOfWinnerAndLosers(Player winner, Set<BoardGameListener> gameListeners) {
        gameListeners.forEach(gr -> gr.onGameFinished(winner, GameResult.WINNER));
    }
}
