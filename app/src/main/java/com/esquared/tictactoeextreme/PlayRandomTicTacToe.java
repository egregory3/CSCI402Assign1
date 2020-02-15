package com.esquared.tictactoeextreme;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class PlayRandomTicTacToe extends AppCompatActivity
        implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private int coinFlipCount = 0;
    private int roundCount = 0;
    private boolean p1Turn = true;
    private char xORo = 'X';
    private String winPlayer = "";
    TextView playerTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_random);

        /*
        //Displaying Random Turn Tic Tac Toe Rules in gameRulesLabel and gameRulesTextView
        TextView gameRulesLabel= findViewById(R.id.gameRulesLabel);
        gameRulesLabel.setText("RULES:");


        TextView gameRulesTextView = findViewById(R.id.gameRulesTextView);
        gameRulesTextView.setText("The coin flip determines whose turn it is! " +
                "Get three in a row horizontally, vertically, or diagnonally to win! ");
        */

        playerTextView = findViewById(R.id.playerTextView);

        final Button buttonCoinFlip = findViewById(R.id.buttonCoinFlip);
        buttonCoinFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (roundCount == 0) {
                    if (myRand() == 0) {
                        playerTextView.setText("Player 1: Make your move");
                        playerTextView.setGravity(Gravity.CENTER);
                        playerTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                        xORo = 'X';
                        p1Turn = true;
                    } else {
                        playerTextView.setText("Player 2: Make your move");
                        playerTextView.setGravity(Gravity.CENTER);
                        playerTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        xORo = 'O';
                        p1Turn = false;
                    }
                } else {
                    if (p1Turn == true) {
                        if (myRand() == 0) {
                            p1Turn = true;
                            xORo = 'X';
                            playerTextView.setText("Player 1: Go Again!");
                            playerTextView.setGravity(Gravity.CENTER);
                            playerTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        } else {
                            p1Turn = false;
                            xORo = 'O';
                            playerTextView.setText("Player 2: Back to you!");
                            playerTextView.setGravity(Gravity.CENTER);
                            playerTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        }
                    } else {
                        if (myRand() == 0) {
                            p1Turn = true;
                            xORo = 'X';
                            playerTextView.setText("Player 1: Back to you!");
                            playerTextView.setGravity(Gravity.CENTER);
                            playerTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        } else {
                            p1Turn = false;
                            xORo = 'O';
                            playerTextView.setText("Player 2: Go Again!");
                            playerTextView.setGravity(Gravity.CENTER);
                            playerTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        }
                    }
                }

                coinFlipCount++;


            }
        });

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);

            }
        }


    }


    @Override
    public void onClick(View v) {
        if (coinFlipCount <= roundCount) {
            Toast.makeText(getApplicationContext(), "You must flip the coin to see whose turn is next!", Toast.LENGTH_SHORT).show();
        } else {
            boolean gamewon = false;
            if (!((Button) v).getText().toString().equals("")) {
                return;
            }

            roundCount += 1;
            if (xORo == 'X') {
                v.setBackgroundResource(R.drawable.xbutton);
                ((Button) v).setTextSize(0);
                ((Button) v).setText("X");


            } else {
                v.setBackgroundResource(R.drawable.obutton);
                ((Button) v).setTextSize(0);
                ((Button) v).setText("O");

            }


            gamewon = checkWin();
            if (gamewon) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Player " + winPlayer + " wins!");
                builder.setMessage("Player " + winPlayer + " triumphed.  Click Below to reset game and play again.");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                buttons[i][j].setText("");
                                buttons[i][j].setBackgroundResource(android.R.drawable.btn_default);
                                roundCount = 0;
                                p1Turn = true;
                                xORo = 'X';
                                if (myRand() == 0) {
                                    playerTextView.setText("Player 1: Make your move");
                                    playerTextView.setGravity(Gravity.CENTER);
                                    playerTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    xORo = 'X';
                                } else {
                                    playerTextView.setText("Player 2: Make your move");
                                    playerTextView.setGravity(Gravity.CENTER);
                                    playerTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    xORo = 'O';
                                }
                            }
                        }
                    }
                });
                builder.show();
            }
            if (roundCount == 9) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Draw");
                builder.setMessage("This game has resulted in a draw.  Play again");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                buttons[i][j].setText("");
                                roundCount = 0;
                                p1Turn = true;
                                xORo = 'X';
                                playerTextView.setText("Player 1: Make Your Move!");
                            }
                        }
                    }
                });
                builder.show();
            }
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
                if (field[i][0] == "X") {
                    winPlayer = "1";
                } else {
                    winPlayer = "2";
                }
                return true;
            }


        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && (!(field[0][i].equals("")))) {
                if (field[0][i] == "X") {
                    winPlayer = "1";
                } else {
                    winPlayer = "2";
                }
                return true;
            }


            if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !(field[1][1].equals(""))) {
                if (field[0][0] == "X") {
                    winPlayer = "1";
                } else {
                    winPlayer = "2";
                }
                return true;
            }
            if (field[0][2].equals(field[2][2]) && field[0][2].equals(field[2][0]) && !(field[2][2].equals(""))) {
                if (field[0][2] == "X") {
                    winPlayer = "1";
                } else {
                    winPlayer = "2";
                }
            }

        }
        return false;
    }


    public int myRand() {
        final int random = new Random().nextInt();
        return random % 2;
    }

}

