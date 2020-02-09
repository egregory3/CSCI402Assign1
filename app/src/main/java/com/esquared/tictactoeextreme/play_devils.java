//Eric Gregory
//CSCI 4010, Spring 2019
//Professor John Nicholson
//Assignment 5

package com.esquared.tictactoeextreme;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class play_devils extends AppCompatActivity
        implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];
    private int roundCount = 0;
    private boolean p1Turn = true;
    private char xORo = 'X';
    private String winplayer = "";
    TextView player_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_devils);

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);

                player_tv = findViewById(R.id.playerTV);
            }
        }

        if(myRand() == 0){
            player_tv.setText("Player 1: Make your move");
            xORo = 'X';
        }else{
            player_tv.setText("Player 2: Make your move");
            xORo = 'O';
        }
        };
       /* Button resetbutton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < 6; i++){
                    for(int j = 0; j < 6; j++) {
                        buttons[i][j].setText("");
                        buttons[i][j].setBackgroundResource(android.R.drawable.btn_default);
                        roundCount = 0;
                        orderTurn = true;
                        xORo = 'X';
                        player_tv.setText("Order: Make Your Move!");
                        playButton.setText("Current:X, Click to Change");
                    }
                }
            }
        });
    }*/


    @Override
    public void onClick(View v) {
        boolean gamewon = false;
        if (!((Button) v).getText().toString().equals("")){
            return;
        }

        roundCount += 1;
        if (xORo == 'X'){
            ((Button)v).setBackgroundResource(R.drawable.xbutton);
            ((Button) v).setTextSize(0);
            ((Button)v).setText("X");


        }
        else{
            ((Button)v).setBackgroundResource(R.drawable.obutton);
            ((Button)v).setTextSize(0);
            ((Button)v).setText("O");

        }

        if (p1Turn ){
            if (myRand() == 0) {
                p1Turn = true;
                xORo = 'X';
                player_tv.setText("Player 1: Go Again!");
            }else{
                p1Turn = false;
                xORo = 'O';
                player_tv.setText("Player 2: Back to you!");
            }
        } else {
            if (myRand() == 0) {
                p1Turn = true;
                xORo = 'x';
                player_tv.setText("Player 1: Back to you!");
            } else {
                p1Turn = false;
                xORo = 'O';
                player_tv.setText("Player 2: Go Again!");
            }
        }
        gamewon = checkWin();
        if(gamewon){
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Player " + winplayer + " wins!");
            builder.setMessage("Player " + winplayer + " triumphed.  Click Below to reset game and play again.");
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    for(int i = 0; i < 3; i++){
                        for(int j = 0; j < 3; j++) {
                            buttons[i][j].setText("");
                            buttons[i][j].setBackgroundResource(android.R.drawable.btn_default);
                            roundCount = 0;
                            p1Turn = true;
                            xORo = 'X';
                            if(myRand() == 0){
                                player_tv.setText("Player 1: Make your move");
                                xORo = 'X';
                            }else{
                                player_tv.setText("Player 2: Make your move");
                                xORo = 'O';
                            }
                        }
                    }
                }
            });
            builder.show();
        }
        if(roundCount == 9){
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Draw");
            builder.setMessage("This game has resulted in a draw.  Play again");
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    for(int i = 0; i < 3; i++){
                        for(int j = 0; j < 3; j++) {
                            buttons[i][j].setText("");
                            roundCount = 0;
                            p1Turn = true;
                            xORo = 'X';
                            player_tv.setText("Player 1: Make Your Move!");
                        }
                    }
                }
            });
            builder.show();
        }


    }

    public boolean checkWin() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && (!(field[i][0]).equals(""))) {
                if (field[i][0] == "X"){
                    winplayer = "1";
                }else{
                    winplayer = "2";
                }
                return true;
            }


        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && (!(field[0][i].equals("")))) {
                if (field[0][i] == "X"){
                    winplayer = "1";
                }else{
                    winplayer = "2";
                }
                return true;
            }


            if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !(field[1][1].equals(""))) {
                if (field[0][0] == "X"){
                    winplayer = "1";
                }else{
                    winplayer = "2";
                }
                return true;
            }
            if(field[0][2].equals(field[2][2]) && field[0][2].equals(field[2][0]) && !(field[2][2].equals(""))){
                if (field[0][2] == "X"){
                    winplayer = "1";
                }else{
                    winplayer = "2";
                }
            }

        }
        return false;
    }
    public int myRand(){
        final int random = new Random().nextInt();
        return random %2;
    }
};

