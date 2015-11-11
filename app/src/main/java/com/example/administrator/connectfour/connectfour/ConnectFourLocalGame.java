package com.example.administrator.connectfour.connectfour;

import com.example.administrator.connectfour.GameFramework.GamePlayer;
import com.example.administrator.connectfour.GameFramework.LocalGame;
import com.example.administrator.connectfour.GameFramework.actionMsg.GameAction;

/**
 * the game that sends info to the players and knows how to play the game.
 * handles the rules of the game
 * Created by garciah16 on 10/30/2015.
 */
public class ConnectFourLocalGame extends LocalGame {
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {

    }

    @Override
    protected boolean canMove(int playerID) {
        return false;
    }

    @Override
    protected String checkIfGameOver() {
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        return false;
    }
}
