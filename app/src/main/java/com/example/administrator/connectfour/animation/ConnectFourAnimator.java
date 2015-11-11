package com.example.administrator.connectfour.animation;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.administrator.connectfour.connectfour.ConnectFourGameState;

/**
 * animates a canvas where the game will take place,
 * including tokens , a board and a token pool
 * Created by garciah16 on 11/10/2015.
 */
public class ConnectFourAnimator implements Animator {

    //instance variables
    Token[] tokens; //tokens that will be drawn, probably be an array list instead
    Board board; //board to be drawn
    TokenPool tokenPoolPlayer1; //tokens drawn out of player 1 pool
    TokenPool tokenPoolPlayer2; //tokens drawn out of player 2 pool
    int gravity; //the pieces should fall realistically
    ConnectFourGameState gameState; //the current state of the game

    @Override
    public int interval() {
        return 0;
    }

    @Override
    public int backgroundColor() {
        return 0;
    }

    @Override
    public boolean doPause() {
        return false;
    }

    @Override
    public boolean doQuit() {
        return false;
    }

    @Override
    public void tick(Canvas canvas) {

    }

    @Override
    public void onTouch(MotionEvent event) {

    }
}
