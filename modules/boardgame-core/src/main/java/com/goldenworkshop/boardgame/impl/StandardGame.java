package com.goldenworkshop.boardgame.impl;

import com.goldenworkshop.boardgame.*;

import java.util.*;

/**
 * Represents most commonly used terms for a board game such as players, points, 2D board etc.
 * <p>
 * <p>
 * This class implements the following sequence for games:
 *
 * <ul>
 * <li>Add game rules to your game by overriding the method template {@link #setupGameRules()}</li>
 * <li>Add players to the game by calling {@link #addPlayer(Player)}</li>
 * </ul>
 *
 * <p>
 * Movement: Simple movement rules. If game-rules allow a move the piece will be moved. If not the piece will not be moved.
 */
public abstract class StandardGame implements BoardGame {

    private final BoardGameFactory factory;
    private Map<String, Player> players = new LinkedHashMap<>();
    private Set<BoardGameListener> gameListeners = new HashSet<>();
    private Collection<GameRule> gameRules = new ArrayList<>();
    private Board board;
    private GameState state;

    public StandardGame(BoardGameFactory factory) {
        this.factory = factory;
        initialize();
    }

    private void initialize() {
        this.changeState(GameState.INITIAL);
        setupGameRules();
    }

    private void changeState(GameState state) {
        this.state = state;
    }

    @Override
    public Tile findTile(Coordinate c) {
        return board.getTile(c);
    }

    @Override
    public Set<Tile> getTiles() {
        return Collections.unmodifiableSet(new HashSet<>(this.board.getTiles()));
    }

    @Override
    public void movePiece(BoardPiece bp, Tile from, Tile to, DiceRoll diceRoll) throws GameRuleException {
        StandardGameHelper.checkMoveAllowed(bp, from, to, diceRoll, this.gameRules);

        from.removeBoardPiece(bp);
        to.addBoardPiece(bp);

        StandardGameHelper.notifyListenersOfMovement(bp, from, to, this.gameListeners);

        checkForWinner();
    }

    private void checkForWinner() {
        Optional<Player> foundWinner = Optional.empty();
        for (GameRule gameRule : this.gameRules) {
            Optional<Player> winner = gameRule.selectWinner(this.getBoard(), this.getPlayers());
            if (winner.isPresent()) {
                foundWinner = winner;
            }
        }
        this.changeState(GameState.GAME_OVER);
        foundWinner.ifPresent(player -> StandardGameHelper.notifyListenersOfWinnerAndLosers(player, this.gameListeners));
    }


    /**
     * Called when the game is being instantiated. Should add all game-rules to the game state.
     * Must be implemented by sup-classes.
     */
    public abstract void setupGameRules();

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
        if (this.getPlayers().isEmpty()) {
            throw new GameStateException("Game cannot be started without players. Add all players before starting game");
        }

        //setup board.
        this.board = factory.createBoard(this);

        BoardPieceLayout boardPieceLayout = this.factory.createBoardPieceLayout();
        boardPieceLayout.setupInitialPositions(board, this.getPlayers());

        //find starting player.
        Player startingPlayer = null;
        for (GameRule rule : this.gameRules) {
            startingPlayer = rule.pickStartingPlayer(this.getPlayers());
            if (startingPlayer != null) {
                break;
            }
        }
        if (startingPlayer == null) {
            throw new GameStateException("No game-rules provided a starting player - cannot start game.");
        }
        Player finalStartingPlayer = startingPlayer;
        this.changeState(GameState.IN_GAME);
        gameRules.stream().forEach(e -> e.onNewPlayerTurn(finalStartingPlayer));

        gameListeners.forEach(e -> e.onGameStarted(this));

        startTurn(startingPlayer);

    }


    protected void startTurn(Player player) {
        gameListeners.stream().forEach(e -> e.onTurnStart(player));
    }

    @Override
    public void endTurn(Player player) throws GameRuleException {

        for (BoardGameListener listener : this.gameListeners) {
            listener.onTurnEnd(player);
        }

    }

    @Override
    public void addPlayer(Player player) {
        if (this.state != GameState.INITIAL) {
            throw new GameStateException("Players must be added before game is started!");
        }

        String key = player.getId();
        if (players.containsKey(key)) {
            throw new GameStateException("A player with ID " + key + " already exists.");
        }
        this.players.put(key, player);
    }

    @Override
    public List<Player> getPlayers() {
        return Collections.unmodifiableList(new ArrayList<>(players.values()));
    }


    @Override
    public Collection<BoardPiece> getBoardPieces(Player player) {
        List<BoardPiece> result = new ArrayList<>();
        for (Tile tile : this.board.getTiles()) {
            for (BoardPiece boardPiece : tile.getBoardPieces()) {
                if (boardPiece.getPlayer() == player) {
                    result.add(boardPiece);
                }
            }
        }
        return result;
    }

    @Override
    public Tile getTile(BoardPiece boardPiece) {
        for (Tile tile : this.board.getTiles()) {
            if (tile.getBoardPieces().contains(boardPiece)) {
                return tile;
            }
        }
        throw new GameStateException("BoardPiece does not have a tile, where did you find it? : " + boardPiece);
    }

    public Board getBoard() {
        return board;
    }
}
