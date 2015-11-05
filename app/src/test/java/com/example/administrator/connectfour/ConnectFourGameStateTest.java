package com.example.administrator.connectfour;

import com.example.administrator.connectfour.connectfour.ConnectFourGameState;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by garciah16 on 11/4/2015.
 */
public class ConnectFourGameStateTest {

    @Test
    public void testOnPlayerMove() throws Exception {
        ConnectFourGameState gameState = new ConnectFourGameState();
        int result = gameState.onPlayerMove(0);

        assertEquals(0, result);

        //test if board is full
        for(int i = 0; i < 6; i++) {
            result = gameState.onPlayerMove(0);
        }
        assertEquals(1, result);
    }

    @Test
    public void testGetPlayer1Score() throws Exception {
        ConnectFourGameState gameState = new ConnectFourGameState();
        gameState.setPlayer1Score(0);

        assertEquals(0, gameState.getPlayer1Score());
    }

    @Test
    public void testGetPlayer2Score() throws Exception {
        ConnectFourGameState gameState = new ConnectFourGameState();
        gameState.setPlayer2Score(0);

        assertEquals(0, gameState.getPlayer2Score());

    }


    @Test
    public void testGetCurrentPlayerID() throws Exception {
        ConnectFourGameState gameState = new ConnectFourGameState();
        gameState.setCurrentPlayerID(ConnectFourGameState.PLAYER1_ID);

        assertEquals(0, gameState.getCurrentPlayerID());

    }

    @Test
    public void testSetCurrentPlayerID() throws Exception {
        ConnectFourGameState gameState = new ConnectFourGameState();
        gameState.setCurrentPlayerID(0);

        assertEquals(0, gameState.getCurrentPlayerID());

    }


    @Test
    public void testSetPlayer1Score() throws Exception {
        ConnectFourGameState gameState = new ConnectFourGameState();
        gameState.setPlayer1Score(0);

        assertEquals(0, gameState.getPlayer1Score());

    }

    @Test
    public void testSetPlayer2Score() throws Exception {
        ConnectFourGameState gameState = new ConnectFourGameState();
        gameState.setPlayer2Score(0);

        assertEquals(0, gameState.getPlayer2Score());

    }
}