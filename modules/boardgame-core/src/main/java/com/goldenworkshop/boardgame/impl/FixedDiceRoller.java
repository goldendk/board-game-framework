package com.goldenworkshop.boardgame.impl;

import com.goldenworkshop.boardgame.DiceRoll;
import com.goldenworkshop.boardgame.DiceRoller;

public class FixedDiceRoller implements DiceRoller {
    private final int fixedRoll;

    public FixedDiceRoller(int fixedRoll) {
        this.fixedRoll = fixedRoll;
    }

    @Override
    public DiceRoll roll1() {
        return new BasicDieRoll(new int[]{fixedRoll});
    }

    @Override
    public DiceRoll roll2() {
        return new BasicDieRoll(new int[]{fixedRoll, fixedRoll});
    }
}
