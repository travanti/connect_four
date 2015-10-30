package com.example.administrator.connectfour.connectfour;

import com.example.administrator.connectfour.GameFramework.infoMsg.GameState;

/**
 * Created by garciah16 on 10/30/2015.
 */
public class ConnectFourGameState extends GameState {

    public static final int PLAYER1_ID = 0;
    public static final int PLAYER2_ID = 1;

    public static final int EMPTY = 0;
    public static final int TAKEN = 1;
    public static final int PLAYER1TOKEN = 2;
    public static final int PLAYER2TOKEN = 3;

    int player1Score; //total wins for player 1
    int player2Score; //total wins for player 2
    int currentPlayerID; //player 1 ID = 0, player 2 ID = 1
    int[][] gameBoard = new int[6][7]; //a 2d matrix representing the game board

    /**
     * constructor
     */
    public ConnectFourGameState(){
        player1Score = 0;
        player2Score = 0;
        currentPlayerID = PLAYER1_ID;

        //initialize entire gameboard to be empty
        for(int i = 0; i < 6; i++ ){
            for(int j = 0; j < 7; j++){
                gameBoard[i][j] = EMPTY;
            }
        }
    }

    /**
     * constructor
     */
    public ConnectFourGameState(ConnectFourGameState gameState){
        this.player1Score = gameState.getPlayer1Score();
        this.player2Score = gameState.getPlayer2Score();
        this.gameBoard = gameState.getGameBoard();
    }

    public int getPlayer1Score() {return player1Score;}

    public int getPlayer2Score() {return player2Score;}

    public int[][] getGameBoard() {return gameBoard;}

    /**
     * when the player makes a move
     * @param col the column which the player moves the token
     */
    public void onPlayerMove(int col){
        //TODO implement this
    }
}
