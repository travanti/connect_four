package com.example.administrator.connectfour.connectfour;

import android.util.Log;

import com.example.administrator.connectfour.GameFramework.infoMsg.GameState;

/**
 * Created by garciah16 on 10/30/2015.
 */
public class ConnectFourGameState extends GameState {

    //define player IDs
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

    /**
     *  on the options screen, the player can reset the entire board,
     *  but keep the current player scores
     */
    public ConnectFourGameState resetGame(){

        //create new game
        ConnectFourGameState newGame = new ConnectFourGameState();
        //save the current player scores
        newGame.setPlayer1Score(this.player1Score);
        newGame.setPlayer2Score(this.player2Score);
        return newGame;
    }


    /**
     * when the player makes a move
     * @param col the column which the player moves the token
     * @return -1 if an error occurs, 0 if success, 1 if board is full
     */
    public int onPlayerMove(int col) {

        //check for index error
        if (col < 0 || col > 6) {
            return -1;
        }
        //check if the column is already full. if so, don't do anything
        if (this.gameBoard[5][col] != EMPTY) {
            //don't do anything
            return 1;
        } else if (currentPlayerID == PLAYER1_ID) {
            //player 1 makes a move
            for (int i = 0; i < 6; i++) {
                if (this.gameBoard[i][col] == EMPTY) {
                    //place the token
                    this.gameBoard[i][col] = PLAYER1TOKEN;
                    //next player's turn
                    this.currentPlayerID = PLAYER2_ID;
                    return 0;
                }
            }
        } else if (currentPlayerID == PLAYER2_ID) {
            //player 2 makes a move
            for (int i = 0; i < 6; i++) {
                if (this.gameBoard[i][col] == EMPTY) {
                    //place the token
                    this.gameBoard[i][col] = PLAYER2TOKEN;
                    //next player's turn
                    this.currentPlayerID = PLAYER1_ID;
                    return 0;
                }
            }
        }
        //else there's an error
            return -1;
    }

    private boolean hasWon(int row, int col){

        /*
        TODO implement win conditions.
        we know the game board and current player from instance variables.
        check vertically up
            - only if row idx is less than 3
        check vertically down
            - only if row idx is greater than 2
        check horizontally left
            - only if column idx is greater than 2
        check horizontally right
            - only if column idx is less than 4
        check diagonally up-right
            - only if column idx is less than 4 & row idx is less than 3
        check diagonally up-left
            - only if column idx is greater than 2 & row idx is less than 3
        check diagonally down-right
            - only if column idx is less than 4 & row idx is greater than 2
        check diagonally down-left
            - only if column idx is less than 4 & row idx is greater than 2
        */
        return false;
    }


    public int getPlayer1Score() {return player1Score;}

    public int getPlayer2Score() {return player2Score;}

    public int[][] getGameBoard() {return gameBoard;}

    public int getCurrentPlayerID() {return currentPlayerID;}

    public void setCurrentPlayerID(int currentPlayerID) {
        this.currentPlayerID = currentPlayerID;
    }

    public void setGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

}
