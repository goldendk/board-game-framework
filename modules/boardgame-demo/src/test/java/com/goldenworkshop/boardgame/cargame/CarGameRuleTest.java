package com.goldenworkshop.boardgame.cargame;

import com.goldenworkshop.boardgame.*;
import com.goldenworkshop.boardgame.impl.BasicBoardPiece;
import com.goldenworkshop.boardgame.impl.BasicDieRoll;
import com.goldenworkshop.boardgame.impl.BasicTile;
import com.goldenworkshop.boardgame.impl.XYCoordinate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;


@ExtendWith(MockitoExtension.class)
public class CarGameRuleTest {

    @Spy
    CarGameRule rule;
    @Mock
    Player player1;
    @Mock Player player2;
    private final DiceRoll diceRollOf2 = new BasicDieRoll(new int[]{2});
    private final BoardPiece player1Piece = new BasicBoardPiece("pl1bp1", player1, "car");

    @Test
    public void shouldStartWithFirstPlayer() {
        Collection<Player> players = new ArrayList<>();
        players.add(player2);
        players.add(player1);

        Player startingPlayer = rule.pickStartingPlayer(players);
        assertSame(startingPlayer, player2, "Player 2 should be starting");
    }

    @Test
    public void shouldAllowMove(){
        Tile from = new BasicTile(new XYCoordinate(0,1));
        from.addBoardPiece(player1Piece);
        Tile to = new BasicTile(new XYCoordinate(0,2));

        assertFalse(rule.isMoveAllowed(from, to,player1Piece, diceRollOf2));


    }

    @Test
    public void shouldNotAllowMove_whenDistanceIsNotEqualToRoll(){

        Tile from = new BasicTile(new XYCoordinate(0,1));
        Tile to = new BasicTile(new XYCoordinate(0,3));
        from.addBoardPiece(player1Piece);

        assertFalse(rule.isMoveAllowed(from, to,player1Piece, diceRollOf2));


    }

    @Test
    public void shouldNotAllowMove_whenFromTileDoesNotHaveBoardPiece(){
        Tile from = new BasicTile(new XYCoordinate(0,1));
        Tile to = new BasicTile(new XYCoordinate(0,2));

        assertFalse(rule.isMoveAllowed(from, to,player1Piece, diceRollOf2));

    }


}