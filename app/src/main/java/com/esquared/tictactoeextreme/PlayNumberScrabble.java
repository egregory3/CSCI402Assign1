//Eric Gregory and Eric Raymond
//CSCI 4020, Fall 2020
//Professor John Nicholson
//Assignment 1

package com.esquared.tictactoeextreme;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PlayNumberScrabble extends AppCompatActivity
        implements View.OnClickListener {

    ArrayList<Integer> player1NumberBank = new ArrayList<Integer>();
    ArrayList<Integer> player2NumberBank = new ArrayList<Integer>();
    private Button[][] buttons = new Button[3][3];
    private EditText numberToBePlayedEditText;
    private String numberToBePlayedText;
    private int numberToBePlayedInt;
    private TextView playerTextView;
    private int roundCount;
    private boolean p1Turn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_play_number);

        playerTextView = findViewById(R.id.playerTextView);
        roundCount = 0;
        p1Turn = true;

        for (int i = 1; i <= 9; i = i + 2) {
            player1NumberBank.add(i);
        }

        for (int i = 2; i <= 9; i = i + 2) {
            player2NumberBank.add(i);
        }


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

        boolean gamewon = false;

        numberToBePlayedEditText = findViewById(R.id.numberToBePlayedEditText);
        numberToBePlayedText = numberToBePlayedEditText.getText().toString();


        if (numberToBePlayedText.equals("")) {
            Toast.makeText(getApplicationContext(), "Please input a number according to the rules!", Toast.LENGTH_SHORT).show();
            return;
        }

        if ((((Button) v).getText().toString().equals(""))) {
            numberToBePlayedInt = Integer.parseInt(numberToBePlayedText);
            if (p1Turn == true) {
                if (player1NumberBank.contains(numberToBePlayedInt)) {
                    ((Button) v).setText(Integer.toString(numberToBePlayedInt));
                    for (int i = 0; i < player1NumberBank.size(); i++) {
                        int test = player1NumberBank.get(i);
                        if (test == numberToBePlayedInt) {
                            player1NumberBank.remove(i);
                        }
                    }
                    gamewon = checkWin();
                    p1Turn = false;
                    playerTextView.setText("Player 2's Turn!");
                    numberToBePlayedEditText.getText().clear();
                    if (gamewon) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("Player 1 Wins!");
                        builder.setMessage("Player 1 triumphed.  Click Below to reset game and play again.");
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for (int i = 0; i <= 2; i++) {
                                    for (int j = 0; j <= 2; j++) {
                                        buttons[i][j].setText("");

                                    }
                                }

                                roundCount = 0;
                                p1Turn = true;
                                playerTextView.setText("Player 1's Turn!");
                                for (int k = 1; k <= 9; k = k + 2) {
                                    player1NumberBank.add(k);
                                }

                                for (int k = 2; k <= 9; k = k + 2) {
                                    player2NumberBank.add(k);
                                }
                            }
                        });

                        builder.show();
                    }
                    if (roundCount == 9 && (checkWin() == false)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("Draw");
                        builder.setMessage("This game has resulted in a draw.  Play again");
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for (int i = 0; i <= 2; i++) {
                                    for (int j = 0; j <= 2; j++) {
                                        buttons[i][j].setText("");

                                    }
                                }

                                roundCount = 0;
                                p1Turn = true;
                                playerTextView.setText("Player 1's Turn!");
                                for (int k = 1; k <= 9; k = k + 2) {
                                    player1NumberBank.add(k);
                                }

                                for (int k = 2; k <= 9; k = k + 2) {
                                    player2NumberBank.add(k);
                                }
                            }
                        });

                        builder.show();
                    }


                } else {
                    Toast.makeText(getApplicationContext(), "Please input a number according to the rules!", Toast.LENGTH_SHORT).show();
                    numberToBePlayedEditText.getText().clear();
                    return;
                }
            } else {
                if (player2NumberBank.contains(numberToBePlayedInt)) {
                    ((Button) v).setText(Integer.toString(numberToBePlayedInt));
                    for (int i = 0; i < player2NumberBank.size(); i++) {
                        int test = player2NumberBank.get(i);
                        if (test == numberToBePlayedInt) {
                            player2NumberBank.remove(i);
                        }
                    }
                    gamewon = checkWin();
                    p1Turn = true;
                    numberToBePlayedEditText.getText().clear();
                    playerTextView.setText("Player 1's Turn!");
                    if (gamewon) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("Player 2 Wins!");
                        builder.setMessage("Player 2 triumphed.  Click Below to reset game and play again.");
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for (int i = 0; i <= 2; i++) {
                                    for (int j = 0; j <= 2; j++) {
                                        buttons[i][j].setText("");

                                    }
                                }

                                roundCount = 0;
                                p1Turn = true;
                                playerTextView.setText("Player 1's Turn!");
                                for (int k = 1; k <= 9; k = k + 2) {
                                    player1NumberBank.add(k);
                                }

                                for (int k = 2; k <= 9; k = k + 2) {
                                    player2NumberBank.add(k);
                                }
                            }
                        });

                        builder.show();
                    }
                    if (roundCount == 9 && (checkWin() == false)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("Draw");
                        builder.setMessage("This game has resulted in a draw.  Play again");
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for (int i = 0; i <= 2; i++) {
                                    for (int j = 0; j <= 2; j++) {
                                        buttons[i][j].setText("");

                                    }
                                }

                                roundCount = 0;
                                p1Turn = true;
                                playerTextView.setText("Player 1's Turn!");
                                for (int k = 1; k <= 9; k = k + 2) {
                                    player1NumberBank.add(k);
                                }

                                for (int k = 2; k <= 9; k = k + 2) {
                                    player2NumberBank.add(k);
                                }
                            }
                        });

                        builder.show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Please input a number according to the rules!", Toast.LENGTH_SHORT).show();
                    numberToBePlayedEditText.getText().clear();
                    return;
                }
            }
        }


        roundCount++;


    }


    public boolean checkWin() {
        String[][] field = new String[3][3];
        Integer[][] intField = new Integer[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();

                if (!(field[i][j].equals(""))) {

                    intField[i][j] = Integer.parseInt(field[i][j]);
                }

            }
        }


        if (!(field[0][0].equals("")) && !(field[1][0].equals("")) && !(field[2][0].equals(""))) {

            int total1 = intField[0][0] + intField[1][0] + intField[2][0];
            if (total1 == 15) {
                return true;
            }
        }

        if (!(field[0][1].equals("")) && !(field[1][1].equals("")) && !(field[2][1].equals(""))) {
            int total2 = intField[0][1] + intField[1][1] + intField[2][1];
            if (total2 == 15) {
                return true;
            }
        }

        if (!(field[0][2].equals("")) && !(field[1][2].equals("")) && !(field[2][2].equals(""))) {
            int total3 = intField[0][2] + intField[1][2] + intField[2][2];
            if (total3 == 15) {
                return true;
            }
        }


        if (!(field[0][0].equals("")) && !(field[0][1].equals("")) && !(field[0][2].equals(""))) {
            int total4 = intField[0][0] + intField[0][1] + intField[0][2];
            if (total4 == 15) {
                return true;
            }
        }


        if (!(field[1][0].equals("")) && !(field[1][1].equals("")) && !(field[1][2].equals(""))) {
            int total5 = intField[1][0] + intField[1][1] + intField[1][2];
            if (total5 == 15) {
                return true;
            }
        }

        if (!(field[2][0].equals("")) && !(field[2][1].equals("")) && !(field[2][2].equals(""))) {
            int total6 = intField[2][0] + intField[2][1] + intField[2][2];
            if (total6 == 15) {
                return true;
            }
        }


        if (!(field[0][0].equals("")) && !(field[1][1].equals("")) && !(field[2][2].equals(""))) {
            int total = intField[0][0] + intField[1][1] + intField[2][2];
            if (total == 15) {
                return true;
            }
        }

        if (!(field[0][2].equals("")) && !(field[1][1].equals("")) && !(field[2][0].equals(""))) {
            int total = intField[0][2] + intField[1][1] + intField[2][0];
            if (total == 15) {
                return true;
            }
        }

        return false;
    }
}
