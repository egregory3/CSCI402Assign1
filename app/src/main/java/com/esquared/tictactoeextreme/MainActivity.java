//Eric Gregory and Eric Raymond
//CSCI 4020, Fall 2020
//Professor John Nicholson
//Assignment 1

package com.esquared.tictactoeextreme;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView) findViewById(R.id.creditsLabel)).setText(Html.fromHtml("Brought to you by e<sup><font size=14px>2</font></sup>"));


        findViewById(R.id.game1Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayGame1();
            }
        });

        findViewById(R.id.game2Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayGame2();
            }
        });

        findViewById(R.id.game3Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayGame3();
            }
        });

    }

    //Open the PlayGame1 activity
    private void PlayGame1() {
        Intent intent = new Intent(getApplicationContext(),
                play_devils.class);
        startActivity(intent);
    }

    //Open the PlayGame2 activity
    private void PlayGame2() {
        Intent intent = new Intent(getApplicationContext(),
                PlayRandomTicTacToe.class);
        startActivity(intent);
    }

    //Open the PlayGame3 activity
    private void PlayGame3() {
        Intent intent = new Intent(getApplicationContext(),
                PlayNumberScrabble.class);
        startActivity(intent);
    }

}
