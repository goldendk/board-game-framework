package com.goldenworkshop.boardgame.game.cargame;

import com.goldenworkshop.boardgame.*;
import com.goldenworkshop.boardgame.cargame.CarGame;
import com.goldenworkshop.boardgame.cargame.CarGameFactory;
import com.goldenworkshop.boardgame.cargame.CarGameRule;
import com.goldenworkshop.boardgame.impl.BasicPlayer;
import com.goldenworkshop.boardgame.impl.FixedDiceRoller;
import com.goldenworkshop.boardgame.impl.listeners.CurrentPlayerListener;
import com.goldenworkshop.boardgame.impl.listeners.GameEndListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarGameTest {

    private CarGame game;

    private Player player1;
    private Player player2;

    CarGameFactory carGameFactorySpy;

    @Spy
    CurrentPlayerListener currentPlayerListener;
    @Spy
    GameEndListener gameEndListener;

    DiceRoller fixedRollerForValue3 = new FixedDiceRoller(3);
    DiceRoller fixedRollerForValue2 = new FixedDiceRoller(2);

    @BeforeEach
    public void setUp() throws Exception {

        carGameFactorySpy = spy(new CarGameFactory(10, "JUnit game"));
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
        assertThrows(GameStateException.class, () -> {
            game.addPlayer(player1);
            game.startGame();
            game.addPlayer(player2);
        });

    }

    @Test
    public void shouldStartGame() {
        createAndStartGame();
        //verify
        verify(carGameFactorySpy, times(1)).createBoard(any(BoardGame.class));
        verify(currentPlayerListener, times(1)).onTurnStart(any(Player.class));
    }

    @Test
    public void shouldPlayGameRound() {

        createAndStartGame();
        assertEquals(currentPlayerListener.getPlayer(), player1);
        moveCurrentPlayer(fixedRollerForValue3.roll1());
        assertEquals(player2, currentPlayerListener.getPlayer());
        moveCurrentPlayer(fixedRollerForValue3.roll1());
        assertEquals(player1, currentPlayerListener.getPlayer());

    }

    @Test
    public void shouldEndGame(){

        createAndStartGame();
        moveCurrentPlayer(fixedRollerForValue3.roll1()); //player 1 at 3
        moveCurrentPlayer(fixedRollerForValue3.roll1()); //player 2 at 3
        moveCurrentPlayer(fixedRollerForValue3.roll1()); //player 1 at 6
        moveCurrentPlayer(fixedRollerForValue3.roll1()); //player 2 at 6
        moveCurrentPlayer(fixedRollerForValue3.roll1()); //player 1 at 9

        assertEquals(gameEndListener.getWinner(), player1);
        assertEquals(gameEndListener.getGameResult(), GameResult.WINNER);

    }

    private void moveCurrentPlayer(DiceRoll diceRoll) {

        Collection<BoardPiece> boardPieces = game.getBoardPieces(currentPlayerListener.getPlayer());
        BoardPiece currentPlayerPiece = boardPieces.iterator().next();
        List<Tile> possibleDestinations = game.getPossibleDestinations(currentPlayerPiece, diceRoll);
        Tile currentTile = game.getTile(currentPlayerPiece);
        Tile toTile = possibleDestinations.get(0);
        game.movePiece(currentPlayerPiece, currentTile, toTile, diceRoll);
        assertTrue(toTile.getBoardPieces().contains(currentPlayerPiece));
        assertFalse(currentTile.getBoardPieces().contains(currentPlayerPiece));

    }

    private void createAndStartGame() {

        game.addListener(currentPlayerListener);
        game.addListener(gameEndListener);

        game.addPlayer(player1);
        game.addPlayer(player2);
        game.startGame();

    }


}