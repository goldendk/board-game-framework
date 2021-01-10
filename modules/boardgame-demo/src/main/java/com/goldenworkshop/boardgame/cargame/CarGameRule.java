package com.goldenworkshop.boardgame.cargame;

import com.goldenworkshop.boardgame.*;
import com.goldenworkshop.boardgame.impl.XYCoordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

public class CarGameRule implements GameRule {
    public static Logger logger = LoggerFactory.getLogger(CarGameRule.class);

    @Override
    public Player pickStartingPlayer(Collection<Player> players) {
        return players.iterator().next();
    }

    @Override
    public void onNewPlayerTurn(Player player) {

    }

    @Override
    public boolean isMoveAllowed(Tile from, Tile to, BoardPiece boardPiece, DiceRoll diceRoll) {
        int distance = to.getCoordinate().getX() - from.getCoordinate().getX();

        if (distance == diceRoll.getSum()
                && from.getBoardPieces().contains(boardPiece)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Optional<Player> selectWinner(Board board, Collection<Player> players) {
        Optional<Integer> max = board.getTiles().stream().map(e -> e.getCoordinate().getX()).max(Comparator.naturalOrder());

        for (int i = 0; i < players.size(); i++) {
            Tile tile = board.getTile(new XYCoordinate(max.get(), i));
            if (!tile.getBoardPieces().isEmpty()) {
                Player player = tile.getBoardPieces().iterator().next().getPlayer();
                logger.info("Winner found: " + player);
                return Optional.of(player);
            }
        }
        return Optional.empty();
    }
}
