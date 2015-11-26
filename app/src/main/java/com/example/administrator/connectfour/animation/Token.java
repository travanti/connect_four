package com.example.administrator.connectfour.animation;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by garciah16 on 11/10/2015.
 */
public class Token {

    public static final float RADIUS = Board.SLOT_LENGTH*11/24;
    //instance variables
    int radius;
    Paint color;
    int xPos;
    int yPos;
    int row; //bottom left slot is [1,1]
    int col;
    int velocity;

    public Token(Paint color, int row, int col){
        this.color = color;
        this.xPos = 170+col*Board.SLOT_LENGTH;
        this.yPos = 0;
        this.row = row;
        this.col = col;
        this.radius = (int) RADIUS;
        this.velocity = 2;
    }

    public void draw(Canvas canvas, float x, float y){


        canvas.drawCircle(x,y,this.radius,color);

    }

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
}
