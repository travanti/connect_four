package com.example.administrator.connectfour.connectfour;

import com.example.administrator.connectfour.GameFramework.GamePlayer;
import com.example.administrator.connectfour.GameFramework.actionMsg.GameAction;

/**
 * Created by garciah16 on 10/30/2015.
 */
public class ConnectFourMoveAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public ConnectFourMoveAction(GamePlayer player) {
        super(player);
    }
}
