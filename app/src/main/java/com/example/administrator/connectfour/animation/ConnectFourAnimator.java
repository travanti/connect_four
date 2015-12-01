package com.example.administrator.connectfour.animation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.example.administrator.connectfour.MainActivity;
import com.example.administrator.connectfour.connectfour.ConnectFourGameState;

import java.util.ArrayList;

import static android.graphics.Color.*;

/**
 * animates a canvas where the game will take place,
 * including tokens , a board and a token pool
 * Created by garciah16 on 11/10/2015.
 * Editted by travanti16 on 11/25/2015
 * Editted by muller16 on 11/25/2015
 */
public class ConnectFourAnimator implements Animator {
    //constants
    public static final int SLOT_LENGTH = 180;
    //instance variables
    ArrayList<Token> tokens = new ArrayList<>(42); //tokens that will be drawn
    //TODO replace the 1D array list with 2D array list
//    ArrayList[][] freeSpaces = new ArrayList[7][6];
//
//    public static final int SPACE_FREE = 0;
//    public static final int SPACE_TAKEN = 1;
//    public static final int SPACE_RED = 2;
//    public static final int SPACE_YELLOW = 3;

    int nextIdx = 0;
    Board board = new Board(); //board to be drawn
    TokenPool p1Pool = new TokenPool(Color.RED, 130, 1000);
    TokenPool p2Pool = new TokenPool(Color.YELLOW, 1650, 1000);
    TokenPool tokenPoolPlayer1; //tokens drawn out of player 1 pool
    TokenPool tokenPoolPlayer2; //tokens drawn out of player 2 pool
    int gravity = 2; //the pieces should fall realistically
    ConnectFourGameState gameState = MainActivity.gameState; //the current state of the game
    boolean touched = false; //don't start the game until it's started

    @Override
    public int interval() {
        return 30;
    }

    @Override
    public int backgroundColor() {
        return WHITE;
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
            if (touched == false) {
                synchronized (board) {
                    board.draw(canvas);
                }
                synchronized (p1Pool) {
                    p1Pool.draw(canvas); //to draw pool positions
                }
                synchronized (p2Pool) {
                    p2Pool.draw(canvas);
                }
                return;
            }


            //we don't want an empty array list
            synchronized (tokens) {
                if (tokens.get(0) == null) {
                    return;
                }
            }
            //draw every token created on the board
            synchronized (tokens) {
                for (Token token : tokens) {
                    //draw the token with realistic gravity
                    //TODO investigate input speed related crash in animator.
                    synchronized (token) { //ensuring threads don't try to access token at same time, slows input related crash...sometimes
                        token.draw(canvas, token.getxPos(), token.getyPos());
                        token.setyPos(token.getyPos() + token.getVelocity());
                        token.setVelocity(token.getVelocity() + gravity);
                    }
                    //stop the token at the bottom of the board
                    //stop at highest empty position
                    synchronized (token) { //ensuring threads don't try to access token at same time, slows input related crash...sometimes
                        if (token.getyPos() > SLOT_LENGTH * (5 - token.row) + SLOT_LENGTH / 2) {
                            token.setVelocity(0);
                            token.setyPos(SLOT_LENGTH * (5 - token.row) + SLOT_LENGTH / 2 + 13);
                        }
                    }
                }
            }
            synchronized (board) {
                board.draw(canvas);
            }
            synchronized (p1Pool) {
                p1Pool.draw(canvas); //to draw pool positions
            }
            synchronized (p2Pool) {
                p2Pool.draw(canvas);
            }
    }

    @Override
    public void onTouch(MotionEvent event) {
        //create a new token
        //TODO create a new token at specific column
        float x = event.getX();
        float y = event.getY();
        //TODO switch color based on player
//        Token marker = new Token();
//
//        //create a token on tap release
//        if(event.getAction(() == MotionEvent.ACTION_DOWN) {
//            if(x <= 130 - Token.RADIUS && x >= 130 - Token.RADIUS && y <= 1000 - Token.RADIUS && y >= 1000 + Token.RADIUS)
//            {
//
//            }
//            if(x <= 1650 - Token.RADIUS && x >= 1650 - Token.RADIUS && y <= 1000 - Token.RADIUS && y >= 1000 + Token.RADIUS)
//            {
//
//            }
//        }

        if(event.getAction() == MotionEvent.ACTION_UP){
            int col = getColumn(x);

            if(col == -1) {
            return; // selected spot is off board
            }
            //TODO implement dragging from a pool
            touched = true;
            Paint pPaint = new Paint();
            if(gameState.getCurrentPlayerID() == gameState.PLAYER1_ID){
                pPaint.setColor(RED);
            }
            else{
                pPaint.setColor(YELLOW);
            }
            Token newToken = new Token(pPaint, gameState.onPlayerMove(col-1), col);
            tokens.add(newToken);
            //check if invalid move
            if(newToken.getRow() == -1){
                tokens.remove(newToken);
            }
            gameState.nextPlayer();
        }
    }

    private int getColumn(float x){
        if(x > 260 && x < 260+ SLOT_LENGTH){
            return 1;
        }
        else if(x > 260 + SLOT_LENGTH/2 && x < 260 + SLOT_LENGTH *2){
            return 2;
        }
        else if(x > 260 + SLOT_LENGTH*2 && x < 260 + SLOT_LENGTH*3){
            return 3;
        }
        else if(x > 260 + SLOT_LENGTH*3 && x < 260 + SLOT_LENGTH*4){
            return 4;
        }
        else if(x > 260 + SLOT_LENGTH*4 && x < 260 + SLOT_LENGTH*5){
            return 5;
        }
        else if(x > 260 + SLOT_LENGTH*5 && x < 260 + SLOT_LENGTH*6){
            return 6;
        }
        else if(x > 260 + SLOT_LENGTH*6 && x < 260 + SLOT_LENGTH*7){
            return 7;
        }
        else{return -1;}
    }


}
