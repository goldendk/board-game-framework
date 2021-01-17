package com.goldenworkshop.boardgame.cargame;

import com.goldenworkshop.boardgame.Board;
import com.goldenworkshop.boardgame.Coordinate;
import com.goldenworkshop.boardgame.Tile;
import com.goldenworkshop.boardgame.cargame.ui.CarGameUI;
import com.goldenworkshop.boardgame.impl.BasicPlayer;
import com.goldenworkshop.boardgame.ui.XYSwingBoard;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class CarGameApp {
    public static void main(String[] args) {


        CarGameFactory factory = new CarGameFactory(10, "Car Game");
        CarGame carGame = new CarGame(factory);
        CarGameUI ui = new CarGameUI(factory);
        carGame.addPlayer(new BasicPlayer("1", "Foo"));
        carGame.addPlayer(new BasicPlayer("2", "Bar"));
        carGame.addListener(ui);
        carGame.startGame();

    }
}
