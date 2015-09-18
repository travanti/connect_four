package com.example.administrator.connectfour;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    SVGameBoard column1, column2, column3, column4, column5, column6, column7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        column1 = (SVGameBoard) findViewById(R.id.gameBoardCol1);
//        column2 = (SVGameBoard) findViewById(R.id.gameBoardCol2);
//        column3 = (SVGameBoard) findViewById(R.id.gameBoardCol3);
//        column4 = (SVGameBoard) findViewById(R.id.gameBoardCol4);
//        column5 = (SVGameBoard) findViewById(R.id.gameBoardCol5);
//        column6 = (SVGameBoard) findViewById(R.id.gameBoardCol6);
//        column7 = (SVGameBoard) findViewById(R.id.gameBoardCol7);
//
//        column1.drawColumn(canvas);
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
