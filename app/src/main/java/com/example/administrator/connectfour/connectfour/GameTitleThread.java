package com.example.administrator.connectfour.connectfour;

import android.widget.TextView;

/**
 * Created by garciah16 on 12/1/2015.
 */
public class GameTitleThread extends Thread {

    ConnectFourGameState cfGameState;
    TextView title;

    public GameTitleThread(ConnectFourGameState cfGameState, TextView title){
        this.cfGameState = cfGameState;
        this.title = title;
    }

    @Override
    public void run() {
        while (true) {
            if (cfGameState.getGameIsWon() == true) {
                if (cfGameState.getCurrentPlayerID() == cfGameState.PLAYER1_ID) {
                    title.setText("PLAYER 1 WINS");
                } else {
                    title.setText("PLAYER 2 WINS");
                }
            }
            else{
                if(cfGameState.getCurrentPlayerID() == cfGameState.PLAYER1_ID){
                    title.setText("Player 1's turn");
                }
                else {
                    title.setText("Player 2's turn");
                }
            }
        }
    }
}
