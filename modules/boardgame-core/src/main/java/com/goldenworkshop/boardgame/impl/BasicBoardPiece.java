package com.goldenworkshop.boardgame.impl;

import com.goldenworkshop.boardgame.BoardPiece;
import com.goldenworkshop.boardgame.BoardPieceType;
import com.goldenworkshop.boardgame.Player;

public class BasicBoardPiece implements BoardPiece {
    private String id;

    private Player player;

    private BoardPieceType type;

    public BasicBoardPiece(String id, Player player, BoardPieceType type) {
        this.id = id;
        this.player = player;
        this.type = type;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public BoardPieceType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "BasicBoardPiece{" +
                "id='" + id + '\'' +
                ", player=" + player +
                ", type='" + type + '\'' +
                '}';
    }
}
