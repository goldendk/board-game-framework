package com.goldenworkshop.boardgame;

import java.util.Set;

public interface BoardGame {

     void movePiece(BoardPiece bp, Tile from, Tile to) throws GameRuleException;


     void addRule(GameRule gameRule);
     void addListener(BoardGameListener listener);
     void removeListener(BoardGameListener listener);

     void startGame() throws GameRuleException;

     /**
      * Allows the UI or another external context to forcefully end a players turn. Note that this might also be done automatically by the game rules.
      *
      * @see BoardGameListener#onTurnEnd(Player)
      * @param player the player whose turn is ending.
      * @throws GameRuleException
      */
     void endTurn(Player player) throws GameRuleException;

     void addPlayer(Player player);

     /**
      * Returns all players currently in the game.
      * @return
      */
     Set<Player> getPlayers();


     /**
      * Board methods.
      */

     Tile findTile(Coordinate c);
     Set<Tile> getTiles();
     void addTile(Tile tile);
}
