package com.goldenworkshop.boardgame.game.cargame;

import com.goldenworkshop.boardgame.*;
import com.goldenworkshop.boardgame.cargame.CarGame;
import com.goldenworkshop.boardgame.cargame.CarGameFactory;
import com.goldenworkshop.boardgame.cargame.CarGameRule;
import com.goldenworkshop.boardgame.impl.BasicPlayer;
import com.goldenworkshop.boardgame.impl.FixedDiceRoller;
import com.goldenworkshop.boardgame.impl.listeners.CurrentPlayerListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarGameTest {

    private BoardGame game;

    private Player player1;
    private Player player2;
    @Spy
    CarGameRule carGameRuleSpy;

    @Spy
    CarGameFactory carGameFactorySpy;

    CurrentPlayerListener currentPlayerListener;
    DiceRoller fixedRollerForValue3 = new FixedDiceRoller(3);

    @BeforeEach
    public void setUp() throws Exception {
        currentPlayerListener = new CurrentPlayerListener();

        game = new CarGame(carGameFactorySpy);
        player1 = new BasicPlayer("player1");
        player2 = new BasicPlayer("player2");

    }

    @Test
    public void shouldAddPlayers() {

        game.addPlayer(player1);
        game.addPlayer(player2);

        assertEquals(2, game.getPlayers().size());
        assertTrue(game.getPlayers().contains(player1));
        assertTrue(game.getPlayers().contains(player2));
    }

    @Test
    public void shouldNotAddPlayersAfterGameStart() {
        doReturn(player1).when(carGameRuleSpy).pickStartingPlayer(anyObject());

        assertThrows(GameStateException.class, () -> {
            game.addRule(carGameRuleSpy);

            game.addPlayer(player1);
            game.startGame();
            game.addPlayer(player2);
        });

    }

    @Test
    public void shouldAddGameRule() {
        game.addRule(carGameRuleSpy);
    }

    @Test
    public void shouldStartGame() {
        createAndStartGame();
        //verify

        verify(carGameFactorySpy, times(1)).createBoard(any(BoardGame.class));
        verify(carGameRuleSpy, times(1)).pickStartingPlayer(anyCollection());
        verify(carGameRuleSpy, times(1)).onNewPlayerTurn(any(Player.class));
    }

    @Test
    public void shouldPlayGameRound() {

        createAndStartGame();

        assertEquals(currentPlayerListener.getPlayer(), player1);

        moveCurrentPlayer();
        assertEquals(player2, currentPlayerListener.getPlayer());

        moveCurrentPlayer();

        assertEquals(player1, currentPlayerListener.getPlayer());


    }

    private void moveCurrentPlayer() {
        Collection<BoardPiece> boardPieces = game.getBoardPieces(currentPlayerListener.getPlayer());
        BoardPiece currentPlayerPiece = boardPieces.iterator().next();
        DiceRoll diceRoll = fixedRollerForValue3.roll1();
        List<Tile> possibleDestinations = game.getPossibleDestinations(currentPlayerPiece, diceRoll);
        Tile currentTile = game.getTile(currentPlayerPiece);
        Tile toTile = possibleDestinations.get(0);
        game.movePiece(currentPlayerPiece, currentTile, toTile, diceRoll);
        assertTrue(toTile.getBoardPieces().contains(currentPlayerPiece));
        assertFalse(currentTile.getBoardPieces().contains(currentPlayerPiece));
    }

    private void createAndStartGame() {
        game.addRule(carGameRuleSpy);
        game.addListener(currentPlayerListener);
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.startGame();

    }


}