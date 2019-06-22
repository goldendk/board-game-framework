package com.goldenworkshop.boardgame;

/**
 * Interface intended to hold games rules for the implemented games. Developers will want to implement this class and register it
 * with their chosen game using {@link BoardGame#addRule(GameRule)}.
 */
public interface GameRule {

    /**
     * Called when the game starts to determine which player starts the game.
     * @return
     */
    Player pickStartingPlayer();

    /**
     * Invoked by the game when a new player starts a turn.
     * @param player
     */
    void onNewPlayerTurn(Player player);

    /**
     * Invoked before moving any player's pieces on the board. It is up the the game implementation to determine which
     * actions to take when true or false is returned.
     * @see com.goldenworkshop.boardgame.impl.AbstractStandardGame for example implementation.
     * @param from
     * @param to
     * @param p
     * @return
     */
    boolean isMoveAllowed(Tile from, Tile to, Player p);



}
