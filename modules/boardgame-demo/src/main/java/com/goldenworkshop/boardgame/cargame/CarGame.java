package com.goldenworkshop.boardgame.cargame;


import com.goldenworkshop.boardgame.*;
import com.goldenworkshop.boardgame.impl.StandardGame;
import com.goldenworkshop.boardgame.impl.XYCoordinate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Demo-game showing some of the board-game framework's capabilities.
 *
 * @see com.goldenworkshop.boardgame.cargame
 */
public class CarGame extends StandardGame {

    public static final String BOARDPIECE_TYPE_CAR = "CAR";

    public CarGame(CarGameFactory factory) {
        super(factory);
    }

    @Override
    public void setupGameRules() {
        this.addRule(new CarGameRule());
    }


    @Override
    public void movePiece(BoardPiece bp, Tile from, Tile to, DiceRoll diceRoll) throws GameRuleException {
        super.movePiece(bp, from, to, diceRoll);
        super.endTurn(bp.getPlayer());

        List<Player> players = new ArrayList<>(super.getPlayers());
        players.remove(bp.getPlayer());
        Player otherPlayer = players.get(0);
        super.startTurn(otherPlayer);
    }

    @Override
    public List<Tile> getPossibleDestinations(BoardPiece boardPiece, DiceRoll diceRoll) {

        Tile from = super.getTile(boardPiece);
        try {
            Tile to = getBoard().getTile(new XYCoordinate(from.getCoordinate().getX() + diceRoll.getSum(), from.getCoordinate().getY()));
            return Arrays.asList(to);
        } catch (GameStateException e) {
            return Collections.emptyList();
        }
    }
}
