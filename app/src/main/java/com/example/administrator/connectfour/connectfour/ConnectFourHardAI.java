package com.example.administrator.connectfour.connectfour;

import android.util.Log;

import com.example.administrator.connectfour.GameFramework.infoMsg.GameState;
import com.example.administrator.connectfour.MainActivity;
import com.example.administrator.connectfour.animation.Board;

import java.util.Random;

/**
 * hard AI that blocks opponents moves and
 * will also search for winning moves,
 * while not putting itself at a disadvantage.
 * Created by mueller16 on 12/5/2015.
 */
public class ConnectFourHardAI {

    ConnectFourGameState CFgameState;

    public ConnectFourHardAI(ConnectFourGameState CFgameState){
        this.CFgameState = CFgameState;
    }

    /*
    Method which goes through the various cases which are possible
    for the AI to either win or for it to make a blocking move to
    stop the opponent from winning
     */
    public int alternateHardAImove(int board[][]){


        int result = 0; //the column where the AI will play
        boolean [] blackCol= new boolean[7]; //the AI SHOULD NOT play in this column

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                //check through the rows to see if the AI has three tokens in a row forward, if they do block the third token
                if (board[i][j] == ConnectFourGameState.PLAYERHARDAITOKEN && board[i][j + 1] == ConnectFourGameState.PLAYERHARDAITOKEN
                        && board[i][j + 2] == ConnectFourGameState.PLAYERHARDAITOKEN && board[i][j+3] == CFgameState.EMPTY) {
                    //result = board[i][j+3];
                    if (i - 1 >= 0 && i + 1 <= 6) {
                            if (board[i - 1][j + 3] != 0) {
                                Log.d("HardAIrow", "we're placing for AI block in row forward");
                                result = j + 4;
                                return  result;
                            }
                            else{
                                blackCol[j+3] = true;
                                break;
                            }
                    }
                    else{
                        return j+4;
                    }
                }
                //check through the rows to see if the human has three tokens in a row forward, if they do block the third token
                else if (board[i][j] == ConnectFourGameState.PLAYER1TOKEN && board[i][j + 1] == ConnectFourGameState.PLAYER1TOKEN
                        && board[i][j + 2] == ConnectFourGameState.PLAYER1TOKEN && board[i][j+3] == CFgameState.EMPTY) {
                    //make sure that the row below the added position is not empty
                    if (i - 1 >= 0 && i + 1 <= 6) {
                            if (board[i - 1][j + 3] != 0) {
                                Log.d("HardAIrow", "we're placing for player block in row forward");

                                result = j + 4;
                                return result;
                            }
                            else{
                                blackCol[j+3] = true;
                                break;
                            }
                    }
                    else{
                        return j+4;
                    }

                }

            }
        }
        //check through the rows to see if the AI has three tokens in a row backward, if they do block the third token
        for(int i= 0; i< 5;i++) {//rows
            for (int j = 6; j >2; j--) { //columns
                if(board[i][j] == ConnectFourGameState.PLAYERHARDAITOKEN && board[i][j-1] == ConnectFourGameState.PLAYERHARDAITOKEN
                        && board[i][j-2] == ConnectFourGameState.PLAYERHARDAITOKEN && board[i][j-3] == CFgameState.EMPTY){
                    //result = board[i][j-3];
                    if (i - 1 >= 0 && i + 1 <= 6) {
                            if (board[i - 1][j - 3] != 0) {
                                Log.d("HardAIrow", "we're placing for AI block in row backward");
                                result = j - 2;
                                return result;
                            }
                            else{
                                blackCol[j-3] = true;
                                break;
                            }
                    }
                    else{
                        return j-2;
                    }
                }
                //check through the rows to see if the human has three tokens in a row backward, if they do block the third token
                else if(board[i][j] == ConnectFourGameState.PLAYER1TOKEN && board[i][j-1] == ConnectFourGameState.PLAYER1TOKEN
                        && board[i][j-2] == ConnectFourGameState.PLAYER1TOKEN && board[i][j-3] == CFgameState.EMPTY){
                        // result = board[i][j-3];
                    if (i - 1 >= 0 && i + 1 <= 6) {
                            if (board[i - 1][j - 3] != 0) {
                                Log.d("HardAIrow", "we're placing for player block in row backward");

                                result = j-2;
                                return result;
                            }
                            else{
                                blackCol[j-3] = true;
                                break;
                            }
                    }
                    else{
                        return j-2;
                    }
                }
            }
        }
        //check through the COLUMNS to see if the human has three tokens in a column, if they do block
        for (int i = 0; i < 3; i++) {//rows
            for(int j = 0; j< 7; j++) { //columns
                //check through the COLUMNS to see if the AI has three tokens in a column, if they do claim the win
                 if (board[i][j] == ConnectFourGameState.PLAYERHARDAITOKEN && board[i + 1][j] == ConnectFourGameState.PLAYERHARDAITOKEN &&
                        board[i + 2][j] == ConnectFourGameState.PLAYERHARDAITOKEN && board[i+3][j] == CFgameState.EMPTY){
                    Log.d("HardAIColumn", "we're placing token for AI win in column");
                    result = j+1;
                    return result;
                }
                 //check through the COLUMNS to see if the human has three tokens in a column, if they do claim the win
                 else if (board[i][j] == ConnectFourGameState.PLAYER1TOKEN && board[i + 1][j] == ConnectFourGameState.PLAYER1TOKEN &&
                        board[i + 2][j] == ConnectFourGameState.PLAYER1TOKEN && board[i+3][j] == CFgameState.EMPTY){
                    Log.d("HardAIColumn", "we're placing for player block in column");
                    result = j+1;
                    return result;
                }

            }

        }
        //having the AI either block a player token from being a diagonal up right win or placing its own token for a diagonal win
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 4; j++) {
                if (board[i][j] == ConnectFourGameState.PLAYERHARDAITOKEN && board[i + 1][j + 1] == ConnectFourGameState.PLAYERHARDAITOKEN &&
                        board[i + 2][j + 2] == ConnectFourGameState.PLAYERHARDAITOKEN && board[i + 3][j + 3] == CFgameState.EMPTY){
                    if (board[i+2][j+3] != CFgameState.EMPTY) { //checks if we are over a spot filled so token won't fall
                        Log.d("HardAIrow", "we're placing for AI block diagonally up right");
                        result = j + 4;
                        return result;
                    }
                    else{
                        blackCol[j+3] = true;
                        break;
                    }
                }
                else if (board[i][j] == ConnectFourGameState.PLAYER1TOKEN && board[i + 1][j + 1] == ConnectFourGameState.PLAYER1TOKEN &&
                        board[i + 2][j + 2] == ConnectFourGameState.PLAYER1TOKEN && board[i + 3][j + 3] == CFgameState.EMPTY){
                        if (board[i+2][j+3] != CFgameState.EMPTY) { //checks if we are over a spot filled so token won't fall
                                Log.d("HardAIrow", "we're placing for player token block diagonally up right");
                                result = j + 4;
                                return result;
                        }
                    else{
                            blackCol[j+3] = true;
                        break;
                    }
                }

            }
        }
        //having the AI either block a player token from being a diagonal up left win or placing its own token for a diagonal win
        for (int i = 0; i < 3; i++) {
            for(int j = 6; j > 2; j--) {
                if (board[i][j] == ConnectFourGameState.PLAYERHARDAITOKEN && board[i + 1][j - 1] == ConnectFourGameState.PLAYERHARDAITOKEN &&
                        board[i + 2][j - 2] == ConnectFourGameState.PLAYERHARDAITOKEN && board[i + 3][j - 3] == CFgameState.EMPTY){
                    if (board[i + 2][j - 3] != CFgameState.EMPTY) { //checks if we are over a spot filled so token won't fall
                        Log.d("HardAIrow", "we're placing for AI win diagonally up left");
                        result = j - 2;
                        return result;
                    }
                    else{
                        blackCol[j-3] = true;
                        break;
                    }
                }
                else if (board[i][j] == ConnectFourGameState.PLAYER1TOKEN && board[i + 1][j - 1] == ConnectFourGameState.PLAYER1TOKEN &&
                        board[i + 2][j - 2] == ConnectFourGameState.PLAYER1TOKEN && board[i + 3][j - 3] == CFgameState.EMPTY){
                    if (board[i + 2][j - 3] != CFgameState.EMPTY) { //checks if we are over a spot filled so token won't fall
                        Log.d("HardAIrow", "we're placing for player block diagonally up left");
                        result = j - 2;
                        return result;
                    }
                    else{
                        blackCol[j-3] = true;
                        break;
                    }
                }

            }
        }
        //having the AI either block a player token from being a diagonal down right win or placing its own token for a diagonal win
        for (int i = 5; i > 2; i--) {
            for(int j = 0; j > 4; j++) {
                if (board[i][j] == ConnectFourGameState.PLAYERHARDAITOKEN && board[i - 1][j + 1] == ConnectFourGameState.PLAYERHARDAITOKEN &&
                        board[i - 2][j + 2] == ConnectFourGameState.PLAYERHARDAITOKEN && board[i - 3][j + 3] == CFgameState.EMPTY){
                    if (board[i - 2][j + 3] != CFgameState.EMPTY) { //checks if we are over a spot filled so token won't fall
                        Log.d("HardAIrow", "we're placing for AI win diagonally down right");
                        result = j + 4;
                        return result;
                    }
                    else{
                        blackCol[j+3] = true;
                        break;
                    }
                }
                else if (board[i][j] == ConnectFourGameState.PLAYER1TOKEN && board[i - 1][j + 1] == ConnectFourGameState.PLAYER1TOKEN &&
                        board[i - 2][j + 2] == ConnectFourGameState.PLAYER1TOKEN && board[i - 3][j + 3] == CFgameState.EMPTY){
                    if (board[i - 2][j + 3] != CFgameState.EMPTY) { //checks if we are over a spot filled so token won't fall
                        Log.d("HardAIrow", "we're placing for player block diagonally down right");
                        result = j + 4;
                        return result;
                    }
                    else{
                        blackCol[j+3] = true;
                        break;
                    }
                }

            }
        }

        //having the AI either block a player token from being a diagonal down left win or placing its own token for a diagonal win
        for (int i = 5; i > 2; i--) {
            for(int j = 6; j < 2; j--) {
                if (board[i][j] == ConnectFourGameState.PLAYERHARDAITOKEN && board[i - 1][j - 1] == ConnectFourGameState.PLAYERHARDAITOKEN &&
                        board[i - 2][j - 2] == ConnectFourGameState.PLAYERHARDAITOKEN && board[i - 3][j - 3] == CFgameState.EMPTY){
                    if (board[i - 2][j - 3] != CFgameState.EMPTY) { //checks if we are over a spot filled so token won't fall
                        Log.d("HardAIrow", "we're placing for AI win diagonally down left");
                        result = j - 4;
                        return result;
                    }
                    else{
                        blackCol[j-3] = true;
                        break;
                    }
                }
                else if (board[i][j] == ConnectFourGameState.PLAYER1TOKEN && board[i - 1][j - 1] == ConnectFourGameState.PLAYER1TOKEN &&
                        board[i - 2][j - 2] == ConnectFourGameState.PLAYER1TOKEN && board[i - 3][j - 3] == CFgameState.EMPTY){
                    if (board[i - 2][j - 3] != CFgameState.EMPTY) { //checks if we are over a spot filled so token won't fall
                        Log.d("HardAIrow", "we're placing for player block diagonally down left");
                        result = j - 4;
                        return result;
                    }
                    else{
                        blackCol[j-3] = true;
                        break;
                    }
                }

            }
        }

        //check for inbetweeners horizontally
        for(int i = 0; i<6; i++){
            for(int j = 1; j<5; j++){
                if(board[i][j+1] == ConnectFourGameState.PLAYERHARDAITOKEN
                        && board[i][j-1] == ConnectFourGameState.PLAYERHARDAITOKEN
                        && board[i][j+2] == ConnectFourGameState.PLAYERHARDAITOKEN
                        && board[i][j] == CFgameState.EMPTY){
                    if(i > 0 && board[i-1][j] != ConnectFourGameState.EMPTY) {
                        Log.d("inbetween", "winning oxoo horizontal");
                        result = j + 1;
                        return result;
                    }else{
                        return j+1;
                    }
                }
            }

        }
        //OOXO case
        for(int i = 0; i<6; i++){
            for(int j = 2; j<6; j++){
                if(board[i][j+1] == ConnectFourGameState.PLAYERHARDAITOKEN
                        && board[i][j-1] == ConnectFourGameState.PLAYERHARDAITOKEN
                        && board[i][j-2] == ConnectFourGameState.PLAYERHARDAITOKEN
                        && board[i][j] == CFgameState.EMPTY){
                    if(i > 0 && board[i-1][j] != ConnectFourGameState.EMPTY) {
                        Log.d("inbetween", "winning ooxo horizontal");
                        result = j + 1;
                        return result;
                    }else{
                        return j+1;
                    }
                }
            }

        }
        //OXOO case
        for(int i = 0; i<6; i++){
            for(int j = 1; j<5; j++){
                if(board[i][j+1] == ConnectFourGameState.PLAYER1TOKEN
                        && board[i][j-1] == ConnectFourGameState.PLAYER1TOKEN
                        && board[i][j+2] == ConnectFourGameState.PLAYER1TOKEN
                        && board[i][j] == CFgameState.EMPTY){
                    if(i > 0 && board[i-1][j] != ConnectFourGameState.EMPTY) {
                        Log.d("inbetween", "blocking oxoo horizontal");
                        result = j + 1;
                        return result;
                    }else{
                        return j+1;
                    }
                }
            }

        }
        //OOXO case
        for(int i = 0; i<6; i++){
            for(int j = 2; j<6; j++){
                if(board[i][j+1] == ConnectFourGameState.PLAYER1TOKEN
                        && board[i][j-1] == ConnectFourGameState.PLAYER1TOKEN
                        && board[i][j-2] == ConnectFourGameState.PLAYER1TOKEN
                        && board[i][j] == CFgameState.EMPTY){
                    if(i > 0 && board[i-1][j] != ConnectFourGameState.EMPTY) {
                        Log.d("inbetween", "blocking ooxo horizontal");
                        result = j + 1;
                        return result;
                    }else{
                        return j+1;
                    }
                }
            }

        }
        //diagonal imbetweeners
        /*
            oxoo up
         */
        for(int i = 1; i<5; i++){
            for(int j = 1; j<5; j++){
                if(board[i-1][j-1] == ConnectFourGameState.PLAYERHARDAITOKEN
                        && board[i+1][j+1] == ConnectFourGameState.PLAYERHARDAITOKEN
                        && board[i+2][j+2] == ConnectFourGameState.PLAYERHARDAITOKEN
                        && board[i][j] == CFgameState.EMPTY){
                    if(i > 0 && board[i-1][j] != ConnectFourGameState.EMPTY) {
                        Log.d("inbetween", "winning oxoo up diagonal");
                        result = j + 1;
                        return result;
                    }else{
                        return j+1;
                    }
                }
            }

        }
        for(int i = 1; i<5; i++){
            for(int j = 1; j<5; j++){
                if(board[i-1][j-1] == ConnectFourGameState.PLAYER1TOKEN
                        && board[i+1][j+1] == ConnectFourGameState.PLAYER1TOKEN
                        && board[i+2][j+2] == ConnectFourGameState.PLAYER1TOKEN
                        && board[i][j] == CFgameState.EMPTY){
                    if(i > 0 && board[i-1][j] != ConnectFourGameState.EMPTY) {
                        Log.d("inbetween", "blocking oxoo up diagonal");
                        result = j + 1;
                        return result;
                    }else{
                        return j+1;
                    }
                }
            }

        }
        /*
            ooxo up
         */
        for(int i = 2; i<5; i++){
            for(int j = 2; j<6; j++){
                if(board[i-2][j-2] == ConnectFourGameState.PLAYERHARDAITOKEN
                        && board[i-1][j-1] == ConnectFourGameState.PLAYERHARDAITOKEN
                        && board[i+1][j+1] == ConnectFourGameState.PLAYERHARDAITOKEN
                        && board[i][j] == CFgameState.EMPTY){
                    if(i > 0 && board[i-1][j] != ConnectFourGameState.EMPTY) {
                        Log.d("inbetween", "winning ooxo up diagonal");
                        result = j + 1;
                        return result;
                    }else{
                        return j+1;
                    }
                }
            }

        }
        for(int i = 2; i<5; i++){
            for(int j = 2; j<6; j++){
                if(board[i-2][j-2] == ConnectFourGameState.PLAYER1TOKEN
                        && board[i-1][j-1] == ConnectFourGameState.PLAYER1TOKEN
                        && board[i+1][j+1] == ConnectFourGameState.PLAYER1TOKEN
                        && board[i][j] == CFgameState.EMPTY){
                    if(i > 0 && board[i-1][j] != ConnectFourGameState.EMPTY) {
                        Log.d("inbetween", "blocking ooxo up diagonal");
                        result = j + 1;
                        return result;
                    }else{
                        return j+1;
                    }
                }
            }

        }
        //diagonal oxoo down AI win case
        for(int i = 2; i<5; i++){
            for(int j = 2; j<5; j++){
                if(board[i+1][j-1] == ConnectFourGameState.PLAYERHARDAITOKEN
                        && board[i-1][j+1] == ConnectFourGameState.PLAYERHARDAITOKEN
                        && board[i-2][j+2] == ConnectFourGameState.PLAYERHARDAITOKEN
                        && board[i][j] == CFgameState.EMPTY){
                    if(i > 0 && board[i-1][j] != ConnectFourGameState.EMPTY) {
                        Log.d("inbetween", "winning oxoo down diagonal");
                        result = j + 1;
                        return result;
                    }else{
                        return j+1;
                    }
                }
            }

        }
        //diagonal oxoo down player blocking case
        for(int i = 2; i<5; i++){
            for(int j = 2; j<5; j++){
                if(board[i+1][j-1] == ConnectFourGameState.PLAYER1TOKEN
                        && board[i-1][j+1] == ConnectFourGameState.PLAYER1TOKEN
                        && board[i-2][j+2] == ConnectFourGameState.PLAYER1TOKEN
                        && board[i][j] == CFgameState.EMPTY){
                    if(i > 0 && board[i-1][j] != ConnectFourGameState.EMPTY) {
                        Log.d("inbetween", "blocking oxoo down diagonal");
                        result = j + 1;
                        return result;
                    }else{
                        return j+1;
                    }
                }
            }

        }
        //diagonal ooxo down AI win case
        for(int i = 1; i<4; i++){
            for(int j = 2; j<5; j++){
                if(board[i-1][j+1] == ConnectFourGameState.PLAYERHARDAITOKEN
                        && board[i+1][j-1] == ConnectFourGameState.PLAYERHARDAITOKEN
                        && board[i+2][j-2] == ConnectFourGameState.PLAYERHARDAITOKEN
                        && board[i][j] == CFgameState.EMPTY){
                    if(i > 0 && board[i-1][j] != ConnectFourGameState.EMPTY) {
                        Log.d("inbetween", "winning ooxo down diagonal");
                        result = j + 1;
                        return result;
                    }else{
                        return j+1;
                    }
                }
            }

        }
        //diagonal ooxo down player blocking case
        for(int i = 1; i<4; i++){
            for(int j = 2; j<5; j++){
                if(board[i-1][j+1] == ConnectFourGameState.PLAYER1TOKEN
                        && board[i+1][j-1] == ConnectFourGameState.PLAYER1TOKEN
                        && board[i+2][j-2] == ConnectFourGameState.PLAYER1TOKEN
                        && board[i][j] == CFgameState.EMPTY){
                    if(i > 0 && board[i-1][j] != ConnectFourGameState.EMPTY) {
                        Log.d("inbetween", "blocking ooxo down diagonal");
                        result = j + 1;
                        return result;
                    }else{
                        return j+1;
                    }
                }
            }

        }

        //else make a random move to be placed in the gameboard
        Random r = new Random();
        int min = 1;
        int max = 8;
        result = r.nextInt(max-min) + min;
        //if the random column is blacklisted, don't use
        //result and recalculate
        while(blackCol[result-1]){
            result = r.nextInt(max-min) + min;
        }
        //make sure the move is not out of the array
        if(result <1 || result > 7){
            return -1;
        }

        for(int i = 0; i < 6; i++){
            blackCol[i] = false;
        }

        return result;
    }


}
