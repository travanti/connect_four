package com.example.administrator.connectfour;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceView;
import static android.graphics.Color.*;


/**
 * Created by mueller16 on 9/14/2015.
 * Modified by travanti16 & Garcia16 on 9/18/2015
 */
public class SVGameBoard extends SurfaceView {

    public static final int RADIUS = 80;
    public static final int OFFSET = 85;

    public SVGameBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }


    @Override
   protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint blue = new Paint();
        blue.setColor(BLUE);
        Rect gameBoard = new Rect();
        gameBoard.set(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawRect(gameBoard, blue);

        Paint white = new Paint();
        white.setColor(Color.WHITE);
        for (int i = 1; i < 7; i++) {
            canvas.drawCircle(OFFSET, OFFSET * 2 * i, RADIUS, white);
        }
        //the below is only to make an example of what the board will look like in play
        //this should be removed later as this is not how we will make the in play board function
        //(unless overlapping shapes turns out to work better than changing colors
//        Paint red = new Paint();
//        red.setColor(Color.RED);
//        Paint yellow = new Paint();
//        yellow.setColor(Color.YELLOW);
//        canvas.drawCircle(OFFSET, OFFSET * 2 * 6, RADIUS, red);
//        canvas.drawCircle(OFFSET, OFFSET * 2 * 5, RADIUS, yellow);

    }
    //TODO: make columns different from each other
 //   protected void drawColumn(Canvas canvas, int id){

 //       Paint red = new Paint()
 //     red.setColor(Color.RED);
 //       if()
 //   }

}

