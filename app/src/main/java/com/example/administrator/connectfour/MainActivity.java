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
    ImageButton restartBtn;
    public static ConnectFourGameState gameState;
    AnimationCanvas newCanvas;
    MainActivity myAct = this;
    Bundle extras;
    ConnectFourAnimator[] connectFourAnim = new ConnectFourAnimator[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get information from options
        extras = getIntent().getExtras();
        //create game state to use in activity
        gameState = new ConnectFourGameState();
        //add other views to activity
        titleText = (TextView) findViewById(R.id.mainText);
        titleText.setText("Connect Four");
        //enable options button
        optionsBtn = (ImageButton) findViewById(R.id.optionsButton);
        optionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent options = new Intent(getApplicationContext(), OptionsMenu.class);
                startActivity(options);
            }
        });
        //add animation canvas to activity
        connectFourAnim[0] = new ConnectFourAnimator();
        final AnimationCanvas myCanvas = new AnimationCanvas(this, connectFourAnim[0]);
        final LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        mainLayout.addView(myCanvas);
        //text thread to display current happenings in game
        startTextThread();
        //enable restart button
        restartBtn = (ImageButton) findViewById(R.id.resetButton);
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reset the game and the animation view
                gameState = gameState.resetGame();
                connectFourAnim[0] = new ConnectFourAnimator();
                mainLayout.removeView(newCanvas);
                newCanvas = new AnimationCanvas(myAct, connectFourAnim[0]);
                //remove extrenuous canvases
                mainLayout.removeView(myCanvas);
                //add the new canvas
                mainLayout.addView(newCanvas);
                updateGameOptions();
            }
        });
        //change qualities of game based on options
        updateGameOptions();
    }

    private void updateGameOptions(){
        if(extras != null){
            //set player number
            gameState.setEasyAIgame(extras.getBoolean("playerEasyAI"));
            //only change color if user chose that option.
            if(extras.getInt("player1Color") != extras.getInt("player2Color")) {
                connectFourAnim[0].setPlayer1Color(extras.getInt("player1Color"));
                connectFourAnim[0].setPlayer2Color(extras.getInt("player2Color"));
                connectFourAnim[0].setEasyAiplayerColor(extras.getInt("player2Color"));
            }
        }

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
                            if(!gameState.getGameIsWon() && gameState.getCurrentPlayerID() != gameState.PLAYEREASYAI_ID){ //display current player
                                titleText.setText("Player "+ (gameState.getCurrentPlayerID()+1) +"'s turn");
                            }
                            else if(!gameState.getGameIsWon() && gameState.getCurrentPlayerID() != gameState.PLAYER1_ID && gameState.getCurrentPlayerID() != gameState.PLAYER2_ID){ //display current player
                                titleText.setText("Easy AI's turn (tap board to move)");
                            }
                            else if(gameState.getCurrentPlayerID() == ConnectFourGameState.PLAYER1_ID && !gameState.getEasyAIgame()){//game is won
                                titleText.setText("PLAYER 2 HAS WON!");
                            }
                            else if(gameState.getGameIsWon() && gameState.getCurrentPlayerID() != gameState.PLAYER1_ID){
                                titleText.setText("PLAYER 1 HAS WON!");
                            }
                            else if(gameState.getCurrentPlayerID() == ConnectFourGameState.PLAYER1_ID && gameState.getEasyAIgame()){
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
    protected void onResume() {
        super.onResume();
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
