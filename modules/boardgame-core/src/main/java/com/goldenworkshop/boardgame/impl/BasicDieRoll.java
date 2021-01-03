package com.goldenworkshop.boardgame.impl;

import com.goldenworkshop.boardgame.DiceRoll;

import java.util.Arrays;

public class BasicDieRoll implements DiceRoll {

    private final int[] values;
    private final int sum;

    public BasicDieRoll(int[] values){
        this.values = values;
        this.sum = Arrays.stream(values).sum();
    }

    @Override
    public int getSum() {
        return sum;
    }

    @Override
    public int[] getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "BasicDieRoll{" +
                "values=" + Arrays.toString(values) +
                ", sum=" + sum +
                '}';
    }
}

