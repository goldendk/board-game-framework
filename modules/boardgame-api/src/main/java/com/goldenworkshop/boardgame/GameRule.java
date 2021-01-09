package com.goldenworkshop.boardgame;

import java.util.Collection;
import java.util.Optional;

/**
 * Interface intended to hold games rules for the implemented games. Developers will want to implement this class and register it
 * with their chosen game using {@link BoardGame#addRule(GameRule)}.
 */
public interface GameRule {

    /**
     * Called when the game starts to determine which player starts the game.
     *
     * @param players
     * @return
     */
    Player pickStartingPlayer(Collection<Player> players);

    /**
     * Invoked by the game when a new player starts a turn.
     *
     * @param player
     */
    void onNewPlayerTurn(Player player);

    /**
     * Invoked before moving any player's pieces on the board. It is up the the game implementation to determine which
     * actions to take when true or false is returned.
     *
     * @param from
     * @param to
     * @param boardPiece
     * @return
     */
    boolean isMoveAllowed(Tile from, Tile to, BoardPiece boardPiece, DiceRoll diceRoll);

    Optional<Player> selectWinner(Board board, Collection<Player> players);
}
