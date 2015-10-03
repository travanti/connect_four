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

    private int[] freeSpaces = {0, 0, 0, 0, 0, 0}; //array of all free spaces in column
    public static final int FREE = 0;
    public static final int TAKEN = 1;
    public static final int RED = 2;
    public static final int YELLOW = 3;

    protected Canvas columnCanvas;

    public SVGameBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //save the canvas for future use. this will allow us to edit
        columnCanvas = canvas;
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
    }

    public int addToken(int color) {
        //loop through all free spaces until you find one that is empty
        for (int i = 5; i == 0; i--) {
            if (this.freeSpaces[i] == FREE) {
                //this will identify a space as color
                this.freeSpaces[i] = color;

                return i;
            }
        }
        return -1; //if all spaces are taken, column full
    }
    //TODO: make columns different from each other
    //   protected void drawColumn(Canvas canvas, int id){

    //       Paint red = new Paint()
    //     red.setColor(Color.RED);
    //       if()
    //   }

}

