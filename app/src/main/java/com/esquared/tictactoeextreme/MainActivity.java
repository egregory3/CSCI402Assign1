package com.esquared.tictactoeextreme;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView)findViewById(R.id.creditsLabel)).setText(Html.fromHtml("Brought to you by e<sup><font size=14px>2</font></sup>"));
        findViewById(R.id.Game1Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayGame();
            }
        });
    }
    //Open the PlayGame activity
    private void PlayGame() {
        Intent intent = new Intent(getApplicationContext(),
                play_devils.class);
        startActivity(intent);
    }

}
