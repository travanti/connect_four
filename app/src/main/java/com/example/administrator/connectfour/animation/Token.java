package com.example.administrator.connectfour.animation;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * a class that represents the aspects of a token
 * being animated on the board on the Connect Four Animator
 * Created by garciah16 on 11/10/2015.
 */
public class Token {

    public static final float RADIUS = Board.SLOT_LENGTH * 11 / 24;
    //instance variables
    int radius;
    Paint color;
    int xPos;
    int yPos;
    int row; //bottom left slot is [1,1]
    int col;
    int velocity;

    /**
     * constructor
     *
     * @param color color of token dependent on player
     * @param row   row where the token is added
     * @param col   column where the token is added
     */
    public Token(Paint color, int row, int col) {
        this.color = color;
        this.xPos = 170 + col * Board.SLOT_LENGTH;
        this.yPos = 0;
        this.row = row;
        this.col = col;
        this.radius = (int) RADIUS;
        this.velocity = 2;
    }

    /**
     * draw the token on the animation canvas
     *
     * @param canvas canvas to be drawn on
     * @param x      x on the canvas where to be drawn
     * @param y      y on the canvas where to be drawn
     */
    public void draw(Canvas canvas, float x, float y) {

        canvas.drawCircle(x, y, this.radius, color);

    }

    //getter and setter methods
    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getVelocity() {
        return velocity;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        this.color = color;
    }
}
