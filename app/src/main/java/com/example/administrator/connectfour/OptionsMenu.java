package com.example.administrator.connectfour;

import android.content.Intent;
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


public class OptionsMenu extends ActionBarActivity {

    //buttons
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
        //retrieve info from main activity
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

        //change the token color to yellow-red
        yrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("player1Color", Color.RED);
                i.putExtra("player2Color", Color.YELLOW);
                yrBtn.setTextColor(Color.RED);
                //remove other btn state
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
                bgBtn.setTextColor(Color.RED);
                //remove other btn state
                yrBtn.setTextColor(Color.BLACK);
            }
        });
        //save changes and send info into activity
        saveGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use following line to resume to old activity:
                //i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
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
