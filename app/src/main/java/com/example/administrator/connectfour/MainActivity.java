package com.example.administrator.connectfour;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.connectfour.animation.AnimationCanvas;
import com.example.administrator.connectfour.animation.Animator;
import com.example.administrator.connectfour.animation.ConnectFourAnimator;
import com.example.administrator.connectfour.connectfour.ConnectFourEasyAI;
import com.example.administrator.connectfour.connectfour.ConnectFourGameState;


public class MainActivity extends Activity {

    TextView titleText;
    ImageButton optionsBtn;
    public static ConnectFourGameState gameState;
    public static ConnectFourAnimator cfAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create game state to use in activity
        gameState = new ConnectFourGameState();
        //add other views to activity
        titleText = (TextView) findViewById(R.id.mainText);
        titleText.setText("Connect Four");
        optionsBtn = (ImageButton) findViewById(R.id.optionsButton);
        optionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent options = new Intent(getApplicationContext(), OptionsMenu.class);
                startActivity(options);
            }
        });
        //add animation canvas to activity
        Animator connectFourAnim = new ConnectFourAnimator();
        cfAnim = (ConnectFourAnimator) connectFourAnim;
        AnimationCanvas myCanvas = new AnimationCanvas(this, connectFourAnim);
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        mainLayout.addView(myCanvas);
        startTextThread();
    }

    /**
     * thread that changes the title text according to info about the Game State
     */
    private void startTextThread() {
        final Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(!gameState.getGameIsWon() && gameState.getCurrentPlayerID() !=ConnectFourEasyAI.PlayerEasyAIid){ //display current player
                                titleText.setText("Player "+ (gameState.getCurrentPlayerID()+1) +"'s turn");
                            }
                            else if(!gameState.getGameIsWon() && gameState.getCurrentPlayerID() != ConnectFourGameState.PLAYER1_ID && gameState.getCurrentPlayerID() != ConnectFourGameState.PLAYER2_ID){ //display current player
                                titleText.setText("Easy AI's turn");
                            }
                            else if(gameState.getCurrentPlayerID() == ConnectFourGameState.PLAYER1_ID){//game is won
                                titleText.setText("PLAYER 2 HAS WON!");
                            }
                            else if(gameState.getCurrentPlayerID() == ConnectFourGameState.PLAYER2_ID){
                                titleText.setText("PLAYER 1 HAS WON!");
                            }
                            else if(gameState.getCurrentPlayerID() == ConnectFourEasyAI.PlayerEasyAIid){
                                titleText.setText("EASY AI HAS WON");
                            }
                        }
                    });
                }
            }
        };
        new Thread(run).start();
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
