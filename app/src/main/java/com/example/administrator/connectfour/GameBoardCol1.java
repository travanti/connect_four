package com.example.administrator.connectfour;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.graphics.Color;



/**
 * Created by garciah16 on 9/18/2015.
 */
public class GameBoardCol1 extends SVGameBoard {
    public GameBoardCol1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDraw(Canvas canvas){
        Paint red = new Paint();
        red.setColor(Color.RED);
        Paint white = new Paint();
        white.setColor(Color.WHITE);
        for (int i = 1; i < 7; i++) {
            canvas.drawCircle(OFFSET, OFFSET * 2 * i, RADIUS, red);
        }
        //the below is only to make an example of what the board will look like in play
        //this should be removed later as this is not how we will make the in play board function
        //(unless overlapping shapes turns out to work better than changing colors

    }
}
