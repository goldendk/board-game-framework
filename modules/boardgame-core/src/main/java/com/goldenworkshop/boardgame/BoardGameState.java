package com.goldenworkshop.boardgame;

/**
 * BoardGameState is a special kind of {@link BoardGameListener}. The implementations are meant to contain the specific rules
 * for each game implemented.
 * <p>
 *     The game-state will be notified of any events before any other BoardGameListener assigned to {@link BoardGame}
 */
public interface BoardGameState extends BoardGameListener {

    /**
     * Invoked as the first method during setup. Should initialize the board and place any board pieces whose position is mandatory.
     */
    void initGame();

    /**
     * Invoked when the user decides to start the game. Note that any implementations can force this call by calling {@link BoardGame#startGame()}
     * <p>
     *     <strong>NOTE:</strong> beware of endless loops here if {@link BoardGame#startGame()} is invoked manually.
     */
    void startGame();

}
