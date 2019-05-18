package org.goldenworkshop.boardgame.app;


import com.goldenworkshop.boardgame.*;
import com.goldenworkshop.boardgame.game.GameRuleException;

import java.util.Set;

/**
 * Main class executing a game using the provided arguments to construct the UI, game-state and rules.
 * <p>
 *     The following arguments are available:
 * <p>
 *    <ul>
 *        <li><strong>--game-factory=Some.Factory.Class</strong> Loads the provided factory class and executes the game with it. </li>
 *
 *    </ul>
 *
 */
public class BoardGameApp {
    public static void main(String... args){
        BoardGame game = new BoardGame() {
            @Override
            public Tile findTile(Coordinate c) {
                return null;
            }

            @Override
            public void movePiece(BoardPiece bp, Tile from, Tile to) throws GameRuleException {

            }

            @Override
            public void addListener(BoardGameListener listener) {

            }

            @Override
            public void removeListener(BoardGameListener listener) {

            }

            @Override
            public void startGame() throws GameRuleException {

            }

            @Override
            public void endTurn(Player player) throws GameRuleException {

            }

            @Override
            public void addPlayer(Player player) {

            }

            @Override
            public Set<Player> getPlayers() {
                return null;
            }

            @Override
            public Set<Tile> getTiles() {
                return null;
            }
        };
        game.getTiles();
    }
}
