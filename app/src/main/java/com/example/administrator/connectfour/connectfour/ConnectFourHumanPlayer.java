package com.example.administrator.connectfour.connectfour;

import android.view.View;

import com.example.administrator.connectfour.GameFramework.GameHumanPlayer;
import com.example.administrator.connectfour.GameFramework.GameMainActivity;
import com.example.administrator.connectfour.GameFramework.infoMsg.GameInfo;

/**
 * Created by garciah16 on 10/30/2015.
 */
public class ConnectFourHumanPlayer extends GameHumanPlayer {
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
