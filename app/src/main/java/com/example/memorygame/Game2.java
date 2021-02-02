package com.example.memorygame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import static com.example.memorygame.Settings.SHARED_PREFS;
import static com.example.memorygame.Settings.SLIDE1;
import static com.example.memorygame.Settings.SWITCH1;
import static com.example.memorygame.Settings.TEXT;

public class Game2 extends AppCompatActivity {

    int image1 = R.drawable.java_logo;

    int[] images = {
            image1, image1};

    int click = 0;

    int[] match = new int[2];
    ImageButton[] trial = new ImageButton[2];

    private TextView pointsText;
    int points = 0;

    private TextView timer;
    int seconds = 0;
    boolean running;

    int finish = 0;

    boolean seen = false;

    private ImageButton iv_11, iv_12;

    String text = "3";
    Boolean toggle;
    int time = 3000;

    int slider;
    SharedPreferences sp;

    Handler handler = new Handler();
    Handler handler2 = new Handler();
    Runnable runnable;
    Runnable runnable2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cards2);

        sp = getApplicationContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        slider = sp.getInt(SLIDE1, 12);
        toggle = sp.getBoolean(SWITCH1, false);
        text = sp.getString(TEXT, "3");


        time = (1000 * Integer.parseInt(text));

        pointsText = (TextView) findViewById(R.id.points);

        iv_11 = (ImageButton) findViewById(R.id.iv_11);
        iv_12 = (ImageButton) findViewById(R.id.iv_12);

        shuffleArray(images);

        iv_11.setOnClickListener(v -> {
            click++;
            fun(iv_11, 0, click);
        });
        iv_12.setOnClickListener(v -> {
            click++;
            fun(iv_12, 1, click);
        });

        if (seen) {
            click = click - 1;
        }

        timer = (TextView) findViewById(R.id.timer);

        handler.post(new Runnable() {
            public void run() {

                int minutes = (seconds % 3600) / 60;
                int sec = seconds % 60;
                String timee= String.format("%02d:%02d", minutes, sec);
                timer.setText("Time Passed: " + timee);

                if (running) {
                    seconds++;
                }

                handler.postDelayed(this, 1000);
            }
        });

        if (toggle) {
            tog();
        }
    }


    private void tog() {
        iv_11.setImageResource(images[0]);
        iv_12.setImageResource(images[1]);
        runnable = new Runnable() {
            public void run() {
                iv_11.setImageResource(R.drawable.back_of_card);
                iv_12.setImageResource(R.drawable.back_of_card);
            }
        };
        handler.postDelayed(runnable, time);
    }

    private void fun(ImageButton iv, int i, int click) {

        //if toggle was on and somebody clicks a card before the time runs out, it doesn't mess up the time
        if (click == 1 && toggle == true) {
            runnable.run();
            handler.removeCallbacks(runnable);
        }

        //to make sure the user has the same(correct) amount of time every time he flips 2 cards
        if (click % 2 != 0 && click != 1) {
            runnable2.run();
            handler2.removeCallbacks(runnable2);
        }

        //flips the card clicked on around
        iv.setImageResource(images[i]);

        //user clicks on another card while his "time" is not up
        //first clicks that isn't a first click
        if (click > 1 && click % 2 != 0) {
            trial[0].setImageResource(R.drawable.back_of_card);
            trial[1].setImageResource(R.drawable.back_of_card);
            iv.setImageResource(images[i]);
        }

        //odd clicks (first clicks)
        if (click % 2 != 0) {
            match[0] = images[i];
            trial[0] = iv;
            running = true;
        }

        //even click (second clicks)
        if (click % 2 == 0) {
            match[1] = images[i];
            trial[1] = iv;
            //if its the same card just turn them around and go to next turn
            if (trial[0] == trial[1]) {
                seen = true;
                trial[0].setImageResource(R.drawable.back_of_card);
                trial[1].setImageResource(R.drawable.back_of_card);
                if (points > 0) {
                    points = points - 1;
                }
            }
            //if its not the same card
            else {
                //if they match
                if (match[0] == match[1]) {
                    if (!seen) {
                        Toast.makeText(getApplicationContext(), "match!!", Toast.LENGTH_SHORT).show();
                        trial[0].setVisibility(View.INVISIBLE);
                        trial[1].setVisibility(View.INVISIBLE);
                        points = points + 3;
                        finish++;
                    }
                }
                //if they dont match
                else {
                    if (points > 0) {
                        points = points - 1;
                    }
                }
                runnable2 = new Runnable() {
                    public void run() {
                        trial[0].setImageResource(R.drawable.back_of_card);
                        trial[1].setImageResource(R.drawable.back_of_card);
                        //      Toast.makeText(getApplicationContext(), "flip", Toast.LENGTH_LONG).show();
                    }
                };
                handler2.postDelayed(runnable2, time);

                //end of game
                pointsText.setText("Points: " + points);
                if (finish == 1) {
                    running = false;
                    Toast.makeText(getApplicationContext(), "Congratulations!! You've won!!", Toast.LENGTH_LONG).show();
                    Intent intnew = new Intent(this, End.class);
                    startActivity(intnew);
                }
            }
        }
    }

    //Fisher–Yates shuffle
    static void shuffleArray(int[] array) {
        Random num = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = num.nextInt(i + 1);
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }

}