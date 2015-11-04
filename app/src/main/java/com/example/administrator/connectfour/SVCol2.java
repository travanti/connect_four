package com.example.administrator.connectfour;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Created by garciah16 on 9/20/2015.
 */
public class SVCol2 extends SVGameBoard {
    public SVCol2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint red = new Paint();
        red.setColor(Color.RED);
        Paint yellow = new Paint();
        yellow.setColor(Color.YELLOW);
        canvas.drawCircle(OFFSET, OFFSET * 2 * 5, RADIUS, red);
        canvas.drawCircle(OFFSET, OFFSET * 2 * 6, RADIUS, yellow);
        canvas.drawCircle(OFFSET, OFFSET * 2 * 4, RADIUS, yellow);
    }
}