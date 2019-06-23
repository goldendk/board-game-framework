package com.goldenworkshop.boardgame.cargame;

import com.goldenworkshop.boardgame.GameRule;
import com.goldenworkshop.boardgame.Player;
import com.goldenworkshop.boardgame.Tile;

import java.util.Collection;

public class CarGameRule implements GameRule {
    @Override
    public Player pickStartingPlayer(Collection<Player> players) {
        return players.iterator().next();
    }

    @Override
    public void onNewPlayerTurn(Player player) {

    }

    @Override
    public boolean isMoveAllowed(Tile from, Tile to, Player p) {
        return false;
    }
}
