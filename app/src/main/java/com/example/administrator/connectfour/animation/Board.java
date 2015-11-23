package com.example.administrator.connectfour.animation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * defines and draws a board which the pieces will fall into
 * on the animation canvas
 * Created by garciah16 on 11/10/2015.
 */
public class Board {

    public static final int SLOT_LENGTH = 180;
    //instance variables
    int xPosition;
    int yPosition;

    public Board(){
        xPosition = 260;
        yPosition = 20;
    }

    public void draw(Canvas canvas){
        for(int i = 0; i < 6; i++ ){ //index 0 is the bottom row
            for(int j = 0; j < 7; j++){ //index 0 is the leftmost column
                drawSlot(canvas, xPosition+SLOT_LENGTH*j, yPosition+SLOT_LENGTH*i);
            }
        }
    }

    private void drawSlot(Canvas canvas, float x, float y){
        //draw a slot in the board
        int length = SLOT_LENGTH;
        Path slotPath = new Path();
        slotPath.moveTo(x, y);
        slotPath.lineTo(x + length, y);
        slotPath.lineTo(x+ length,y+length);
        slotPath.lineTo(x,y+length);
        slotPath.lineTo(x, y);
        slotPath.close();
        //draw an circle inside the square
        slotPath.moveTo(x + length / 2, y);
        RectF oval = new RectF
                (x+length-length/12,y+length/12,x+length/12,y+length-length/12);
        slotPath.addOval(oval, Path.Direction.CW);
        slotPath.close();
        //paint the slot
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        slotPath.setFillType(Path.FillType.EVEN_ODD);

        canvas.drawPath(slotPath, p);
    }
}
