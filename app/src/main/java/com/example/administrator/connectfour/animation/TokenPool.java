package com.example.administrator.connectfour.animation;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * this class draws the "pool" of tokens from which a player drags a token to the board
 * Created by garciah16 on 11/10/2015.
 * Editted by travanti16 on 11/25/2015
 * Editted by muller16 on 11/25/2015
 */
public class TokenPool {

    //instance variables
    float radius;
    //int height;
    //int width;
    int color;
    int xPos;
    int yPos;

    /**
     * constructor
     *
     * @param color color dependent on the player
     * @param x     x coordinate to be drawn
     * @param y     y coordinate to be drawn
     */
    public TokenPool(int color, int x, int y) {
        this.xPos = x;
        this.yPos = y;
        this.radius = Token.RADIUS;
        this.color = color;

    }

    /**
     * draw the token pool at its position
     *
     * @param canvas canvas on which to be drawn
     */
    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(this.color);
        canvas.drawCircle(this.xPos, this.yPos, this.radius, p);

    }

    public void setColor(int color) {
        this.color = color;
    }
}
