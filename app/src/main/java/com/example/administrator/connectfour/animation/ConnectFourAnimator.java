package com.example.administrator.connectfour.animation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.example.administrator.connectfour.connectfour.ConnectFourGameState;

import java.util.ArrayList;

/**
 * animates a canvas where the game will take place,
 * including tokens , a board and a token pool
 * Created by garciah16 on 11/10/2015.
 */
public class ConnectFourAnimator implements Animator {

    public static final int SLOT_LENGTH = 180;
    //instance variables
    ArrayList<Token> tokens = new ArrayList<>(42); //tokens that will be drawn
    int nextIdx = 0;
    Board board = new Board(); //board to be drawn
    TokenPool tokenPoolPlayer1; //tokens drawn out of player 1 pool
    TokenPool tokenPoolPlayer2; //tokens drawn out of player 2 pool
    int velocity = 2;
    int gravity = 2; //the pieces should fall realistically
    ConnectFourGameState gameState; //the current state of the game
    boolean touched = false; //don't start the game until it's started

    @Override
    public int interval() {
        return 30;
    }

    @Override
    public int backgroundColor() {
        return Color.WHITE;
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

        //check if the board has been touched yet
        if(touched == false){
            board.draw(canvas);
            return;
        }

        //we don't want an empty array list
        if(tokens.get(0) == null){
            return;
        }
        Token newToken = tokens.get(0);
        //draw the token with realistic gravity
        newToken.draw(canvas, newToken.getxPos(), newToken.getyPos());
        newToken.setyPos(newToken.getyPos()+velocity);
        velocity += gravity;
        //stop the token at the bottom of the board
        if(newToken.getyPos() > SLOT_LENGTH*5+SLOT_LENGTH/2+20){
            velocity = 0;
        }

        board.draw(canvas);
    }

    @Override
    public void onTouch(MotionEvent event) {
        //create a new token
        //TODO create a new token at specific column
        //TODO implement dragging from a pool
        touched = true;
        Paint p1Paint = new Paint();
        p1Paint.setColor(Color.RED);
        tokens.add(new Token(p1Paint,1,1));
    }



}
