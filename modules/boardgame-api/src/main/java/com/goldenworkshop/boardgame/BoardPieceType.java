package com.goldenworkshop.boardgame;

import java.util.HashMap;
import java.util.Map;

public final class BoardPieceType {
    private static Map<String, BoardPieceType> boardPieceTypeMap = new HashMap<>();
    private final String value;

    private BoardPieceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BoardPieceType fromValue(String value){
        if(boardPieceTypeMap.containsKey(value)){
            throw new GameStateException("Duplicate board piece types not allowed: " + value);
        }
        return new BoardPieceType(value);
    }
}
