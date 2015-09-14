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


    public SVGameBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }


    @Override
   protected void onDraw(Canvas canvas){
       super.onDraw(canvas);

       Paint blue = new Paint();
       blue.setColor(Color.BLUE);
       Rect gameBoard = new Rect();
       gameBoard.set(0, 0, canvas.getWidth(), canvas.getHeight());
       canvas.drawRect(gameBoard, blue);

    }
}
