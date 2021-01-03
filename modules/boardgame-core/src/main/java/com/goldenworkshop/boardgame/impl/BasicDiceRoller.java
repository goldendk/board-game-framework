package com.goldenworkshop.boardgame.impl;

import com.goldenworkshop.boardgame.DiceRoll;
import com.goldenworkshop.boardgame.DiceRoller;

public abstract class BasicDiceRoller implements DiceRoller {
    private final int faceCount;

    public BasicDiceRoller(int faceCount) {
        this.faceCount = faceCount;
    }

    @Override
    public DiceRoll roll1() {
        return new BasicDieRoll(new int[]{rollDie(this.faceCount)});
    }

    @Override
    public DiceRoll roll2() {
        return new BasicDieRoll(new int[]{
                rollDie(this.faceCount),
                rollDie(this.faceCount)
        });
    }

    protected static int rollDie(int faces) {
        return ((int) (Math.random() * faces)) + 1;
    }
}
