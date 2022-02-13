package com.ankit.tictactoe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class play extends AppCompatActivity implements View.OnClickListener {
    Button b0,b1,b2,b3,b4,b5,b6,b7,b8;
    TextView header,index,vic;

    int p1=0;
    int p2=1;
    int active = p1;
    int[] filled = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    boolean gameactive = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        header=findViewById(R.id.header);
        index=findViewById(R.id.index);
        vic=findViewById(R.id.vic);
        b0=findViewById(R.id.b0);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b3=findViewById(R.id.b3);
        b4=findViewById(R.id.b4);
        b5=findViewById(R.id.b5);
        b6=findViewById(R.id.b6);
        b7=findViewById(R.id.b7);
        b8=findViewById(R.id.b8);

        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

        if (gameactive == false)
            return;

        Button clicked = findViewById(v.getId());
        int clickedtag = Integer.parseInt(v.getTag().toString());

        if (filled[clickedtag] != -1){
            return;
        }

        filled[clickedtag] = active;

        if (active == p1) {
            clicked.setText("X");
            active = p2;
            header.setText("P2's Turn");
            index.setText("");
            clicked.setBackground(getDrawable(android.R.color.holo_blue_bright));
        }
        else{
            clicked.setText("O");
            active = p1;
            header.setText("P1's Turn");
            index.setText("");
            clicked.setBackground(getDrawable(android.R.color.holo_green_light));
        }
        check();
    }

    private void check() {
        int[][] win = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

        for (int i=0; i<8;i++){
            int val0 = win[i][0];
            int val1 = win[i][1];
            int val2 = win[i][2];

            if (filled[val0] == filled[val1] && filled[val1] == filled[val2]){
                if (filled[val0] != -1){
                  gameactive = false;
                  if (filled[val0] == p1){
                      header.setText("Player 1 Won");
                      vic.setText("Happiness is Temporary\nYou have 5 Sec to celebrate your victory");
                      Handler handler = new Handler();
                      handler.postDelayed(new Runnable() {
                          @Override
                          public void run() {
                              showDialog();
                          }
                      }, 5000);

                  }
                  else{
                      header.setText("Player 2 Won");
                      vic.setText("Happiness is Temporary\nYou have 5 Sec to celebrate your victory");
                      Handler handler = new Handler();
                      handler.postDelayed(new Runnable() {
                          @Override
                          public void run() {
                              showDialog();
                          }
                      }, 5000);
                  }
                }
            }
        }
        int count=0;
        for (int i=0;i<9;i++){
            if (filled[i] !=-1){
                count++;
            }
        }
        if (count==9){
            header.setText("It's A Draw");
            vic.setText("Thanks for Playing!!!!\nNobody Won Fight harder Next Time to Win");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showDialog();
                }
            }, 3000);

        }
    }
    private void showDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Do You Wish To Play Again")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restart();
                    }
                })
                .show();

    }



    private void restart(){
        active = p1;
        header.setText("New Game Begins");
        index.setText("P1's turn");
        vic.setText("");
        filled = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
        b0.setText("");
        b1.setText("");
        b2.setText("");
        b3.setText("");
        b4.setText("");
        b5.setText("");
        b6.setText("");
        b7.setText("");
        b8.setText("");
        gameactive = true;
    }
}