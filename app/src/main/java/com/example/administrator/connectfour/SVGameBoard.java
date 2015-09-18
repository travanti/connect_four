package com.example.administrator.connectfour;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceView;



/**
 * Created by mueller16 on 9/14/2015.
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
        blue.setColor(Color.BLUE);
        Rect gameBoard = new Rect();
        gameBoard.set(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawRect(gameBoard, blue);

        Paint white = new Paint();
        white.setColor(Color.WHITE);
        for (int i = 1; i < 7; i++) {
            canvas.drawCircle(OFFSET, OFFSET * 2 * i, RADIUS, white);
        }
    }
    //TODO: make columns different from each other
 //   protected void drawColumn(Canvas canvas, int id){

 //       Paint red = new Paint()
 //     red.setColor(Color.RED);
 //       if()
 //   }

}

