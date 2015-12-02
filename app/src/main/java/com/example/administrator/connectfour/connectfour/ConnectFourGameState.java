package com.example.administrator.connectfour.connectfour;

import android.util.Log;

import com.example.administrator.connectfour.GameFramework.infoMsg.GameState;

/**
 * handles the current state of the game, including initialization,
 * and win conditions.
 * Created by garciah16 on 10/30/2015.
 * Modified by Mueller16 on 11/30/2015
 */
public class ConnectFourGameState extends GameState {

    //define player IDs
    public static final int PLAYER1_ID = 0;
    public static final int PLAYER2_ID = 1;
    public static final int PlayerEasyAI = 2;
    public static final int PlayerHardAI = 3;

    //constants for slots on the game board, so we know what is in each slot
    public static final int EMPTY = 0;
    public static final int TAKEN = 1;
    public static final int PLAYER1TOKEN = 2;
    public static final int PLAYER2TOKEN = 3;
    public static final int PLAYEREASYAITOKEN = 4;
    public static final int PLAYERHARDAITOKEN = 5;


    int player1Score; //total wins for player 1
    int player2Score; //total wins for player 2
    int playerEasyAIScore; //total wins for easy AI
    int playerHardAIScore; //total wins for hard AI
    int currentPlayerID; //player 1 ID = 0, player 2 ID = 1
    int[][] gameBoard = new int[6][7]; //a 2d matrix representing the game board
                                       //first index is row, second index is column
    boolean gameIsWon = false;

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
     * @return -1 if an error occurs or board is full, row idx if successful move
     */
    public int onPlayerMove(int col) {

        //check for index error
        if (col < 0 || col > 6) {
            return -1;
        }
        //check if the column is already full. if so, don't do anything
        if (this.gameBoard[5][col] != EMPTY) {
            //don't do anything
            return -1;
        } else if (currentPlayerID == PLAYER1_ID) {
            //player 1 makes a move
            for (int i = 0; i < 6; i++) {
                if (this.gameBoard[i][col] == EMPTY) {
                    //place the token
                    this.gameBoard[i][col] = PLAYER1TOKEN;
                    return i +1; //add offset for the animation
                }
            }
        } else if (currentPlayerID == PLAYER2_ID) {
            //player 2 makes a move
            for (int i = 0; i < 6; i++) {
                if (this.gameBoard[i][col] == EMPTY) {
                    //place the token
                    this.gameBoard[i][col] = PLAYER2TOKEN;
                    return i+1;
                }
            }
        }
        //else there's an error
            return -1;
    }

    /**
     *
     * @param row row the last piece was dropped
     * @param col col the last piece was dropped
     * @param playerID current player
     * @return true if the current player has won, false if not
     */
    public boolean hasWon(int row, int col, int playerID){

        int token; //identify the type of token we are checking

        if(playerID == PLAYER1_ID){
            token = PLAYER1TOKEN;
        }
        else{
            token = PLAYER2TOKEN;
        }

//        we know the game board from instance variables.
//        check vertically up
//            - only if row idx is less than 3
        boolean win1 = false;
        if(row < 3){
            for(int i = 0; i < 4; i++){
                if(gameBoard[row+i][col] == token){
                    win1 = true;
                }
                else{
                    win1 = false;
                    break;
                }
            }
        }
//        check vertically down
//            - only if row idx is greater than 2
        boolean win2 = false;
        if(row > 2){
            for(int i = 0; i < 4; i++){
                if(gameBoard[row-i][col] == token){
                    win2 = true;
                }
                else{
                    win2 = false;
                    break;
                }
            }
        }
//        check horizontally left
//            - only if column idx is greater than 2
        boolean win3 = false;
        if(col > 2){
            for(int i = 0; i < 4; i++){
                if(gameBoard[row][col-i] == token){
                    win3 = true;
                }
                else{
                    win3 = false;
                    break;
                }
            }
        }
//        check horizontally right
//            - only if column idx is less than 4
        boolean win4 = false;
        if(col < 4){
            for(int i = 0; i <4; i++){
                if(gameBoard[row][col+i] == token){
                    win4 = true;
                }
                else{
                    win4 = false;
                    break;
                }
            }
        }
//        check diagonally up-right
//            - only if column idx is less than 4 & row idx is less than 3
        boolean win5 = false;
        if(col < 4 && row < 3){
            for (int i = 0; i < 4; i++){
                if(gameBoard[row+i][col+i] == token){
                    win5 = true;
                }
                else{
                    win5 = false;
                    break;
                }
            }
        }
//        check diagonally up-left
//            - only if column idx is greater than 2 & row idx is less than 3
        boolean win6 = false;
        if(col > 2 && row < 3){
            for(int i = 0; i < 4; i++){
                if(gameBoard[row+i][col-i] == token){
                    win6 = true;
                }
                else{
                    win6 = false;
                    break;
                }
            }
        }
//        check diagonally down-right
//            - only if column idx is less than 4 & row idx is greater than 2
        boolean win7 = false;
        if(col < 4 && row > 2){
            for(int i = 0; i < 4; i++){
                if(gameBoard[row-i][col+i] == token){
                    win7 = true;
                }
                else{
                    win7 = false;
                    break;
                }
            }
        }
//        check diagonally down-left
//            - only if column idx is less than 4 & row idx is greater than 2
        boolean win8 = false;
        if(col < 4 && row > 2){
            for(int i = 0; i < 4; i++){
                if(gameBoard[row-i][col-i] == token){
                    win8 = true;
                }
                else{
                    win8 = false;
                    break;
                }
            }
        }

        if(win1 || win2 || win3 ||win4||win5||win6||win7||win8){
            gameIsWon = true;
            return true;
        }
        return false;
    }


    public int getPlayer1Score() {return player1Score;}

    public int getPlayer2Score() {return player2Score;}

    public int getPlayerEasyAIScore() {return playerEasyAIScore;}

    public int getPlayerHardAIScore() {return playerHardAIScore;}

    public int[][] getGameBoard() {return gameBoard;}

    public int getCurrentPlayerID() {return currentPlayerID;}

    public void setCurrentPlayerID(int currentPlayerID) {
        this.currentPlayerID = currentPlayerID;
    }

    public void nextPlayer(){
        if(currentPlayerID == PLAYER1_ID){
            setCurrentPlayerID(PLAYER2_ID);
        }
        else{
            setCurrentPlayerID(PLAYER1_ID);
        }
    }

    public void setGameBoard(int[][] gameBoard) {this.gameBoard = gameBoard;}

    public void setPlayer1Score(int player1Score) {this.player1Score = player1Score;}

    public void setPlayer2Score(int player2Score) {this.player2Score = player2Score;}

    public void setPlayerEasyAIScore(int playerEasyAIScore) {this.playerEasyAIScore = playerEasyAIScore;}

    public void setPlayerHardAIScore(int playerHardAIScore) {this.playerHardAIScore = playerHardAIScore;}

    public boolean getGameIsWon(){
        return gameIsWon;
    }


}
