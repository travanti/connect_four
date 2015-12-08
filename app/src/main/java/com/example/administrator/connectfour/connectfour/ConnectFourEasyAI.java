package com.example.administrator.connectfour.connectfour;

import java.util.Random;

/**
 * Created by mueller16 on 12/2/2015.
 */
public class ConnectFourEasyAI extends ConnectFourGameState{

    public static final int PlayerEasyAIid = 2;
    public static final int PLAYEREASYAITOKEN = 4;
    int playerEasyAIScore; //total wins for easy AI


    ConnectFourEasyAI CFEasyAI = new ConnectFourEasyAI();

    public ConnectFourEasyAI(){

        playerEasyAIScore = 0;
        CFEasyAI.currentPlayerID = PlayerEasyAIid;
    }

    public  ConnectFourEasyAI(ConnectFourEasyAI easyAI){

    this.playerEasyAIScore = easyAI.getPlayerEasyAIScore();
    this.currentPlayerID = easyAI.PlayerEasyAIid;
    }

    public int easyAImove(int result){
       //make a random move to be placed in the gameboard
        Random r = new Random();
        int min = 1;
        int max = 6;
        result = r.nextInt(max-min) + min;

        if(result <1 || result > 6){
            return -1;
        }
        else if(CFEasyAI.getCurrentPlayerID() != ConnectFourGameState.PLAYER1_ID){
            CFEasyAI.setCurrentPlayerID(PlayerEasyAIid);
            CFEasyAI.onPlayerMove(result);
            CFEasyAI.nextPlayer();
        }
        return result;
    }


    public int getPlayerEasyAIScore() {return playerEasyAIScore;}
    public void setPlayerEasyAIScore(int playerEasyAIScore) {this.playerEasyAIScore = playerEasyAIScore;}

}
