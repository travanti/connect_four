package com.example.administrator.connectfour;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.connectfour.animation.AnimationCanvas;
import com.example.administrator.connectfour.animation.Animator;
import com.example.administrator.connectfour.animation.ConnectFourAnimator;
import com.example.administrator.connectfour.connectfour.ConnectFourGameState;
import com.example.administrator.connectfour.connectfour.GameTitleThread;


public class MainActivity extends Activity {

    TextView titleText;
    ImageButton optionsBtn;
    public static ConnectFourGameState gameState;
    GameTitleThread gtThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create game state to use in activity
        gameState = new ConnectFourGameState();
        //add other views to activity
        titleText = (TextView) findViewById(R.id.mainText);
        titleText.setText("Player 1's turn");
        optionsBtn = (ImageButton) findViewById(R.id.optionsButton);
        //add animation canvas to activity
        Animator connectFourAnim = new ConnectFourAnimator();
        AnimationCanvas myCanvas = new AnimationCanvas(this, connectFourAnim);
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        mainLayout.addView(myCanvas);

        //gtThread = new GameTitleThread(gameState, titleText);
        //gtThread.run();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);


    }
}
