package com.goldenworkshop.boardgame;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface BoardGame {
    /**
     * Moves a piece on the board from one Tile to another. Should not be called unless the game-rules have been checked.
     *
     * @param bp
     * @param from
     * @param to
     * @throws GameRuleException
     */
    void movePiece(BoardPiece bp, Tile from, Tile to, DiceRoll diceRoll) throws GameRuleException;


    void addRule(GameRule gameRule);

    void addListener(BoardGameListener listener);

    void removeListener(BoardGameListener listener);

    /**
     * Starts the game. Should be called once all players have been added to the game.
     * This method will start the following sequence until the game is started:
     *
     * <ol>
     * <li><strong>Create the board (setting up tiles)</strong></li>
     * <li><strong>Place board pieces on board</strong></li>
     * <li><strong>Ask game-rules to select starting player.</strong></li>
     * <li><strong>Notify game-rules of first turn.</strong></li>
     *
     * </ol>
     *
     * @throws GameRuleException
     */
    void startGame() throws GameRuleException;

    /**
     * Allows the UI or another external context to forcefully end a players turn. Note that this might also be done automatically by the game rules.
     *
     * @param player the player whose turn is ending.
     * @throws GameRuleException
     * @see BoardGameListener#onTurnEnd(Player)
     */
    void endTurn(Player player) throws GameRuleException;

    void addPlayer(Player player);

    /**
     * Returns all players currently in the game.
     *
     * @return
     */
    List<Player> getPlayers();


    /**
     * Board methods.
     */

    Tile findTile(Coordinate c);

    Set<Tile> getTiles();

    /**
     * Calculates a list of possible destinations for the given board-piece.
     *
     * @param boardPiece
     * @param diceRoll
     * @return
     */
    List<Tile> getPossibleDestinations(BoardPiece boardPiece, DiceRoll diceRoll);


    Collection<BoardPiece> getBoardPieces(Player player);

    Tile getTile(BoardPiece player1Piece);
}
