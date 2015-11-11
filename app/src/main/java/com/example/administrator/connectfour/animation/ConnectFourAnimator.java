package com.example.administrator.connectfour.animation;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * This
 * Created by garciah16 on 11/10/2015.
 */
public class ConnectFourAnimator implements Animator {
    @Override
    public int interval() {
        return 0;
    }

    @Override
    public int backgroundColor() {
        return 0;
    }

    @Override
    public boolean doPause() {
        return false;
    }

    @Override
    public boolean doQuit() {
        return false;
    }

    @Override
    public void tick(Canvas canvas) {

    }

    @Override
    public void onTouch(MotionEvent event) {

    }
}
