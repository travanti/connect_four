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
import com.example.administrator.connectfour.connectfour.ConnectFourHardAI;

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
 * Edited by Mueller16 12/9/15
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
    ConnectFourHardAI CFHardAI = new ConnectFourHardAI(MainActivity.gameState);

    //indicate token and pool colors
    private int player1Color = Color.RED;
    private int player2Color = Color.YELLOW;
    private int easyAiplayerColor = Color.YELLOW;
    private int hardAiplayerColor = Color.YELLOW;

    Board board = new Board(); //board to be drawn
    TokenPool p1Pool = new TokenPool(player1Color, TOKEN_POOL_X1, TOKEN_POOL_Y); //p1Token Pool
    TokenPool p2Pool = new TokenPool(player2Color, TOKEN_POOL_X2, TOKEN_POOL_Y); //p2Token Pool
    private boolean movingStatus = false; //used to make the marker not be drawn until moved

    //the movable token that is drawn under finger
    Paint blah = new Paint();
    TokenMovable marker = new TokenMovable(blah, TOKEN_POOL_X1, TOKEN_POOL_Y);

    int gravity = 3; //the pieces should fall realistically
    ConnectFourGameState gameState = MainActivity.gameState; //the current state of the game
    boolean touched = false; //don't start the game until it's started

    boolean won = false; //used when the player wins to blink a token
    Token winningToken; //make this token blink if its the winner
    Paint winningTokenColor;
    int counter = 0; //counting how long to blink token

    @Override
    //interval of ticks per second to animate the canvas
    public int interval() {
        return 30;
    }

    @Override
    //white background
    public int backgroundColor() {
        return WHITE;
    }

    @Override
    //unused
    public boolean doPause() {
        return false;
    }

    @Override
    //unused
    public boolean doQuit() {
        return false;
    }

    @Override
    /**
     * draw the tokens, token pools, the board,
     * and make a token blink upon win
     */
    public void tick(Canvas canvas) {
        //make sure token pool colors match token colors
        p1Pool.setColor(player1Color);
        p2Pool.setColor(player2Color);

        //check if the board has been touched yet and if so draw pools and the board
        if (!touched) {
            board.draw(canvas);
            p1Pool.draw(canvas);
            p2Pool.draw(canvas);
            if (movingStatus) {
                marker.draw(canvas, marker.getColor());
            }
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
                    if (won && token == winningToken) {
                        //make the token blink
                        int color = winningTokenColor.getColor();
                        if (counter <= 5) {
                            token.setColor(winningTokenColor);
                            counter++;
                        } else if (counter > 5 && counter <= 10) {
                            counter++;
                            Paint p = new Paint();
                            p.setColor(Color.WHITE);
                            token.setColor(p);
                        } else {
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

        //draw a marker when the player drags a token
        if (movingStatus) {
            marker.draw(canvas, marker.color);
        }
    }

    @Override
    /**
     * the touch method that updates the game state,
     * animation, and everything else when the user
     * touches the screen in a certain manner
     */
    public void onTouch(MotionEvent event) {

        if (won) {
            return;
        } //don't do anything if game is won

        //create a new token
        float x = event.getX(); //get x coordinate from the screen
        float y = event.getY(); //get the y coordinate from the screen
        Paint poolColor = new Paint(); //make pool
        poolColor.setColor(player1Color);

        //check if token pool location was pressed to see if drag should begin and if so initalize proper colors
        //also note use of else if chain for all events being checked, must be used so each case for event is ensured to be examined
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (x <= TOKEN_POOL_X1 + Token.RADIUS && x >= TOKEN_POOL_X1 - Token.RADIUS && y >= TOKEN_POOL_Y -
                    Token.RADIUS && y <= TOKEN_POOL_Y + Token.RADIUS && gameState.getCurrentPlayerID() == gameState.PLAYER1_ID) {
                poolColor.setColor(player1Color);
                movingStatus = true; //make markerToken visible to player
                marker.setColor(poolColor); //set color for what poolColor1 is
                marker.setxPos(TOKEN_POOL_X1); //assign from constant X1
                marker.setyPos(TOKEN_POOL_Y);
            }
            if (x <= TOKEN_POOL_X2 + Token.RADIUS && x >= TOKEN_POOL_X2 - Token.RADIUS && y >= TOKEN_POOL_Y -
                    Token.RADIUS && y <= TOKEN_POOL_Y + Token.RADIUS && gameState.getCurrentPlayerID() == gameState.PLAYER2_ID) {
                poolColor.setColor(player2Color);
                movingStatus = true; //make markerToken visible to player
                marker.setColor(poolColor); //set color for what poolColor2 is
                marker.setxPos(TOKEN_POOL_X2); //assign from constant X2
                marker.setyPos(TOKEN_POOL_Y);
            }
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) { //when a person moves their finger on the screen
            //adjust the marker coordinates if it is moving
            {
                synchronized (marker) {
                    marker.setxPos(event.getX()); //get the X position and update it
                    marker.setyPos(event.getY()); //get the Y position and update it
                }
            }

        } else if (event.getAction() == MotionEvent.ACTION_UP) { //when user releases finger
            int col = getColumn(x); //call to helper method to get the column number on board to make placement
            //check if column is valid
            if (col == -1) {
                movingStatus = false; //not a valid placement, therefore token disappears
                return;
            } else if (!tokens.isEmpty()) { //make sure something was dropped
                touched = true; //begin drawing in "token placed" mode
            }

            //set the color of the token to be drawn
            Paint pPaint = new Paint();
            if (gameState.getCurrentPlayerID() == gameState.PLAYER1_ID) {
                pPaint.setColor(player1Color);
            } else if (gameState.getCurrentPlayerID() == gameState.PLAYER2_ID) {
                pPaint.setColor(player2Color);
            } else if (gameState.getCurrentPlayerID() == gameState.PLAYEREASYAI_ID) {
                pPaint.setColor(easyAiplayerColor);
            } else if (gameState.getCurrentPlayerID() == gameState.PLAYERHARDAI_ID) {
                pPaint.setColor(easyAiplayerColor);
            }
            Token newToken; //this is the token being added to the board
            //add hard AI boolean to this if when implemented
            if (movingStatus || ((gameState.getEasyAIgame() || gameState.getHardAIgame())
                    && gameState.getCurrentPlayerID() != gameState.PLAYER1_ID)) {
                //ensure token was placed properly before executing any of the following
                if (gameState.getCurrentPlayerID() == gameState.PLAYER1_ID) {
                    pPaint.setColor(player1Color);
                } else {
                    pPaint.setColor(player2Color);
                }
                //depending on the player call onMove on the column for that player to make a move
                if (gameState.getCurrentPlayerID() == gameState.PLAYER1_ID || gameState.getCurrentPlayerID() == gameState.PLAYER2_ID) {
                    newToken = new Token(pPaint, gameState.onPlayerMove(col - 1), col);
                } else if (gameState.getCurrentPlayerID() == gameState.PLAYEREASYAI_ID) {
                    int columnEasyAI = CFEasyAI.easyAImove(); //run the easy AI if it has been set
                    newToken = new Token(pPaint, gameState.onPlayerMove(columnEasyAI - 1), columnEasyAI);
                } else if (gameState.getCurrentPlayerID() == gameState.PLAYERHARDAI_ID) {
                    int columnHardAI = CFHardAI.alternateHardAImove(gameState.getGameBoard()); //run the hard AI if it has been set
                    newToken = new Token(pPaint, gameState.onPlayerMove(columnHardAI - 1), columnHardAI);
                } else {
                    return;//new token was never initialized properly
                }
                synchronized (tokens) { //synchronize tokens in case thread uses the new token
                    tokens.add(newToken);
                    //check if invalid move above board
                    if (newToken.getRow() == -1) {
                        tokens.remove(newToken);
                        return; //end the touch
                    }
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
                        //the game is over so make the token flash
                        won = true;
                        winningToken = newToken;
                        winningTokenColor = newToken.getColor();
                    }
                    //if it goes this far, the token has been played. switch to next player
                    gameState.nextPlayer();  //swap players
                }
            }

        }
    }

    /**
     * method returns the column where the player has touched on the screen.
     * calculated by the size of the board slots
     *
     * @param x x location on the screen where player has touched
     * @return idx of column where player has touched, -1 if outside of board
     */
    private int getColumn(float x) {
        //do maths to figure out where in the canvas each column
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

    public void setWon(boolean b) {
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

