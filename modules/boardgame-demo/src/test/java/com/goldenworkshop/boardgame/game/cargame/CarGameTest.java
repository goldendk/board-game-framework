package com.goldenworkshop.boardgame.game.cargame;

import com.goldenworkshop.boardgame.BoardGame;
import com.goldenworkshop.boardgame.Player;
import com.goldenworkshop.boardgame.cargame.CarGameFactory;
import com.goldenworkshop.boardgame.cargame.CarGameRule;
import com.goldenworkshop.boardgame.impl.BasicPlayer;
import com.goldenworkshop.boardgame.impl.TurnBasedGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CarGameTest {

    private BoardGame carGame;

    private Player player1;
    private Player player2;
    @Spy
    CarGameRule carGameRuleSpy;

    @Spy
    CarGameFactory carGameFactorySpy;

    @BeforeEach
    public void setUp() throws Exception {

        carGame = new TurnBasedGame(carGameFactorySpy);
        player1 = new BasicPlayer("player1");
        player2 = new BasicPlayer("player2");

    }

    @Test
    public void shouldAddPlayers() {

        carGame.addPlayer(player1);
        carGame.addPlayer(player2);

        assertEquals(2, carGame.getPlayers().size());
        assertTrue(carGame.getPlayers().contains(player1));
        assertTrue(carGame.getPlayers().contains(player2));
    }

    @Test
    public void shouldAddGameRule(){
        carGame.addRule(carGameRuleSpy);
    }

    @Test
    public void shouldStartGame(){
        carGame.addRule(carGameRuleSpy);
        carGame.addPlayer(player1);
        carGame.addPlayer(player2);

        carGame.startGame();

        //verify

        verify(carGameFactorySpy, times(1)).createBoard(any(BoardGame.class));
        verify(carGameRuleSpy, times(1)).pickStartingPlayer(anyCollection());
        verify(carGameRuleSpy, times(1)).onNewPlayerTurn(any(Player.class));


    }



}