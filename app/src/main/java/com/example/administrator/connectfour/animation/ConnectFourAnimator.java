package com.example.administrator.connectfour.animation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

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
    //constants and instances to control token pool and dragged tokens
    public final static int TOKEN_POOL_X1 = 130;
    public final static int TOKEN_POOL_X2 = 1650;
    public final static int TOKEN_POOL_Y = 1000;
    public boolean dragStatus1 = false;
    public boolean dragStatus2 = false;

    Board board = new Board(); //board to be drawn
    TokenPool p1Pool = new TokenPool(Color.RED, 130, 1000);
    TokenPool p2Pool = new TokenPool(Color.YELLOW, 1650, 1000);
    TokenPool tokenPoolPlayer1; //tokens drawn out of player 1 pool
    TokenPool tokenPoolPlayer2; //tokens drawn out of player 2 pool
    int gravity = 2; //the pieces should fall realistically
    ConnectFourGameState gameState = MainActivity.gameState; //the current state of the game
    boolean touched = false; //don't start the game until it's started
    boolean won = false; //used when the player wins to blink a token
    private LinearLayout layout;



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
                if (tokens.isEmpty()) {
                    return;
            }
            //draw every token created on the board
            synchronized (tokens) { //prevent game from crashing
                for (Token token : tokens) {
                    //draw the token with realistic gravity
                        token.draw(canvas, token.getxPos(), token.getyPos());
                        token.setyPos(token.getyPos() + token.getVelocity());
                        token.setVelocity(token.getVelocity() + gravity);
                    //stop the token at the bottom of the board
                    //stop at highest empty position
                        if (token.getyPos() > SLOT_LENGTH * (6 - token.getRow()) + SLOT_LENGTH / 2) {
                            token.setVelocity(0);
                            token.setyPos(SLOT_LENGTH * (6 - token.getRow()) + SLOT_LENGTH / 2 + 13);
                            //now check if someone has won
                            boolean hasWon = gameState.hasWon(
                                    token.getRow()-1,token.getCol()-1,gameState.getCurrentPlayerID());
                            if(hasWon){
                                won = true;
                                //make the token blink
                            }
                    }
                }
            }
                board.draw(canvas);
        if(!won) {
            p1Pool.draw(canvas); //to draw pool positions
            p2Pool.draw(canvas);
        }
    }

    @Override
    public void onTouch(MotionEvent event) {

        //create a new token
        float x = event.getX();
        float y = event.getY();
        TokenMovable marker;
        //create a token on tap at the location of the token pools
       if(event.getAction() == MotionEvent.ACTION_DOWN) {
            Paint pPaint = new Paint();

            if(x <= 130 - Token.RADIUS && x >= TOKEN_POOL_X1 - Token.RADIUS && y <= TOKEN_POOL_Y - Token.RADIUS && y >= TOKEN_POOL_Y + Token.RADIUS)
            {
                dragStatus1 = true;
                pPaint.setColor(RED);
                marker = new TokenMovable(pPaint, TOKEN_POOL_X1, TOKEN_POOL_Y);
            }
            if(x <= 1650 - Token.RADIUS && x >= TOKEN_POOL_X2 - Token.RADIUS && y <= TOKEN_POOL_Y - Token.RADIUS && y >= TOKEN_POOL_Y + Token.RADIUS)
            {
                dragStatus2 = true;
                pPaint.setColor(YELLOW);
                marker = new TokenMovable(pPaint, TOKEN_POOL_X2, TOKEN_POOL_Y);
            }
        }



        if (event.getAction() == MotionEvent.ACTION_UP) {
            int col = getColumn(x);

            if (col == -1) {
                return;
            } // selected spot is off board
            // }
            //TODO implement dragging from a pool
            touched = true;
            Paint pPaint = new Paint();
            if (gameState.getCurrentPlayerID() == gameState.PLAYER1_ID) {
                pPaint.setColor(RED);
            } else {
                pPaint.setColor(YELLOW);
            }
            Token newToken = new Token(pPaint, gameState.onPlayerMove(col - 1), col);
            synchronized (tokens) { //synchronize tokens in case thread uses the new token
                tokens.add(newToken);
                //check if invalid move
                if (newToken.getRow() == -1) {
                    tokens.remove(newToken);
                }
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
