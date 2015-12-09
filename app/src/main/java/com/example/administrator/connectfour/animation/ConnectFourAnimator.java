package com.example.administrator.connectfour.animation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.administrator.connectfour.GameFramework.infoMsg.GameState;
import com.example.administrator.connectfour.MainActivity;
import com.example.administrator.connectfour.connectfour.ConnectFourEasyAI;
import com.example.administrator.connectfour.connectfour.ConnectFourGameState;

import java.util.ArrayList;

import static android.graphics.Color.*;

/**
 * animates a canvas where the game will take place,
 * including tokens, a board and a token pool
 * Additionally it makes use of a token movable object which serves as a marker for following
 * the player's finger during "token drags" from the token pool
 * Created by garciah16 on 11/10/2015.
 * Editted by travanti16 on 11/25/2015
 * Editted by muller16 on 11/25/2015
 * Eddited by travanti16 12/8/15
 */
public class ConnectFourAnimator implements Animator {
    //constants
    public static final int SLOT_LENGTH = 180;
    public static final int TOKEN_POOL_X1 = 130;
    public static final int TOKEN_POOL_X2 = 1650;
    public static final int TOKEN_POOL_Y = 1000;
    //instance variables
    ArrayList<Token> tokens = new ArrayList<>(42); //tokens that will be drawn

    //the easy AI player
    ConnectFourEasyAI CFEasyAI = new ConnectFourEasyAI();

    //indicate token and pool colors
    private int player1Color = Color.RED;
    private int player2Color = Color.YELLOW;
    private int easyAiplayerColor = Color.YELLOW;

    Board board = new Board(); //board to be drawn
    TokenPool p1Pool = new TokenPool(Color.RED, TOKEN_POOL_X1, TOKEN_POOL_Y); //p1Token Pool
    TokenPool p2Pool = new TokenPool(Color.YELLOW, TOKEN_POOL_X2, TOKEN_POOL_Y); //p2Token Pool
    private boolean movingStatus = false; //used to make the marker not be drawn until moved

    //Todo find a betttr way to initialize TokenMovable
    Paint blah = new Paint();
    TokenMovable marker= new TokenMovable(blah, TOKEN_POOL_X1, TOKEN_POOL_Y);

    int gravity = 3; //the pieces should fall realistically
    ConnectFourGameState gameState = MainActivity.gameState; //the current state of the game
    boolean touched = false; //don't start the game until it's started

    boolean won = false; //used when the player wins to blink a token
    Token winningToken; //make this token blink if its the winner
    Paint winningTokenColor;
    int counter = 0; //counting how long to blink token

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
        //if we are currently holding onto a marker (movable token) then draw the movable token following the finger

        //check if the board has been touched yet
        //and still draw the
        if (!touched) {
            board.draw(canvas);
            p1Pool.draw(canvas); //to draw pool positions
            p2Pool.draw(canvas);
            if(movingStatus){
                marker.draw(canvas, marker.getColor());
            }
        }


        //we don't want an empty array list
        Paint tempColor = new Paint();
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

                    if(won && token == winningToken){
                        //make the token blink
                        int color = winningTokenColor.getColor();
                        if(counter <= 5){
                            token.setColor(winningTokenColor);
                            counter++;
                        }
                        else if(counter > 5 && counter <= 10){
                            counter++;
                            Paint p = new Paint();
                            p.setColor(Color.WHITE);
                            token.setColor(p);
                        }
                        else{
                            counter = 0;
                        }
                    }
                }
            }
        }
        //draw the token pools after we've pressed the screen as well.
        p1Pool.draw(canvas);
        p2Pool.draw(canvas);
        //draw the board last in order to make the pieces fall "behind"
        board.draw(canvas);

        if(movingStatus)
        {
            marker.draw(canvas, marker.color);
        }

    }

    @Override
    public void onTouch(MotionEvent event) {

        if (won) {return;} //don't do anything

        //create a new token
        float x = event.getX();
        float y = event.getY();
        Paint poolColor = new Paint();
        poolColor.setColor(RED);

        //check if token pool location was pressed to see if drag should begin and if so initalize proper colors
        //also note use of else if chain, must be used so each case for event is ensured to be examined
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (x <= TOKEN_POOL_X1 + Token.RADIUS && x >= TOKEN_POOL_X1 - Token.RADIUS && y >= TOKEN_POOL_Y -
                    Token.RADIUS && y <= TOKEN_POOL_Y + Token.RADIUS && gameState.getCurrentPlayerID() == gameState.PLAYER1_ID) {
                poolColor.setColor(RED);
                movingStatus = true;
                marker.setColor(poolColor);
                marker.setxPos(TOKEN_POOL_X1);
                marker.setyPos(TOKEN_POOL_Y);
            }
            if (x <= TOKEN_POOL_X2 + Token.RADIUS && x >= TOKEN_POOL_X2 - Token.RADIUS && y >= TOKEN_POOL_Y -
                    Token.RADIUS && y <= TOKEN_POOL_Y + Token.RADIUS && gameState.getCurrentPlayerID() == gameState.PLAYER2_ID) {
                poolColor.setColor(YELLOW);
                movingStatus = true;
                marker.setColor(poolColor);
                marker.setxPos(TOKEN_POOL_X2);
                marker.setyPos(TOKEN_POOL_Y);
            }
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //adjust the marker coordinates if it is moving
            {
                synchronized (marker) {
                    marker.setxPos(event.getX());
                    marker.setyPos(event.getY());
                }
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) { //when user releases finger
            int col = getColumn(x);
            //check if column is valid
            if (col == -1) {
                movingStatus = false; //not a valid placement, therefore token disappears
                return;
            } else if (!tokens.isEmpty()) { //make sure something was dropped
                    touched = true; //begin drawing in "token placed" mode
                }

            //TODO implement dragging from a pool

            touched = true;
            Paint pPaint = new Paint();
            if (gameState.getCurrentPlayerID() == gameState.PLAYER1_ID) {
                pPaint.setColor(player1Color);
            } else if(gameState.getCurrentPlayerID() == gameState.PLAYER2_ID){
                pPaint.setColor(player2Color);
            }
            else{
                pPaint.setColor(easyAiplayerColor);
            }
            Token newToken;
            if (movingStatus) { //ensure token was placed properly before executing any of the following
                Paint pPaint = new Paint();
                if (gameState.getCurrentPlayerID() == gameState.PLAYER1_ID) {
                    pPaint.setColor(RED);
                } else {
                    pPaint.setColor(YELLOW);
                }
                newToken = new Token(pPaint, gameState.onPlayerMove(col - 1), col);

                //TODO: modify this to play with an AI
                if(gameState.getCurrentPlayerID() == gameState.PLAYER1_ID || gameState.getCurrentPlayerID() == gameState.PLAYER2_ID) {
                    newToken = new Token(pPaint, gameState.onPlayerMove(col - 1), col);
                }
                else if(gameState.getCurrentPlayerID() == gameState.PLAYEREASYAI_ID){
                    int column = CFEasyAI.easyAImove();
                    newToken = new Token(pPaint, gameState.onPlayerMove(column-1),column);
                }
                else{
                    return;//new token was never initialized properly
                }
            synchronized (tokens) { //synchronize tokens in case thread uses the new token
                tokens.add(newToken);
                //check if invalid move above board
                if (newToken.getRow() == -1) {
                    tokens.remove(newToken);
                    movingStatus = false; //not a valid placement therefore it disappears
                    return; //end the touch
                }
                //check if the token is the winning token
                boolean hasWon = false; //default
                movingStatus = false; //reset draggable so it is not drawn anymore
                hasWon = gameState.hasWon(newToken.getRow() - 1, newToken.getCol() - 1, gameState.getCurrentPlayerID());
                if (hasWon) {
                    won = true;
                    winningToken = newToken;
                    winningTokenColor = newToken.getColor();
                }
                gameState.nextPlayer();
            }
        }

    }


    private int getColumn(float x) {
        if (x > 260 && x < 260 + SLOT_LENGTH) {
            return 1;
        } else if (x > 260 + SLOT_LENGTH / 2 && x < 260 + SLOT_LENGTH * 2) {
            return 2;
        } else if (x > 260 + SLOT_LENGTH * 2 && x < 260 + SLOT_LENGTH * 3) {
            return 3;
        } else if (x > 260 + SLOT_LENGTH * 3 && x < 260 + SLOT_LENGTH * 4) {
            return 4;
        } else if (x > 260 + SLOT_LENGTH * 4 && x < 260 + SLOT_LENGTH * 5) {
            return 5;
        } else if (x > 260 + SLOT_LENGTH * 5 && x < 260 + SLOT_LENGTH * 6) {
            return 6;
        } else if (x > 260 + SLOT_LENGTH * 6 && x < 260 + SLOT_LENGTH * 7) {
            return 7;
        } else {
            return -1;
        }
    }

    public void setWon(boolean b){
        won = b;
    }

    public void setPlayer1Color(int player1Color) {
        this.player1Color = player1Color;
    }

    public void setPlayer2Color(int player2Color) {
        this.player2Color = player2Color;
    }

    public void setEasyAiplayerColor(int easyAiplayerColor) {
        this.easyAiplayerColor = easyAiplayerColor;
    }
}

