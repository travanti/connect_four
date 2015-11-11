package com.example.administrator.connectfour.connectfour;

import android.view.View;
import android.widget.Button;

import com.example.administrator.connectfour.GameFramework.GameHumanPlayer;
import com.example.administrator.connectfour.GameFramework.GameMainActivity;
import com.example.administrator.connectfour.GameFramework.infoMsg.GameInfo;

/**
 * A GUI for a human to play Connect Four. Listens and responds to the user's actions
 * Created by garciah16 on 10/30/2015.
 */
public class ConnectFourHumanPlayer extends GameHumanPlayer {

    //instance variables
    GameMainActivity myActivity; //activity which the game takes place
    Button settingsButton; //the button to get to the settings activity

    /**
     * constructor
     *
     * @param name
     */
    public ConnectFourHumanPlayer(String name) {
        super(name);
    }

    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {

    }

    @Override
    public void setAsGui(GameMainActivity activity) {

    }
}
