package com.example.administrator.connectfour.connectfour;

import android.util.Log;

import com.example.administrator.connectfour.GameFramework.infoMsg.GameState;

/**
 * Created by garciah16 on 10/30/2015.
 */
public class ConnectFourGameState extends GameState {

    public static final int PLAYER1_ID = 0;
    public static final int PLAYER2_ID = 1;

    //constants for slots on the game board, so we know what is in each slot
    public static final int EMPTY = 0;
    public static final int TAKEN = 1;
    public static final int PLAYER1TOKEN = 2;
    public static final int PLAYER2TOKEN = 3;

    int player1Score; //total wins for player 1
    int player2Score; //total wins for player 2
    int currentPlayerID; //player 1 ID = 0, player 2 ID = 1
    int[][] gameBoard = new int[6][7]; //a 2d matrix representing the game board
                                       //first index is row, second index is column

    /**
     * constructor
     */
    public ConnectFourGameState(){
        player1Score = 0;
        player2Score = 0;
        currentPlayerID = PLAYER1_ID;

        //initialize entire gameboard to be empty
        for(int i = 0; i < 6; i++ ){ //index 0 is the bottom row
            for(int j = 0; j < 7; j++){ //index 0 is the leftmost column
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
        this.currentPlayerID = gameState.getCurrentPlayerID();
    }

    public int getPlayer1Score() {return player1Score;}

    public int getPlayer2Score() {return player2Score;}

    public int[][] getGameBoard() {return gameBoard;}

    public int getCurrentPlayerID() {return currentPlayerID;}

    /**
     * when the player makes a move
     * @param col the column which the player moves the token
     */
    public void onPlayerMove(int col) {

        //check for index error
        if (col < 0 || col > 6) {
            Log.e("ConnectFourGameState", "incorrect column index");
            return;
        }
        //check if the column is already full. if so, don't do anything
        if (this.gameBoard[5][col] != EMPTY) {
            //don't do anything
            return;
        }
        else if (currentPlayerID == PLAYER1_ID) {
            //player 1 makes a move
            for (int i = 0; i < 5; i++) {
                if (this.gameBoard[i][col] == EMPTY) {
                    //place the token
                    this.gameBoard[i][col] = PLAYER1TOKEN;
                    //next player's turn
                    this.currentPlayerID = PLAYER2_ID;
                    return;
                }
            }
        }
        else if (currentPlayerID == PLAYER2_ID) {
            //player 2 makes a move
            for (int i = 0; i < 5; i++) {
                if (this.gameBoard[i][col] == EMPTY) {
                    //place the token
                    this.gameBoard[i][col] = PLAYER2TOKEN;
                    //next player's turn
                    this.currentPlayerID = PLAYER1_ID;
                    return;
                }
            }
        }
        else { //there's an error
            Log.e("ConnectFourGameState", "there was an error making a move");
            return;
        }
    }

    private boolean hasWon(int row, int col){

        /*
        TODO implement win conditions.
        we know the game board and current player from instance variables.
        check vertically up
        check vertically down
        check horizontally left
        check horizontally right
        check diagonally up-right
        check diagonally up-left
        check diagonally down-right
        check diagonally down-left
        */
        return false;
    }



}
