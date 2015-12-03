package com.example.administrator.connectfour.animation;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by travanti16 on 12/2/2015.
 */
public class TokenMovable {
    int radius;
    Paint color;
    float xPos;
    float yPos;
    public TokenMovable(Paint color, int xPos, int yPos){
        this.color = color;
        this.xPos = xPos;
        this.yPos = yPos;
        radius = (int) Token.RADIUS;
    }

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
        this.yPos += yPos;
    }

    public void setxPos(float xPos){
        this.xPos += xPos;
    }
}
