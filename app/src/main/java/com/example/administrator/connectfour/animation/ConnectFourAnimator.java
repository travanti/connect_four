package com.example.administrator.connectfour.animation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.administrator.connectfour.MainActivity;
import com.example.administrator.connectfour.connectfour.ConnectFourGameState;

import java.util.ArrayList;

import static android.graphics.Color.*;

/**
 * animates a canvas where the game will take place,
 * including tokens, a board and a token pool
 * Created by garciah16 on 11/10/2015.
 * Editted by travanti16 on 11/25/2015
 * Editted by muller16 on 11/25/2015
 */
public class ConnectFourAnimator implements Animator {
    //constants
    public static final int SLOT_LENGTH = 180;
    //instance variables
    ArrayList<Token> tokens = new ArrayList<>(42); //tokens that will be drawn

    private int player1Color = Color.RED;
    private int player2Color = Color.YELLOW;
    private int easyAiplayerColor = Color.YELLOW;

    Board board = new Board(); //board to be drawn
    TokenPool p1Pool = new TokenPool(player1Color, 130, 1000);
    TokenPool p2Pool = new TokenPool(player2Color, 1650, 1000);
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
        //check if the board has been touched yet
        if (touched == false) {
            board.draw(canvas);
            p1Pool.draw(canvas); //to draw pool positions
            p2Pool.draw(canvas);
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
        p1Pool.draw(canvas);
        p2Pool.draw(canvas);
        //draw the board last in order to make the pieces fall "behind"
        board.draw(canvas);

    }

    @Override
    public void onTouch(MotionEvent event) {

        if (won) {return;} //don't do anything

        //create a new token
        float x = event.getX();

        if (event.getAction() == MotionEvent.ACTION_UP) { //when user releases finger
            int col = getColumn(x);
            //check if column is valid
            if (col == -1) {return;}

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
            //TODO: modify this to play with an AI
            Token newToken = new Token(pPaint, gameState.onPlayerMove(col - 1), col);

            synchronized (tokens) { //synchronize tokens in case thread uses the new token
                tokens.add(newToken);
                //check if invalid move above board
                if (newToken.getRow() == -1) {
                    tokens.remove(newToken);
                    return; //end the touch
                }
            }
            //check if the token is the winning token
            boolean hasWon = false; //default
            hasWon = gameState.hasWon(
                    newToken.getRow() - 1, newToken.getCol() - 1, gameState.getCurrentPlayerID());
            if (hasWon) {
                won = true;
                winningToken = newToken;
                winningTokenColor = newToken.getColor();
            }
            gameState.nextPlayer();
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

    public void addTokenAI(Token t) {
        tokens.add(t);
    }

    public static void setPlayer1Color(int player1Color) {
        player1Color = player1Color;
    }

    public static void setPlayer2Color(int player2Color) {
        player2Color = player2Color;
    }
}
