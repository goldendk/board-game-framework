package com.goldenworkshop.boardgame;

import com.goldenworkshop.boardgame.Player;

public class BasicPlayer implements Player {
    private final String id;
    private final String name;
    public BasicPlayer(String playerId) {
        this(playerId, playerId);
    }

    public BasicPlayer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
