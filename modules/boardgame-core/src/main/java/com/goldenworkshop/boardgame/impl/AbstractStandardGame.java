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

    private final BoardGameFactory factory;
    private Map<String, Player> players = new HashMap<>();
    private Set<BoardGameListener> gameListeners = new HashSet<>();
    private Collection<GameRule> gameRules = new ArrayList<>();
    private Board board;

    public AbstractStandardGame(BoardGameFactory factory) {
        this.factory = factory;
    }

    @Override
    public Tile findTile(Coordinate c) {
        return board.getTile(c);
    }

    @Override
    public Set<Tile> getTiles() {
        return null;
    }

    @Override
    public void movePiece(BoardPiece bp, Tile from, Tile to) throws GameRuleException {
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
        //setup board.
        this.board = factory.createBoard(this);

        //find starting player.
        Player startingPlayer = null;
        for(GameRule rule: this.gameRules){
            startingPlayer = rule.pickStartingPlayer(this.getPlayers());
            if(startingPlayer != null){
                break;
            }
        }
        if(startingPlayer == null){
            throw new GameRuleException("No game-rules provided a starting player - cannot start game.");
        }
        Player finalStartingPlayer = startingPlayer;
        gameRules.stream().forEach(e->e.onNewPlayerTurn(finalStartingPlayer));



    }

    @Override
    public void endTurn(Player player) throws GameRuleException {

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
        return Collections.unmodifiableSet(new HashSet<>(players.values()));
    }
}
