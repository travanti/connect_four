package com.example.administrator.connectfour;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.administrator.connectfour.animation.ConnectFourAnimator;
import com.example.administrator.connectfour.connectfour.ConnectFourGameState;

/**
 * an options menu activity that changes certain aspects
 * of the game such as the types of players, color of tokens.
 *
 * @author garciah16
 * @version 12/12/15
 */
public class OptionsMenu extends ActionBarActivity {

    //buttons in the activity
    Button p1Btn;
    Button p2Btn;
    Button easyAIBtn;
    Button hardAIBtn;
    Button yrBtn;
    Button bgBtn;
    Button saveGoBtn;
    Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_menu);
        final Intent i = new Intent(getApplicationContext(), MainActivity.class);
        //retrieve intent from main activity
        //declare buttons
        p1Btn = (Button) findViewById(R.id.p1Button);
        p2Btn = (Button) findViewById(R.id.p2Button);
        easyAIBtn = (Button) findViewById(R.id.easyButton);
        hardAIBtn = (Button) findViewById(R.id.hardButton);
        yrBtn = (Button) findViewById(R.id.yrButton);
        bgBtn = (Button) findViewById(R.id.bgButton);
        saveGoBtn = (Button) findViewById(R.id.saveButton);
        cancelBtn = (Button) findViewById(R.id.cancelButton);

        //onClick Listeners ---------------------------------------------------------------

        //change number of players. changing to 1 player defaults to easy AI
        p1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set easy Ai game to true
                i.putExtra("playerEasyAI", true);
                i.putExtra("playerHardAI", false);
                //set button text colors to indicate options
                p1Btn.setTextColor(Color.RED);
                easyAIBtn.setTextColor(Color.RED);
                p2Btn.setTextColor(Color.BLACK);
            }
        });
        p2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("playerEasyAI", false);
                i.putExtra("playerHardAI", false);
                //set button text colors to indicate options
                p1Btn.setTextColor(Color.BLACK);
                p2Btn.setTextColor(Color.RED);
                easyAIBtn.setTextColor(Color.BLACK);
                hardAIBtn.setTextColor(Color.BLACK);
            }
        });
        easyAIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("playerEasyAI", true);
                i.putExtra("playerHardAI", false);
                //set button text colors to reflect choices
                p1Btn.setTextColor(Color.RED);
                p2Btn.setTextColor(Color.BLACK);
                easyAIBtn.setTextColor(Color.RED);
                hardAIBtn.setTextColor(Color.BLACK);
            }
        });
        //change the token color to yellow-red
        yrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("player1Color", Color.RED);
                i.putExtra("player2Color", Color.YELLOW);
                //set button text colors to indicate options
                yrBtn.setTextColor(Color.RED);
                bgBtn.setTextColor(Color.BLACK);
            }
        });
        //change the token color to blue-green
        bgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("btn", "BG button clicked");
                i.putExtra("player1Color", Color.GREEN);
                i.putExtra("player2Color", Color.CYAN);
                //set button text colors to indicate options
                bgBtn.setTextColor(Color.RED);
                yrBtn.setTextColor(Color.BLACK);
            }
        });
        //save changes and send info into activity
        saveGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start a new activity
                startActivity(i);
                finish(); //finish this activity so that
            }
        });
        //cancel and kill the activity
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options_menu, menu);
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
