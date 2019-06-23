package com.goldenworkshop.boardgame.cargame;

import com.goldenworkshop.boardgame.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertSame;


@ExtendWith(MockitoExtension.class)
public class CarGameRuleTest {

    @Spy
    CarGameRule rule;
    @Mock
    Player player1;
    @Mock Player player2;

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


    }

    @Test
    public void shouldNotAllowMove(){


    }
}