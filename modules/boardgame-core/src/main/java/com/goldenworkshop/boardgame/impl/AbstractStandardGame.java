package com.goldenworkshop.boardgame.impl;

import com.goldenworkshop.boardgame.*;

import java.util.*;

/**
 * Represents most commonly used terms for a board game such as players points, 2D board game etc.
 *
 * <p>
 *     Movement: Simple movement rules. If game-rules allow a move the piece will be moved. If not the piece will not be moved.
 *
 *
 */
public class AbstractStandardGame implements BoardGame {
    private static Logger logger
    private Map<String, Player> players = new HashMap<>();
    private Map<String, Tile> tiles = new HashMap<>();
    private BoardGameState boardGameState = null;
    private Set<BoardGameListener> gameListeners = new HashSet<>();
    private Collection<GameRule> gameRules = new ArrayList<>();

    public AbstractStandardGame(BoardGameFactory factory) {
        boardGameState = factory.createBoardGameState(this);
    }

    @Override
    public Tile findTile(Coordinate c) {
        return tiles.get(c.getX() + "_" + c.getY());
    }

    @Override
    public Set<Tile> getTiles() {
        return null;
    }

    @Override
    public void addTile(Tile tile) {

    }

    @Override
    public void movePiece(BoardPiece bp, Tile from, Tile to) throws GameRuleException {
        boardGameState.onPieceMoved(bp, from, to);
        for (BoardGameListener listener : this.gameListeners) {
            listener.onPieceMoved(bp, from, to);
        }

    }

    @Override
    public void addRule(GameRule gameRule) {
        this.gameRules.add(gameRule);
    }

    @Override
    public void addListener(BoardGameListener listener) {
        this.gameListeners.add(listener);
    }

    @Override
    public void removeListener(BoardGameListener listener) {
        this.gameListeners.remove(listener);
    }

    @Override
    public void startGame() throws GameRuleException {

    }

    @Override
    public void endTurn(Player player) throws GameRuleException {
        boardGameState.onTurnEnd(player);

        for (BoardGameListener listener : this.gameListeners) {
            listener.onTurnEnd(player);
        }
    }

    @Override
    public void addPlayer(Player player) {
        String key = player.getId();
        if (players.containsKey(key)) {
            throw new GameRuleException("A player with ID " + key + " already exists.");
        }
        this.players.put(key, player);
    }

    @Override
    public Set<Player> getPlayers() {
        return new HashSet<>(players.values());
    }
}
