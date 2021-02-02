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
import static com.example.memorygame.Settings.SWITCH1;
import static com.example.memorygame.Settings.TEXT;

public class Game20 extends AppCompatActivity {
    int image1 = R.drawable.java_logo;
    int image2 = R.drawable.matlab_logo;
    int image3 = R.drawable.cpp_logo;
    int image4 = R.drawable.pascal_logo;
    int image5 = R.drawable.python_logo;
    int image6 = R.drawable.javascript_logo;
    int image7 = R.drawable.c_logo;
    int image8 = R.drawable.perl_logo;
    int image9 = R.drawable.ruby_logo;
    int image10 = R.drawable.swift_logo;

    int[] images = {
            image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image1,
            image2, image3, image4, image5, image6, image7, image8, image9, image10};

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

    private ImageButton iv_11, iv_12, iv_13, iv_14, iv_21, iv_22, iv_23, iv_24, iv_31, iv_32, iv_33, iv_34, iv_41, iv_42, iv_43, iv_44, iv_51, iv_52, iv_53, iv_54;
    String text = "3";
    Boolean toggle;
    int time = 3000;

    SharedPreferences sp;

    Handler handler = new Handler();
    Handler handler2 = new Handler();
    Runnable runnable;
    Runnable runnable2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cards20);

        sp = getApplicationContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        toggle = sp.getBoolean(SWITCH1, false);
        text = sp.getString(TEXT, "3");

        time = (1000 * Integer.parseInt(text));
        pointsText = (TextView) findViewById(R.id.points);

        iv_11 = (ImageButton) findViewById(R.id.iv_11);
        iv_12 = (ImageButton) findViewById(R.id.iv_12);
        iv_13 = (ImageButton) findViewById(R.id.iv_13);
        iv_14 = (ImageButton) findViewById(R.id.iv_14);
        iv_21 = (ImageButton) findViewById(R.id.iv_21);
        iv_22 = (ImageButton) findViewById(R.id.iv_22);
        iv_23 = (ImageButton) findViewById(R.id.iv_23);
        iv_24 = (ImageButton) findViewById(R.id.iv_24);
        iv_31 = (ImageButton) findViewById(R.id.iv_31);
        iv_32 = (ImageButton) findViewById(R.id.iv_32);
        iv_33 = (ImageButton) findViewById(R.id.iv_33);
        iv_34 = (ImageButton) findViewById(R.id.iv_34);
        iv_41 = (ImageButton) findViewById(R.id.iv_41);
        iv_42 = (ImageButton) findViewById(R.id.iv_42);
        iv_43 = (ImageButton) findViewById(R.id.iv_43);
        iv_44 = (ImageButton) findViewById(R.id.iv_44);
        iv_51 = (ImageButton) findViewById(R.id.iv_51);
        iv_52 = (ImageButton) findViewById(R.id.iv_52);
        iv_53 = (ImageButton) findViewById(R.id.iv_53);
        iv_54 = (ImageButton) findViewById(R.id.iv_54);
        shuffleArray(images);
        iv_11.setOnClickListener(v -> {
            click++;
            fun(iv_11, 0, click);
        });
        iv_12.setOnClickListener(v -> {
            click++;
            fun(iv_12, 1, click);
        });
        iv_13.setOnClickListener(v -> {
            click++;
            fun(iv_13, 2, click);
        });
        iv_14.setOnClickListener(v -> {
            click++;
            fun(iv_14, 3, click);
        });
        iv_21.setOnClickListener(v -> {
            click++;
            fun(iv_21, 4, click);
        });
        iv_22.setOnClickListener(v -> {
            click++;
            fun(iv_22, 5, click);
        });
        iv_23.setOnClickListener(v -> {
            click++;
            fun(iv_23, 6, click);
        });
        iv_24.setOnClickListener(v -> {
            click++;
            fun(iv_24, 7, click);
        });
        iv_31.setOnClickListener(v -> {
            click++;
            fun(iv_31, 8, click);
        });
        iv_32.setOnClickListener(v -> {
            click++;
            fun(iv_32, 9, click);
        });
        iv_33.setOnClickListener(v -> {
            click++;
            fun(iv_33, 10, click);
        });
        iv_34.setOnClickListener(v -> {
            click++;
            fun(iv_34, 11, click);
        });
        iv_41.setOnClickListener(v -> {
            click++;
            fun(iv_41, 12, click);
        });
        iv_42.setOnClickListener(v -> {
            click++;
            fun(iv_42, 13, click);
        });
        iv_43.setOnClickListener(v -> {
            click++;
            fun(iv_43, 14, click);
        });
        iv_44.setOnClickListener(v -> {
            click++;
            fun(iv_44, 15, click);
        });
        iv_51.setOnClickListener(v -> {
            click++;
            fun(iv_51, 16, click);
        });
        iv_52.setOnClickListener(v -> {
            click++;
            fun(iv_52, 17, click);
        });
        iv_53.setOnClickListener(v -> {
            click++;
            fun(iv_51, 18, click);
        });
        iv_54.setOnClickListener(v -> {
            click++;
            fun(iv_52, 19, click);
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
        iv_13.setImageResource(images[2]);
        iv_14.setImageResource(images[3]);
        iv_21.setImageResource(images[4]);
        iv_22.setImageResource(images[5]);
        iv_23.setImageResource(images[6]);
        iv_24.setImageResource(images[7]);
        iv_31.setImageResource(images[8]);
        iv_32.setImageResource(images[9]);
        iv_33.setImageResource(images[10]);
        iv_34.setImageResource(images[11]);
        iv_41.setImageResource(images[12]);
        iv_42.setImageResource(images[13]);
        iv_43.setImageResource(images[14]);
        iv_44.setImageResource(images[15]);
        iv_51.setImageResource(images[16]);
        iv_52.setImageResource(images[17]);
        iv_53.setImageResource(images[18]);
        iv_54.setImageResource(images[19]);

        runnable = new Runnable() {
            public void run() {
                iv_11.setImageResource(R.drawable.back_of_card);
                iv_12.setImageResource(R.drawable.back_of_card);
                iv_13.setImageResource(R.drawable.back_of_card);
                iv_14.setImageResource(R.drawable.back_of_card);
                iv_21.setImageResource(R.drawable.back_of_card);
                iv_22.setImageResource(R.drawable.back_of_card);
                iv_23.setImageResource(R.drawable.back_of_card);
                iv_24.setImageResource(R.drawable.back_of_card);
                iv_31.setImageResource(R.drawable.back_of_card);
                iv_32.setImageResource(R.drawable.back_of_card);
                iv_33.setImageResource(R.drawable.back_of_card);
                iv_34.setImageResource(R.drawable.back_of_card);
                iv_41.setImageResource(R.drawable.back_of_card);
                iv_42.setImageResource(R.drawable.back_of_card);
                iv_43.setImageResource(R.drawable.back_of_card);
                iv_44.setImageResource(R.drawable.back_of_card);
                iv_51.setImageResource(R.drawable.back_of_card);
                iv_52.setImageResource(R.drawable.back_of_card);
                iv_53.setImageResource(R.drawable.back_of_card);
                iv_54.setImageResource(R.drawable.back_of_card);
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
                if (finish == 10) {
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