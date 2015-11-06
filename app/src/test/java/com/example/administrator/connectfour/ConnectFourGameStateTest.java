package com.example.administrator.connectfour;

import com.example.administrator.connectfour.connectfour.ConnectFourGameState;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by garciah16 on 11/4/2015.
 */
public class ConnectFourGameStateTest {

    @Test
    public void testConstructor() throws Exception {
        ConnectFourGameState gameState = new ConnectFourGameState();

        //test if the array is empty
        for(int i = 0; i < 6; i++ ){ //index 0 is the bottom row
            for(int j = 0; j < 7; j++){ //index 0 is the leftmost column
                assertEquals(ConnectFourGameState.EMPTY, gameState.getGameBoard()[i][j]);
            }
        }
    }

    @Test
    public void testOtherConstructor() throws Exception {
        ConnectFourGameState gameState = new ConnectFourGameState();

        ConnectFourGameState newGameState = new ConnectFourGameState(gameState);

        assertEquals(newGameState.getPlayer1Score(), gameState.getPlayer1Score());
        assertEquals(newGameState.getPlayer2Score(), gameState.getPlayer2Score());
        assertEquals(newGameState.getCurrentPlayerID(), gameState.getCurrentPlayerID());

        //test if the arrays are the same
        for(int i = 0; i < 6; i++ ){ //index 0 is the bottom row
            for(int j = 0; j < 7; j++){ //index 0 is the leftmost column
                assertEquals(newGameState.getGameBoard()[i][j], gameState.getGameBoard()[i][j]);
            }
        }

    }

    @Test
    public void testResetGame() throws Exception {

        ConnectFourGameState gameState = new ConnectFourGameState();
        gameState.setPlayer1Score(5);
        gameState.setPlayer2Score(5);

        ConnectFourGameState newGameState = gameState.resetGame();

        assertEquals(5, newGameState.getPlayer1Score());
        assertEquals(5, newGameState.getPlayer2Score());
    }

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