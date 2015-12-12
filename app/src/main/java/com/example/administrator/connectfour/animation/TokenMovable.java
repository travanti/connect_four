package com.example.administrator.connectfour.animation;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * instance of a movable token tat
 * Created by travanti16 on 12/2/2015.
 */
public class TokenMovable {
    int radius;
    Paint color;
    float xPos;
    float yPos;

    /**
     * constructor
     * @param color color of the drawn token
     * @param xPos x coordinate of the canvas
     * @param yPos y coordinate of the canvas
     */
    public TokenMovable(Paint color, int xPos, int yPos){
        this.color = color;
        this.xPos = xPos;
        this.yPos = yPos;
        radius = (int) Token.RADIUS;
    }

    /**
     * draw the token on the canvas
     * @param canvas canvas on which to be drawn
     * @param color color of which to be drawn
     */
    public void draw(Canvas canvas, Paint color){
        canvas.drawCircle(this.xPos, this.yPos, this.radius, color);
    }

    public Paint getColor() {
        return color;
    }

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public void setxPos(float xPos){
        this.xPos = xPos;
    }

    public void setColor(Paint color)
    {
        this.color = color;
    }
}
