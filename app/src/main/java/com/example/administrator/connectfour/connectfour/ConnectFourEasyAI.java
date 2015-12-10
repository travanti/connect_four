package com.example.administrator.connectfour.connectfour;

import java.util.Random;

/**
 * Created by mueller16 on 12/2/2015.
 */
public class ConnectFourEasyAI extends ConnectFourGameState{

    public static final int PlayerEasyAIid = 2;
    public static final int PLAYEREASYAITOKEN = 4;
    int playerEasyAIScore; //total wins for easy AI


    public ConnectFourEasyAI(){

        playerEasyAIScore = 0;
    }

    public  ConnectFourEasyAI(ConnectFourEasyAI easyAI){

    this.playerEasyAIScore = easyAI.getPlayerEasyAIScore();
    this.currentPlayerID = easyAI.PlayerEasyAIid;
    }

    public int easyAImove(){
       //make a random move to be placed in the gameboard
        Random r = new Random();
        int min = 0;
        int max = 8;
        int result = r.nextInt(max-min) + min;

        if(result <1 || result > 7){
            return -1;
        }

        return result;
    }



    public int getPlayerEasyAIScore() {return playerEasyAIScore;}
    public void setPlayerEasyAIScore(int playerEasyAIScore) {this.playerEasyAIScore = playerEasyAIScore;}

}
