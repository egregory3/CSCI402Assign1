package com.esquared.tictactoeextreme;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView)findViewById(R.id.creditsLabel)).setText(Html.fromHtml("Brought to you by e<sup><font size=14px>2</font></sup>"));
    }
}
